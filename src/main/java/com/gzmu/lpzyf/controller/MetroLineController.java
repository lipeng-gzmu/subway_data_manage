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
    public List findLineCount(Model model){
        List<MetroLine> count = metroLineService.findCount();
        List<MetroLine> lineCount = metroLineService.findLineCount();
        for (int i=0;i<count.size();i++){
            lineCount.get(i).setNum(count.get(i).getNum());

        }
        return lineCount;
    }
}
