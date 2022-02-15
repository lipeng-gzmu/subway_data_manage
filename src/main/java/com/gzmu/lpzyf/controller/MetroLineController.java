package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.service.CityService;
import com.gzmu.lpzyf.service.MetroLineService;
import com.gzmu.lpzyf.util.FileUtil;
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
        Map<String,String> cityMap = FileUtil.readFileToMap("other/city_privince.txt");
        for (MetroLine metroLine : lineCount) {
            boolean check = true;
            Map<String,String> tempMap = new HashMap<>();
            String name=cityMap.get(metroLine.getCity().getNameCn());
            String value=metroLine.getNum().toString();
            for (Map<String, String> map : resultlist) {
                if (map.containsValue("name")){
                    map.put("value",Integer.valueOf(map.get("value")+value).toString());
                    check=false;
                }
            }
            if (check){
                tempMap.put("name",name);
                tempMap.put("value",value);
                resultlist.add(tempMap);
            }

        }
        return resultlist;
    }
}
