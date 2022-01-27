package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.service.CityService;
import com.gzmu.lpzyf.service.MetroLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MetroLineController {
    @Autowired
    private MetroLineService metroLineService;
    @Autowired
    private CityService cityService;

    @RequestMapping("/findLineCount1")
    public List findLineCount1(){
        List<MetroLine> lineCount = metroLineService.findLineCount();
        List<MetroLine> lineCount1 = metroLineService.findLineCount1();
        for (int i=0;i<lineCount.size();i++){
            System.err.println(lineCount.get(i).getNum());
            lineCount1.get(i).setNum(lineCount.get(i).getNum());

        }
        return lineCount1;
    }
}
