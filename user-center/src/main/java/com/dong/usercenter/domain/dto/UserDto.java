package com.dong.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dong
 * @since JDK1.8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    /**
     * 用户Id
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 1 表示男 0 表示女
     */
    private Boolean sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String mark;

    /**
     * 账号是否被锁定 1 表示未锁定 0 表示锁定
     */
    private Boolean locked;
}
