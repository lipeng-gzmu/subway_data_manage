package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.Line_Station;
import com.gzmu.lpzyf.bean.MetroStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Line_StationMapper {
    List<Line_Station> findAll();
    void insert(Line_Station line_station);
    List<MetroStation> findByLineId(String lineId);
}
