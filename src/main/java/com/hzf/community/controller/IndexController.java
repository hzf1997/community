package com.hzf.community.controller;

import com.hzf.community.dao.UserDao;
import com.hzf.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private UserDao userDao;
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpSession session){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User byToken = userDao.findByToken(token);
                if (byToken!=null){
                    session.setAttribute("user",byToken);
                }
            }
        }
        return "index";
    }
}
