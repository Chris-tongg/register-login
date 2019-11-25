package com.dong.usercenter.handler;

import com.dong.usercenter.common.result.ResponseEntity;
import com.dong.usercenter.common.result.ResponseStatus;
import com.dong.usercenter.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author dong
 * @since JDK1.8
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.error(ResponseStatus.SYSTEM_INNER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handlerServiceException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.error(ResponseStatus.BUSINESS_UNKNOW_ERROR);
    }
}
