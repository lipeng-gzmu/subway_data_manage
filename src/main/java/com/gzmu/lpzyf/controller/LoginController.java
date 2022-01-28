package com.gzmu.lpzyf.controller;

import com.gzmu.lpzyf.bean.Admin;
import com.gzmu.lpzyf.service.AdminService;
import com.gzmu.lpzyf.util.GetCodeUtil;
import com.gzmu.lpzyf.util.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
                        "port:["+remotePort+"]admin:["+admin.getPhone()+"]result:[login Fail]type:[login]method:[phone]");
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
                        "port:["+remotePort+"]admin:["+admin.getPhone()+"]result:[login Success]type:[login]method:[phone]");
                return "index";
            }
        }
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public int sendCode(String phone,HttpServletRequest request){
        if (phone!=null||phone.equals("")){
            String code = GetCodeUtil.getCode();
            request.getSession().setAttribute("code",code);
            Map<String,String> send = SendSmsUtil.send(code, "+86" + phone);
            System.out.println(send);
            if (send.get("Code").equals("Ok")){
                return 200;
            }else return 500;
        }
        return 500;
    }

    @RequestMapping("/loginByCode")
    public String loginByCode(String phone,String code,RedirectAttributesModelMap modelMap,HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        String remoteUser = request.getRemoteUser();
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        if (request.getSession().getAttribute("username")!=null){
            return "index";
        }else if(phone==null||phone.equals("")){
            return "login";
        }else{
            Admin findAdmin = adminService.findByPhone(phone);
            //查询为空表示第一次使用手机号码登录则使用手机号码创建一个用户
            if(findAdmin==null){
                findAdmin = new Admin();
                findAdmin.setPhone(phone);
                findAdmin.setName("请设置昵称");
                findAdmin.setPassword("000000");
                findAdmin.setCreateTime(new Date());
                adminService.insert(findAdmin);
            }
            String systemCode = request.getSession().getAttribute("code").toString();
            System.out.println(phone + "   "+code+"   "+ systemCode);
            if (systemCode.equals(code)){
                String phone1 = findAdmin.getPhone();
                String sub1 = phone.substring(0,3);
                String sub2 = phone.substring(7,11);
                phone = sub1+"****"+sub2;
                request.getSession().setAttribute("username",findAdmin.getName());
                request.getSession().setAttribute("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(findAdmin.getCreateTime()));
                request.getSession().setAttribute("phone",phone);
                log.info("ip:["+remoteAddr+"]"+ ",user:["+remoteUser+"]Host:["+remoteHost+"]" +
                        "port:["+remotePort+"]admin:["+findAdmin.getPhone()+"]result:[login Success]type:[login]method:[code]");
                return "index";
            }else{
                log.warn("ip:["+remoteAddr+"]"+",user:["+remoteUser+"]Host:["+remoteHost+"]" +
                        "port:["+remotePort+"]admin:["+findAdmin.getPhone()+"]result:[login Fail]type:[login]method:[code]");
                modelMap.addFlashAttribute("msg","验证码错误");
                return "redirect:/login";
            }

        }
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
