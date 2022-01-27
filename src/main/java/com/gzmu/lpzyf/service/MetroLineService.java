package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.MetroLine;

import java.util.List;

public interface MetroLineService {
    //查询每个城市的地铁线路数
    Integer findLineCount(Integer city_id);
    List<MetroLine> findLineCount1();
}
