package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.City;
import com.gzmu.lpzyf.crawler.CrawlerCity;
import com.gzmu.lpzyf.mapper.CityMapper;
import com.gzmu.lpzyf.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<City> getAllCities() {
        return cityMapper.findAll();
    }

    @Override
    public City findById(Integer id) {
        return cityMapper.findById(id);
    }

    @Override
    public List<City> findCities() {
        CrawlerCity crawlerCity = new CrawlerCity();
        List<City> cites = crawlerCity.getCites();
        return cites;
    }

}
