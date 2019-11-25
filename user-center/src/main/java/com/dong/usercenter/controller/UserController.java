package com.dong.usercenter.controller;

import com.dong.usercenter.common.result.ResponseEntity;
import com.dong.usercenter.domain.dto.UserDto;
import com.dong.usercenter.domain.entity.User;
import com.dong.usercenter.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dong
 * @since JDK1.8
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public ResponseEntity verify(String username){
        ResponseEntity verify = userService.verify(username);
        return verify;
    }


    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseEntity register(@Validated @RequestBody UserDto userDto){
        ResponseEntity register = userService.register(userDto);
        return register;
    }


    @RequestMapping(value = "/active",method = RequestMethod.GET)
    //激活不能直接传递uid，不安全，所以用redis存储，传递key值,方法接收的参数要与前端传递的参数名一致
    public ResponseEntity activeUser(String code){
        ResponseEntity responseEntity = userService.activeUser(code);
        return responseEntity;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseEntity login(UserDto userDto){
        ResponseEntity login = userService.login(userDto);
        return login;
    }
}
