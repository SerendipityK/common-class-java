package com.chen.demo.mapper;

import com.chen.demo.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectById(Integer id);
}
