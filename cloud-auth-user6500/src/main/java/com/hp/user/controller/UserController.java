package com.hp.user.controller;

import com.hp.user.common.Result;
import com.hp.user.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-24 22:01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Result
     */
    @RequestMapping("/login")
    public Result login(@NotNull @RequestParam("username") String username, @NotNull @RequestParam("password") String password){
        //TODO 模拟数据库操作
        if ("admin".equals(username)&& "123456".equals(password)) {
            String token = JwtUtils.token();
            return Result.builder().code(200).msg("success").token(token).build();
        }else {
            return Result.builder().code(500).msg("用户名或密码不正确").build();
        }
    }
}
