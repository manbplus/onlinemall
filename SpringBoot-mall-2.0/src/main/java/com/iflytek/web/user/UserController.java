package com.iflytek.web.user;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iflytek.entity.User;
import com.iflytek.entity.pojo.ResultBean;
import com.iflytek.service.UserService;
import com.iflytek.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toSign.html")
    public String toSign() {
        return "mall/user/sign";
    }
    
      @RequestMapping("/toRegister.html")
    public String toRegister() {
        return "mall/user/register";
    }
      @RequestMapping("/toForget.html")
      public String toPassword() {
          return "mall/user/password";
      }

      @RequestMapping("/toDetails.html")
      public String toDetails() {
          return "mall/user/details";
      }
      
    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "mall/user/login";
    }

    @RequestMapping("/toForget1.html")
    public String toForget() {
        return "mall/user/forget";
    }
  
    @RequestMapping("/login.do")
    public void login(String username,
                      String password,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
    	Md5Hash md5Hash=new Md5Hash(password,username,5);
    	
    	System.out.println(md5Hash.toString());
        User user = userService.checkLogin(username,md5Hash.toString());
        if (user != null) {
            //登录成功 重定向到首页
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/mall/index.html");
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }

    }

    @RequestMapping("/check.do")
    public void login1(String name,
                      String phone,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        User user = userService.checkLogin1(name, phone);
        if (user != null) {
            //登录成功 重定向到首页
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/mall/user/toForget1.html");
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }

    }
    
    
    @RequestMapping("/register.do")
    public void register(String username,
                         String password,
                         String name,
                         String phone,
                         String email,
                         String addr,
                         HttpServletResponse response) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        
        Md5Hash md5Hash=new Md5Hash(password,username,5);
        
        user.setPassword(md5Hash.toString());
        
        user.setName(name);
        user.setEmail(email);
        user.setAddr(addr);
        userService.create(user);
        // 注册完成后重定向到登录页面
        response.sendRedirect("/mall/user/toLogin.html");
    }

 
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/mall/index.html");
    }

  
    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String username){
        List<User> users = userService.findByUsername(username);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }
    
   

   
    @RequestMapping("/error.html")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public ResultBean<Boolean> update(int id,String username,
                                      String password,String name,
                                      String phone,String email,
                                      String addr) {
        // 更新前先查询
        User user = userService.findById(id);
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        Md5Hash md5Hash=new Md5Hash(password,username,5);
        user.setPassword(md5Hash.toString());
        user.setAddr(addr);
        user.setEmail(email);
        user.setPhone(phone);
        userService.update(user);
        return new ResultBean<>(true);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/updatepassword.do")
    public ResultBean<Boolean> update(int id,
                                      String password) {
        // 更新前先查询
    	userService.updatepassword(id, password);
        return new ResultBean<>(true);
    }
    
}
