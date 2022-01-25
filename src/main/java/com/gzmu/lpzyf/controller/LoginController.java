package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.Admin;
import com.gzmu.lpzyf.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String skipLogin(Admin admin, RedirectAttributesModelMap modelMap, Model model, HttpServletRequest request,HttpServletResponse response){
        String remoteAddr = request.getRemoteAddr();
        String remoteUser = request.getRemoteUser();
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        if (request.getSession().getAttribute("username")!=null){
            return "index";
        }else if(admin.getPhone()==null){
            return "login";
        }
        else{
            Admin findAdmin = adminService.findByPhone(admin.getPhone());
            String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
            if(findAdmin==null){
                modelMap.addFlashAttribute("msg","用户不存在");
                return "redirect:/login";
            }else if(!findAdmin.getPassword().equals(password)){
                modelMap.addFlashAttribute("msg","密码错误");

                log.warn("ip:["+remoteAddr+"]"+",user:["+remoteUser+"]Host:["+remoteHost+"]" +
                        "port:["+remotePort+"]admin:["+admin.getPhone()+"]result:[login Fail]type:[login]");
                return "redirect:/login";
            }else{
                //model.addAttribute("username",findAdmin.getName());
                //对电话进行处理
                String phone = findAdmin.getPhone();
                String sub1 = phone.substring(0,3);
                String sub2 = phone.substring(7,11);
                phone = sub1 +"****"+sub2;
                request.getSession().setAttribute("username",findAdmin.getName());
                request.getSession().setAttribute("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(findAdmin.getCreateTime()));
                request.getSession().setAttribute("phone",phone);
                log.info("ip:["+remoteAddr+"]"+ ",user:["+remoteUser+"]Host:["+remoteHost+"]" +
                        "port:["+remotePort+"]admin:["+admin.getPhone()+"]result:[login Success]type:[login]");
                return "index";
            }
        }
    }

    @RequestMapping("/to_register")
    public String to_register(){
        return "register";
    }
    @RequestMapping("/register")
    public String register(Admin admin, RedirectAttributesModelMap modelMap, HttpServletRequest request){
             String remoteAddr = request.getRemoteAddr();
             String remoteUser = request.getRemoteUser();
             String remoteHost = request.getRemoteHost();
             int remotePort = request.getRemotePort();
            adminService.insert(admin);
           log.info("ip:["+remoteAddr+"]"+ ",user:["+remoteUser+"]Host:["+remoteHost+"]" +
                "port:["+remotePort+"]admin:["+admin.getPhone()+"]result:[register Success]type:[register]");
            modelMap.addFlashAttribute("msg","注册成功，请登录");
            return "redirect:/login";

    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("phone");
        return "redirect:/login";
    }
}
