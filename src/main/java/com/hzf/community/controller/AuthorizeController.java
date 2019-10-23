package com.hzf.community.controller;

import com.hzf.community.dto.AccessTokenDTO;
import com.hzf.community.dto.GithubUser;
import com.hzf.community.github.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController{
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpSession session){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id("e2b1023513a7875233ef");
        accessTokenDTO.setClient_secret("5423af955975e421d544f8704a2b4abce9146492");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        String access_token = githubProvider.postData(accessTokenDTO);
        GithubUser user = githubProvider.getData(access_token);
        session.setAttribute("user",user);
        return "index";
    }

}
