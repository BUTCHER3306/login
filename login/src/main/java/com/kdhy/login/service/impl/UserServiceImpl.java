package com.kdhy.login.service.impl;

import com.kdhy.login.mapper.customize.UserExtMapper;
import com.kdhy.login.mapper.generate.UserMapper;
import com.kdhy.login.model.User;
import com.kdhy.login.service.UserService;
import com.kdhy.login.util.MD5;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Author zhanghuan
 * @Date 2022/3/30 23:18
 * @Description TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserExtMapper userExtMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        password = MD5.GetMD5Code(password);
        User user = userExtMapper.login(userName, password);
        return user;
    }

    @Override
    @Transactional
    public int addUser(User user) throws Exception {

        //添加用户之前，需要检验添加的用户是否存在
        String userName = user.getUserName();
        int count = userExtMapper.checkUserName(userName);
        if (count != 0){
            throw new Exception("当前用户已经存在");
        }
        user.setId(UUID.randomUUID().toString());
        user.setPassWord(MD5.GetMD5Code(user.getPassWord()));
        int result = userMapper.insertSelective(user);
        return result;
    }

    @Override
    public List<User> selectAll() {
        List<User> list = userExtMapper.selectAll();
        return list;
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @Transactional
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public int delete(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

}
