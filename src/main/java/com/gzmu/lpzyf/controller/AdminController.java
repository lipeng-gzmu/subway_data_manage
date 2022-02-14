package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/updateName")
    public int updateName(String name, HttpServletRequest request){
        String phone = request.getSession().getAttribute("phone").toString();
        int result = adminService.updateName(name, phone);
        if (result==200){
            request.getSession().setAttribute("username",name);
        }
        return result;
    }


    @RequestMapping("/updatePassword")
    public int updatePassword(HttpServletRequest request){
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String phone = request.getParameter("phone");
        adminService.updatePassword(password,phone,code);

        return 200;
    }
}
