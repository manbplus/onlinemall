package com.iflytek.web.admin;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iflytek.entity.AdminUser;
import com.iflytek.entity.pojo.ResultBean;
import com.iflytek.service.AdminUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;


    @RequestMapping("/toIndex.html")
    public String toIndex() {
        return "admin/index";
    }

  
    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "admin/login";
    }

 
    //@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login.do")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Md5Hash md5Hash=new Md5Hash(password,username,5);
    	System.out.println(md5Hash);
    	AdminUser adminUser = adminUserService.checkLogin(request, username,md5Hash.toString());
        response.sendRedirect("/mall/admin/toIndex.html");
    }

   
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login_user");
        response.sendRedirect("toLogin.html");
    }
}
