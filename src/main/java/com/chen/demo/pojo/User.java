package com.chen.demo.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


@Data
public class User {
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Max(value = 150,message = "年龄不能超过150岁")
    private Integer age;
}
