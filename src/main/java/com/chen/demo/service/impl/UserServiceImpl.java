package com.chen.demo.service.impl;

import com.chen.demo.enums.ReturnCodeEnum;
import com.chen.demo.exception.BusinessException;
import com.chen.demo.mapper.UserMapper;
import com.chen.demo.pojo.User;
import com.chen.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(Integer id) {
        User user = userMapper.selectById(id);
/*        if (1 > 0){
            throw new BusinessException(ReturnCodeEnum.MY_BUSINESS);
        }*/
        return user;
    }
}
