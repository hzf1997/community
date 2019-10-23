package com.hzf.community.controller;

import com.hzf.community.dao.UserDao;
import com.hzf.community.dto.AccessTokenDTO;
import com.hzf.community.dto.GithubUser;
import com.hzf.community.entity.User;
import com.hzf.community.github.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController{
    @Autowired
    private UserDao userDao;
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.clientid}")
    private String client_id;
    @Value("${github.clientsecret}")
    private String client_secret;
    @Value("${github.redirecturi}")
    private String client_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpSession session, HttpServletResponse response){

        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(client_uri);
        String access_token = githubProvider.postData(accessTokenDTO);
        GithubUser githubUser = githubProvider.getData(access_token);
        User user =new User();
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setName(githubUser.getName());
        user.setToken(UUID.randomUUID().toString());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
       // System.out.println(user);
        userDao.insert(user);
        response.addCookie(new Cookie("token",user.getToken()));
        //session.setAttribute("user",user);
        return "redirect:/";
    }

}
