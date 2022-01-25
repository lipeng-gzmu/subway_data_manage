package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.MetroLine;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface MetroLineMapper {
    void insert(MetroLine metroLine);
    //查询每个城市的地铁线路数
    Integer findLineCount(Integer city_id);
}
