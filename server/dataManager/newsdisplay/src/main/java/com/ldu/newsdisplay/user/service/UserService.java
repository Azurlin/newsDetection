package com.ldu.newsdisplay.user.service;

import com.ldu.newsdisplay.user.domain.User;

import java.util.List;

public interface UserService {
    List<User> queryUser(User user);

    Integer insertUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User queryUserByUserName(String userName,String userPassword);
}
