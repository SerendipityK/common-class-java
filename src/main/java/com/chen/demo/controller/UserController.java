package com.chen.demo.controller;


import com.chen.demo.pojo.User;
import com.chen.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


//以及如何日志的打印 log4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public User get(@RequestBody @Valid User user) {
        System.out.println("user");
        User a = userService.getUser(1);
        return user;
    }
}
