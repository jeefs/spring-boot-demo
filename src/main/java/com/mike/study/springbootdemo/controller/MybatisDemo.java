package com.mike.study.springbootdemo.controller;

import com.mike.study.springbootdemo.dao.UserDao;
import com.mike.study.springbootdemo.entity.User;
import com.mike.study.springbootdemo.validation.DeleteUserValidGroup;
import com.mike.study.springbootdemo.validation.InsertUserValidGroup;
import com.mike.study.springbootdemo.validation.UpdateUserValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MybatisDemo {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users/mybatis/queryAll")
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @PostMapping("/users/mybatis/insert")
    public Boolean insertUser(@RequestBody @Validated(InsertUserValidGroup.class) User user) {
        return userDao.insertUser(user) > 0;
    }

    @PostMapping("/users/mybatis/update")
    public Boolean updateUser(@RequestBody @Validated(UpdateUserValidGroup.class) User user) {
        return userDao.updateUser(user) > 0;
    }

    @PostMapping("/users/mybatis/delete")
    public Boolean deleteUser(@RequestBody @Validated(DeleteUserValidGroup.class) User user) {
        return userDao.deleteUser(user) > 0;
    }
}
