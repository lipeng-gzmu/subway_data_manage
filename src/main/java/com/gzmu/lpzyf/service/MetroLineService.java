package com.gzmu.lpzyf.service;

public interface MetroLineService {
    //查询每个城市的地铁线路数
    Integer findLineCount(Integer city_id);
}
