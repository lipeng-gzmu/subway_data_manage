package com.gzmu.lpzyf.controller.crawler;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.bean.crawler.CitySubway;
import com.gzmu.lpzyf.service.CityService;
import com.gzmu.lpzyf.service.CrawlerService;
import com.gzmu.lpzyf.util.SpeedOfProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/getCitiesData")
    public String getCitiesData(){
        String citiesData = crawlerService.getCitiesData();
        return citiesData;
    }

    @RequestMapping("/getSubwayData")
    public Map<String,List> getSubwayData(){
        Map<String, List> subwayData = crawlerService.getSubwayData();
        return subwayData;
    }

    @RequestMapping("/SpeedOfData")
    @ResponseBody
    public double getSpeedOfData(){
        return SpeedOfProgress.suwayInsertProgress;
    }

    @RequestMapping("/insertData")
    @ResponseBody
    public void insertData(){
        synchronized (SpeedOfProgress.suwayCheck){
            SpeedOfProgress.suwayCheck.notify();
            SpeedOfProgress.suwayInsertProgressCheck=true;
        }
    }

}
