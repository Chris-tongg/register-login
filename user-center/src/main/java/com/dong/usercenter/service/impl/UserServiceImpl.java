package com.dong.usercenter.service.impl;

import com.dong.usercenter.common.result.ResponseEntity;
import com.dong.usercenter.common.result.ResponseStatus;
import com.dong.usercenter.common.utils.redis.RedisService;
import com.dong.usercenter.domain.dto.UserDto;
import com.dong.usercenter.domain.entity.User;
import com.dong.usercenter.mapper.UserMapper;
import com.dong.usercenter.message.MqMessageService;
import com.dong.usercenter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dong
 * @since JDK1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MqMessageService mqMessageService;
    @Resource
    private RedisService redisService;

    @Override
    public User findUserByName(String username) {
        User user = userMapper.findUserByName(username);
        return user;
    }

    @Override
    public ResponseEntity verify(String username) {
        User user = findUserByName(username);
        if (user != null) {
            //用户已存在
            return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_EXISTENT);
        } else {
            //用户不存在
            return ResponseEntity.success(ResponseStatus.ACCOUNT_NOT_EXIST);
        }
    }

    @Override
    public ResponseEntity register(UserDto userDto) {
        User user = findUserByName(userDto.getUsername());
        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(userDto, user);
            int count = userMapper.insert(user);
            if (count == 1) {
                //插入成功
                userDto.setUid(user.getUid());
                mqMessageService.sendRegisterMsg(userDto);
                return ResponseEntity.success(userDto);
            } else {
                //注册错误
                return ResponseEntity.error(ResponseStatus.ACCOUNT_REGISTER_ERROR);
            }
        } else {
            //用户已存在
            return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_EXISTENT);
        }
    }

    @Override
    public ResponseEntity activeUser(String token) {
        int count = 0;
        Object o = redisService.get(token);
        if (o != null) {
            count = userMapper.updateUser(Integer.parseInt(String.valueOf(o)));
            redisService.delete(token);
            if (count == 1) {
                return ResponseEntity.success(ResponseEntity.success());
            }else {
                //更新失败，参数无效，redis中存储的数据已过期
                return ResponseEntity.error(ResponseStatus.PARAMS_IS_INVALID);
            }
        } else {
            //更新失败，参数无效，redis中存储的数据已过期
            return ResponseEntity.error(ResponseStatus.PARAMS_IS_INVALID);
        }
    }

    /**
     * 1.用户是否存在
     * 2.密码是否正确
     * 3.用户是否激活
     * @param userDto
     * @return
     */
    @Override
    public ResponseEntity login(UserDto userDto) {
        User user = userMapper.findUserByName(userDto.getUsername());
        if (user!=null){
            //用户已存在
            if (userDto.getPassword().equals(user.getPassword())){
                //密码正确
                if (user.getLocked()==true){
                    //用户已激活，返回成功
                    return ResponseEntity.success(ResponseStatus.SUCCESS);
                }else {
                    //账号未激活，返回失败
                    return ResponseEntity.error(ResponseStatus.ACCOUNT_NOT_ACTIVE);
                }
            }else {
                //密码错误
                return ResponseEntity.error(ResponseStatus.ACCOUNT_LOGIN_ERROR);
            }
        }else {
            //用户不存在
            return ResponseEntity.error(ResponseStatus.ACCOUNT_NOT_EXIST);
        }
    }
}
