package com.dong.usercenter.exception;

import com.dong.usercenter.common.result.ResponseStatus;

/**
 * @author dong
 * @since JDK1.8
 */
public class ServiceException extends RuntimeException {
    private final ResponseStatus responseStatus;

    public ServiceException(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
