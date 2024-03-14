package com.mike.study.springbootdemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@Slf4j  TODO 暂未接入日志系统

//全局异常处理器,用户校验失败时自动捕获异常信息
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        //log.error("全局异常信息 ex={}", e.getMessage(), e);
        System.out.println("全局异常信息 ex=" +  e.getMessage());
        return ResultData.fail(ReturnCode.RC500.getCode(),e.getMessage());
    }
}
