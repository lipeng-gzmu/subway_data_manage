package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.MetroStation;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroStationMapper {
    void insert(MetroStation metroStation);
}
