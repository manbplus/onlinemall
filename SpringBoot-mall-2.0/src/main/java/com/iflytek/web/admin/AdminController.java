package com.iflytek.web.admin;

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
        AdminUser adminUser = adminUserService.checkLogin(request, username, password);
        response.sendRedirect("/mall/admin/toIndex.html");
    }

   
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login_user");
        response.sendRedirect("toLogin.html");
    }
}
