package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.MetroStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetroStationMapper {
    //查询全部
    List<MetroStation> findAll();
    void insert(MetroStation metroStation);
}
