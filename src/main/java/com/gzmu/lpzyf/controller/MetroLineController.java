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
    @RequestMapping("/findLineCount")
    public Map<String,Integer> findLineCount(){
        List<City> allCities = cityService.getAllCities();
        Map map = new HashMap();
        for(int i = 0;i<allCities.size();i++){
            Integer lineCount = metroLineService.findLineCount(allCities.get(i).getId());
            map.put(allCities.get(i).getNameCn(),lineCount);
        }

        return map;
    }
    @RequestMapping("/findLineCount1")
    public Map<String,Integer> findLineCount1(){
        List<MetroLine> lineCount1 = metroLineService.findLineCount1();
        Map map = new HashMap();
        for (int i = 0 ;i<lineCount1.size();i++){
            City city = cityService.findById(lineCount1.get(i).getCity_id());
            map.put(city.getNameCn(),lineCount1.get(i).getNum());
        }
        return map;
    }
}
