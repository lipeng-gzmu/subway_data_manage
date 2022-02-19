package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.service.Line_StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LineStationController {
    @Autowired
    private Line_StationService line_stationService;

    @RequestMapping("/getCitySubwayData")
    public Map<String,Object> getCitySubwayData(HttpServletRequest request){
        String cityId = request.getParameter("cityId");
        String lineId = request.getParameter("lineId");
        lineId = lineId.equals("")?"":lineId;
        List<Map> resultList = line_stationService.findByCityId(cityId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",resultList);
        resultMap.put("lineId",lineId);
        return resultMap;
    }
}
