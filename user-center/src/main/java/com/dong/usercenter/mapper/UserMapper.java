package com.dong.usercenter.mapper;

import com.dong.usercenter.domain.entity.User;

/**
 
 * @author dong
 
 * @since JDK1.8

 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByName(String username);

    Integer updateUser(Integer uid);

}