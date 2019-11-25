package com.dong.usercenter.service;

import com.dong.usercenter.common.result.ResponseEntity;
import com.dong.usercenter.domain.dto.UserDto;
import com.dong.usercenter.domain.entity.User;

/**
 * @author dong
 * @since JDK1.8
 */
public interface UserService {
    /**
     * 通过username查找User
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 验证用户是否存在
     * @param username
     * @return
     */
    ResponseEntity verify(String username);

    /**
     * 注册
     * @param userDto
     * @return
     */
    ResponseEntity register(UserDto userDto);
    /**
     * 激活用户
     */
    ResponseEntity activeUser(String token);
    /**
     * 登录
     */
    ResponseEntity login(UserDto userDto);
}
