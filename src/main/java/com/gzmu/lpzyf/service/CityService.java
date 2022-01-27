package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();
    //根据Id查询
    City findById(Integer id);
}
