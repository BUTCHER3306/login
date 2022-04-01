package com.kdhy.login.service;

import com.kdhy.login.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhanghuan
 * @Date 2022/3/30 23:18
 * @Description TODO
 */
public interface UserService {

    User login(String userName, String password);

    int addUser(User user) throws Exception;

    List<User> selectAll();

    User getUserById(String id);

    int update(User user);

    int delete(String id);
}
