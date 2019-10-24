package com.hzf.community.controller;

import com.hzf.community.dao.QuestionDao;
import com.hzf.community.dao.UserDao;
import com.hzf.community.entity.Question;
import com.hzf.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    @GetMapping("/publishpage")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String questionPublish(Question question, Model model, HttpServletRequest request, HttpSession session){
       model.addAttribute("title",question.getTitle());
       model.addAttribute("description",question.getDescription());
       model.addAttribute("tag",question.getTag());
        System.out.println(question);
       if (question.getTitle()==null || question.getTitle().equals("")){
           model.addAttribute("errMsg","标题不能为空");
           return "publish";
       }
       if (question.getDescription()==null || question.getDescription().equals("")){
           model.addAttribute("errMsg","描述不能为空");
           return "publish";
       }
        User byToken=null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())){
                String token = cookie.getValue();
                byToken= userDao.findByToken(token);
                if (byToken!=null){
                    session.setAttribute("user",byToken);
                }
            }
        }
        if (byToken==null){
            model.addAttribute("errMsg","你还未登录");
            return "publish";
        }
        question.setCreator(byToken.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        System.out.println(question);
        questionDao.insert(question);
        return "index";
    }
}
