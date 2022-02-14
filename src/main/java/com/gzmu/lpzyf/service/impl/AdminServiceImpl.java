package com.gzmu.lpzyf.service.impl;

import com.gzmu.lpzyf.bean.Admin;
import com.gzmu.lpzyf.mapper.AdminMapper;
import com.gzmu.lpzyf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Admin findByPhone(String phone) {
        return adminMapper.findByPhone(phone);
    }

    @Override
    public void insert(Admin admin) {
        admin.setCreateTime(new Date());
        String password = admin.getPassword();
        String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        admin.setPassword(newPassword);
        adminMapper.insert(admin);
    }

    @Override
    public int updateName(String name, String phone) {
        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setName(name);
        int check = adminMapper.updateName(admin);
        if (check==1){
            return 200;
        }else {
            return 500;
        }


    }

    @Override
    public void updatePassword(String password, String phone,String code) {
        String systemCode = redisTemplate.opsForValue().get("updatePassword"+phone).toString();
        if (systemCode.equals(code)){
            Admin admin = new Admin();
            admin.setPhone(phone);
            admin.setPassword(password);
            adminMapper.updatePassword(admin);
        }
    }
}
