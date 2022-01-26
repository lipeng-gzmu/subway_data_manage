package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.City;
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
}
