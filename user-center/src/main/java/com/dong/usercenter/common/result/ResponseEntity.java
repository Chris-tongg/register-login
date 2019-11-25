package com.dong.usercenter.common.result;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ControllerAdvice、ResponseBodyAdvice这两个类是本功能的关键使用类，
 * 用于接口的响应体增强，
 * 其中supports方法用于判断是否需要做增强转化，beforeBodyWrite方法用于增加逻辑实现
 *
 * @author zhangwei
 */
@Builder
@Data
public class ResponseEntity<Object> implements BaseResponseEntity {
    private Integer status;

    private String msg;

    private Object data;


    public static ResponseEntity success() {
        return ResponseEntity
                .builder()
                .status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(T data) {
        return ResponseEntity
                .builder()
                .data(data)
                .status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(ResponseStatus responseStatus, T data) {
        return ResponseEntity
                .builder()
                .data(data)
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    /**
     * @param status
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity success(int status, String msg, T data) {
        return ResponseEntity
                .builder()
                .data(data)
                .status(status)
                .msg(msg)
                .build();
    }

    /*=============错误信息处理=================*/

    /**
     * 框架自带的状态码 org.springframework.http.HttpStatus
     *
     * @param
     * @return
     */

    public static ResponseEntity error() {
        return ResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }


    public static ResponseEntity error(String message) {
        return ResponseEntity
                .builder()
                .msg(message)
                .build();
    }

    public static ResponseEntity error(int status) {
        return ResponseEntity
                .builder()
                .status(status)
                .build();
    }

    public static ResponseEntity error(int status, String message) {
        return ResponseEntity
                .builder()
                .msg(message)
                .status(status)
                .build();
    }


    public static ResponseEntity error(ResponseStatus responseStatus) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    public static <T> ResponseEntity error(ResponseStatus responseStatus, T data) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .data(data)
                .build();
    }

}
