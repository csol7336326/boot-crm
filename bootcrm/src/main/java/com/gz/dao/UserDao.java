package com.gz.dao;

import com.gz.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User findUser(@Param("usercode") String usercode, @Param("password") String password);
}
