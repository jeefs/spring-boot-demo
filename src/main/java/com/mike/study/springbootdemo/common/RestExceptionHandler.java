package com.mike.study.springbootdemo.common;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Map;


//@Slf4j  TODO 暂未接入日志系统

//全局异常处理器,用户校验失败时自动捕获异常信息
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 处理方法的普通参数异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<Map<String, String>> exception(ConstraintViolationException e) {
        //Map<String, String> errorMap = new HashMap<>();
        String errorMapStr = "";
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            String field = "";
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                field = node.getName();
            }
            //errorMap.put(field, constraintViolation.getMessage());
            errorMapStr += field + ":" + constraintViolation.getMessage()  + ",";
        }
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(),errorMapStr);
    }

    /**
     * 处理方法的实体参数异常
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<Map<String, String>> exception(BindException e) {
        //Map<String, String> errorMap = new HashMap<>();
        //e.getBindingResult().getFieldErrors().forEach(r -> errorMap.put(r.getField(), r.getDefaultMessage()));
        String errorMapStr = "";
        for (FieldError r: e.getBindingResult().getFieldErrors()) {
            errorMapStr += r.getField() + ":" + r.getDefaultMessage()  + ",";
        }
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), errorMapStr.trim());
    }
    /**
     * 处理方法的参数格式异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<String> exception(HttpMessageNotReadableException e) {
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), e.getMessage());
    }



    /**
     * 处理方法的参数缺失
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<String> exception(MissingServletRequestPartException e) {
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), e.getMessage());
    }


    /**
     * 处理方法的参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<String> exception(MissingServletRequestParameterException e) {
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理方法的参数类型不匹配异常
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<String> exception(MethodArgumentTypeMismatchException e) {
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), e.getMessage());
    }
    /**
     * 处理方法的参数类型转换异常
     */

    @ExceptionHandler(value = MethodArgumentConversionNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultData<String> exception(MethodArgumentConversionNotSupportedException e) {
        return ResultData.fail(ReturnCode.PARAMETER_ERROR.getCode(), e.getMessage());
    }



    /**
     * 全局异常处理。
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
