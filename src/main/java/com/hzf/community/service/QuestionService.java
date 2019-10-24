package com.hzf.community.service;

import com.hzf.community.dao.QuestionDao;
import com.hzf.community.dao.UserDao;
import com.hzf.community.dto.QuestionDTO;
import com.hzf.community.entity.Question;
import com.hzf.community.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionDao questionDao;
    public List<QuestionDTO> list(){
        List<QuestionDTO> list=new ArrayList<>();
        List<Question> questions = questionDao.findAll();
        for (Question question : questions) {
            User user = userDao.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        return list;
    }
}
