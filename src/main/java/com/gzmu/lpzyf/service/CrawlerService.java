package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.City;

import java.util.List;
import java.util.Map;

public interface CrawlerService {
    Map<String,List> getSubwayData(String type);
    Map<String,List> getStationData();
    String getCitiesData();
}
