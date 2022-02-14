package com.gzmu.lpzyf.service;

import com.gzmu.lpzyf.bean.Admin;

public interface AdminService {
    Admin findByPhone(String phone);
    void insert(Admin admin);

    /**
     * 修改昵称
     * @param name
     * @param phone
     */
    int updateName(String name,String phone);

    /**
     * 修改密码
     * @param password
     * @param phone
     */
    void updatePassword(String password,String phone,String code);
}
