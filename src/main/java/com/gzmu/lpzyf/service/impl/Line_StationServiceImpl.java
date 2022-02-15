package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.bean.MetroStation;
import com.gzmu.lpzyf.mapper.Line_StationMapper;
import com.gzmu.lpzyf.mapper.MetroLineMapper;
import com.gzmu.lpzyf.service.Line_StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class Line_StationServiceImpl implements Line_StationService {
    @Autowired
    private MetroLineMapper metroLineMapper;
    @Autowired
    private Line_StationMapper line_stationMapper;

    @Override
    public List<Map> findByCityId(String cityId) {
        List<MetroLine> metroLines = metroLineMapper.findByCity(cityId);
        List<Map> resultMap = new LinkedList<>();
        for (MetroLine metroLine : metroLines) {
            List<MetroStation> metroStations = line_stationMapper.findByLineId(metroLine.getId());
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("line",metroLine);
            tempMap.put("stations",metroStations);
            resultMap.add(tempMap);
        }

        return resultMap;
    }
}
