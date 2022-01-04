package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.Admin;

public interface AdminService {
    Admin findByPhone(String phone);
    void insert(Admin admin);
}
