package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;
    @RequestMapping("/getCities")
    public List<City> getCities(){
        List<City> allCities = cityService.getAllCities();
        return allCities;
    }
}
