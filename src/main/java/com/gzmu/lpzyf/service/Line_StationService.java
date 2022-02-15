package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.MetroStation;

import java.util.List;
import java.util.Map;

public interface Line_StationService {
    //查询每条线路下的站点
    List<Map> findByCityId(String cityId);
}
