package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.service.Line_StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LineStationController {
    @Autowired
    private Line_StationService line_stationService;

    @RequestMapping("/getCitySubwayData")
    public List<Map> getCitySubwayData(String cityId){
        List<Map> resultList = line_stationService.findByCityId(cityId);
        return resultList;
    }
}
