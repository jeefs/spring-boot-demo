package com.mike.study.springbootdemo.entity;

import com.mike.study.springbootdemo.validation.DeleteUserValidGroup;
import com.mike.study.springbootdemo.validation.InsertUserValidGroup;
import com.mike.study.springbootdemo.validation.UpdateUserValidGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class User {

    @NotNull(groups = {UpdateUserValidGroup.class, DeleteUserValidGroup.class},message = "用户id不能为空!")
    private Integer id;
    @NotEmpty(groups = InsertUserValidGroup.class,message = "用户名称不能为空!")
    private String name;

    @NotNull(groups = InsertUserValidGroup.class,message = "用户年龄不能为空!")
    private Integer  age;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}





