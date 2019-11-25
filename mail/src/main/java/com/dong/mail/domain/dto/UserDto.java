package com.dong.mail.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author dong
 * @since JDK1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    private int uid;
    /**
     * 用户名
     */
    @Pattern(regexp = "^[a-zA-Z]\\w{5,15}$")
    private String username;

    /**
     * 密码
     */
    @Pattern(regexp = "^[A-Z]\\w{7,15}$")
    private String password;

    /**
     * 手机
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone;

    /**
     * 性别 1 表示男 0 表示女
     */
    private Boolean sex;

    /**
     * 邮箱
     */
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;

    /**
     * 备注
     */
    private String mark;

}
