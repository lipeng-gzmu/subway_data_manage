package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.MetroLine;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MetroLineMapper {
    void insert(MetroLine metroLine);
    //查询每个城市的地铁线路数
    List<MetroLine> findCount();
    List<MetroLine> findLineCount();
}
