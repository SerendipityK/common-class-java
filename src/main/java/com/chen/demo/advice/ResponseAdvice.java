package com.chen.demo.advice;

import com.chen.demo.common.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @RestControllerAdvice是@RestController注解的增强，可以实现三个方面的功能：
 * 1.全局异常处理
 * 2.全局数据绑定
 * 3.全局数据预处理
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 是否支持advice功能
     * true支持，false不支持
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 对返回的数据进行处理
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        /**
         * 这段代码一定要加，如果Controller直接返回String的话，SpringBoot是直接返回，故我们需要手动转换成json。
         */
        if (o instanceof String){
            return objectMapper.writeValueAsString(JsonResult.success(o));
        }
        //如果返回的结果是JsonResult对象，直接返回即可 避免对返回的异常结果进行再次封装
        /*
            {
            "status": 100,
            "message": "操作成功",
            "data": {
                      "status": 500,
                      "message": "/ by zero",
                      "data": null,
                      "timestamp": 1655199874263
                    },
            "timestamp": 1655199874268
            }
         */
        if (o instanceof JsonResult){
            return o;
        }
        return JsonResult.success(o);
    }
}
