package com.kdhy.login.controller;

import com.kdhy.login.model.User;
import com.kdhy.login.service.UserService;
import com.kdhy.login.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author zhanghuan
 * @Date 2022/3/30 23:14
 * @Description TODO
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/index")
    public String index(){
        return "login";
    }

    @RequestMapping(value = "/getAll" , method = RequestMethod.GET)
    public String getAll(Model model){
        List<User> users = userService.selectAll();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password , HttpServletRequest request) {
        if ("".equals(userName.trim()) || "".equals(password.trim())) {
            return "请输入正确的用户名和密码";
        }
        User user = userService.login(userName, password);
        if (user == null) {
            return "登录失败";
        }

        if (user.getStatus() != 0){
            return "该用户状态已经被禁用";
        }
        String ip = IpUtil.getIpAddr(request);
        if (!user.getLoginIp().contains("127.0.0.1") && !user.getLoginIp().contains(ip)) {
            return "该用户不能在此设备登录";
        }
        return "redirect:/getAll";
    }

    @RequestMapping(value = "/private/addUser", method = RequestMethod.POST)
    public String addUser(User user) throws Exception {
        int result = userService.addUser(user);
        if (result != 1){
            return "新增用户失败";
        }
        return "redirect:/getAll";
    }

    @RequestMapping(value = "/private/toUpdate" , method = RequestMethod.GET)
    public String toUpdate(String id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/private/update" , method = RequestMethod.POST)
    public String update(User user) {
        int count  = userService.update(user);
        if (count != 1){
            return "修改失败";
        }
        return "redirect:/getAll";
    }

    @RequestMapping(value = "/private/delete")
    public String delete(String id){
        int count = userService.delete(id);
        if (count != 1){
            return "删除失败";
        }
        return "redirect:/getAll";
    }

    @RequestMapping(value = "/private/toAdd")
    public String toAdd(){
        return "addUser";
    }
}
