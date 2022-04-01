package com.kdhy.login.mapper.customize;

import com.kdhy.login.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhanghuan
 * @Date 2022/3/30 23:24
 * @Description TODO
 */
public interface UserExtMapper {
    User login(@Param(value = "userName") String userName, @Param(value = "passWord") String password);

    int checkUserName(String userName);

    List<User> selectAll();
}
