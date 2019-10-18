package com.gz.service;

import com.gz.po.User;

public interface UserService {
    public User findUser(String usercode, String password);
}
