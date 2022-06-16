package com.chen.demo.controller;


import com.chen.demo.pojo.User;
import com.chen.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


// swagger2
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("打招呼")
    @GetMapping("/hello")
    public User get(@RequestBody @Valid User user) {
        System.out.println("user");
        User a = userService.getUser(1);
        return user;
    }
}
