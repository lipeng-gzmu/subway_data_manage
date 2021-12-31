package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.Admin;
import com.gzmu.lpzyf.mapper.AdminMapper;
import com.gzmu.lpzyf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByPhone(String phone) {
        return adminMapper.findByPhone(phone);
    }
}
