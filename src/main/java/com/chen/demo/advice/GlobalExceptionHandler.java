package com.chen.demo.advice;

import com.chen.demo.common.JsonResult;
import com.chen.demo.enums.ReturnCodeEnum;
import com.chen.demo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

// @RestControllerAdvice，RestController的增强类，可用于实现全局异常处理器
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // @ExceptionHandler,统一处理某一类异常，从而减少代码重复率和复杂度，
    // 比如要获取自定义异常可以@ExceptionHandler(BusinessException.class)
    @ExceptionHandler(Exception.class)
    // @ResponseStatus指定客户端收到的http状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<String> doException(Exception e){
        // 业务异常需要记录日志
        log.error("Exception:{}",e.getMessage(),e);
        return JsonResult.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
    }

    // 业务异常不需要记录日志
    @ExceptionHandler(BusinessException.class)
    public JsonResult<String> doBusinessException(BusinessException e){
        return JsonResult.fail(e.getCode(),e.getMsg());
    }

    // 参数校验@Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<String> doMethodArgumentNotValidException(MethodArgumentNotValidException e){
        StringBuffer sb = new StringBuffer();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (int i=0;i<errors.size();i++){
                sb.append(errors.get(i).getDefaultMessage());
                if (i != errors.size() - 1){
                    sb.append(",");
                }
            }
        }
        return JsonResult.fail(ReturnCodeEnum.PARAM_ERROR.getCode(),sb.toString());
    }

}
