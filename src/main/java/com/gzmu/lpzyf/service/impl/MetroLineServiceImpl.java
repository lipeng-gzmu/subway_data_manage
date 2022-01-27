package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.MetroLine;
import com.gzmu.lpzyf.mapper.MetroLineMapper;
import com.gzmu.lpzyf.service.MetroLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetroLineServiceImpl implements MetroLineService {
    @Autowired
    private MetroLineMapper metroLineMapper;


    @Override
    public Integer findLineCount(Integer city_id) {
        return metroLineMapper.findLineCount(city_id);
    }

    @Override
    public List<MetroLine> findLineCount1() {
        return metroLineMapper.findLineCount1();
    }
}
