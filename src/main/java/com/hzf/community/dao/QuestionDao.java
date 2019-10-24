package com.hzf.community.dao;

import com.hzf.community.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao {
    void insert(Question question);
    List<Question> findAll();
}
