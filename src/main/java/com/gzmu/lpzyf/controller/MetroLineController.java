package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.service.CityService;
import com.gzmu.lpzyf.service.MetroLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MetroLineController {
    @Autowired
    private MetroLineService metroLineService;
    @Autowired
    private CityService cityService;

    @RequestMapping("/findLineCount")
    public List findLineCount(){
        List<MetroLine> count = metroLineService.findCount();
        List<MetroLine> lineCount = metroLineService.findLineCount();
        for (int i=0;i<count.size();i++){
            lineCount.get(i).setNum(count.get(i).getNum());

        }
        return lineCount;
    }

    @RequestMapping("/findLineCountAll")
    public List<Map<String, String>> findLineCountAll(){
        List<MetroLine> count = metroLineService.findCount();
        List<MetroLine> lineCount = metroLineService.findLineCount();
        for (int i=0;i<count.size();i++){
            lineCount.get(i).setNum(count.get(i).getNum());
        }
        List<Map<String,String>> resultlist = new LinkedList<>();
        for (MetroLine metroLine : lineCount) {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("name",metroLine.getCity().getName_cn());
            tempMap.put("value",metroLine.getNum().toString());
            resultlist.add(tempMap);
        }
        return resultlist;
    }
}
