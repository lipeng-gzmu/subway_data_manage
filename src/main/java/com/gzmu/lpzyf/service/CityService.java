package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.City;

import java.util.List;

public interface CityService {
    //查询所有的城市
    List<City> getAllCities();

    //根据Id查询
    City findById(Integer id);

    //从网页获取城市
    List<City> findCities();
}
