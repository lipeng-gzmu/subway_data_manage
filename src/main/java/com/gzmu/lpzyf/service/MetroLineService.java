package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.MetroLine;

import java.util.List;

public interface MetroLineService {
    //查询每个城市的地铁线路数
    List<MetroLine> findLineCount();
    List<MetroLine> findLineCount1();
}
