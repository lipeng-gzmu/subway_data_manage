package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.MetroLine;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroLineMapper {
    void insert(MetroLine metroLine);
}
