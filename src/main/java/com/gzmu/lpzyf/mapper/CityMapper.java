package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {
    List<City> findAll();
    void insert(City city);
    //根据Id查询
    City findById(Integer id);
}
