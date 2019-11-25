package com.dong.usercenter.common.result;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangwei
 * data
 */
@Data
@Builder
public class ErrorResponseEntity implements BaseResponseEntity {
    /**
     * HTTP响应状态码 {@link org.springframework.http.HttpStatus}
     */
    private Integer status;

    /**
     * 前端显示的信息
     */
    private String msg;
    /**
     * 错误的信息
     */
    private String error;
    /**
     * 异常的名字
     */
    private String exception;
    /**
     * 请求路径
     */
    private String path;

    /**
     * 时间戳
     */
    private Date timestamp;


    public static ErrorResponseEntity failure(ResponseStatus responseStatus, Throwable e) {
        return ErrorResponseEntity.builder()
                .msg(responseStatus.getMsg())
                .status(responseStatus.getStatus())
                .error(e.getMessage())
                .timestamp(new Date())
                .exception(e.getClass().getName())
                .build();
    }

}
