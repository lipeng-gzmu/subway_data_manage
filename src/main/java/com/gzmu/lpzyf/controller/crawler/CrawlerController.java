package com.gzmu.lpzyf.controller.crawler;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.crawler.CitySubway;
import com.gzmu.lpzyf.service.CityService;
import com.gzmu.lpzyf.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/getCities")
    public List<City> getCities(){
        List<City> allCities = cityService.getAllCities();
        return allCities;
    }

    @RequestMapping("/getSubwayData")
    public String getSubwayData(){
        String subwayData = crawlerService.getSubwayData();
        return subwayData;
    }
}
