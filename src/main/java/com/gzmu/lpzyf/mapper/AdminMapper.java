package com.gzmu.lpzyf.mapper;

import com.gzmu.lpzyf.bean.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    Admin findByPhone(String phone);
    void insert(Admin admin);
}
