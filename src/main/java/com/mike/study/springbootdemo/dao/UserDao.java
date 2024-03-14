package com.mike.study.springbootdemo.dao;

import com.mike.study.springbootdemo.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUsers();

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int deleteUser(User user);
}
