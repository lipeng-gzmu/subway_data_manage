package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.Admin;
import com.gzmu.lpzyf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String skipLogin(Admin admin, RedirectAttributesModelMap modelMap, Model model, HttpServletRequest request,HttpServletResponse response){
        if (admin.getPhone()==null){
            return "login";
        }else{
            Admin findAdmin = adminService.findByPhone(admin.getPhone());
            String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
            if(findAdmin==null){
                modelMap.addFlashAttribute("msg","用户不存在");
                return "redirect:/login";
            }else if(!findAdmin.getPassword().equals(password)){
                modelMap.addFlashAttribute("msg","密码错误");
                return "redirect:/login";
            }else{
                model.addAttribute("username",findAdmin.getName());
                request.getSession().setAttribute("phone",findAdmin.getPhone());
                return "index";
            }
        }
    }

    @RequestMapping("/register")
    public String register(Admin admin,RedirectAttributesModelMap modelMap){
        if (admin.getPhone()==null){
            return "register";
        }else{
            adminService.insert(admin);
            modelMap.addFlashAttribute("msg","注册成功，请登录");
            return "redirect:/login";
        }
    }
}
