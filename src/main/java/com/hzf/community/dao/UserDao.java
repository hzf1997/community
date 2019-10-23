package com.hzf.community.dao;

import com.hzf.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {
    void insert(User user);
    User findByToken(String token);
}
