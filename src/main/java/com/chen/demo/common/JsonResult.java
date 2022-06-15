package com.chen.demo.common;

import com.chen.demo.enums.ReturnCodeEnum;
import lombok.Data;

@Data
public class JsonResult<T> {

    /**
     * status 状态值：由后端统一定义各种返回结果的状态码
     */
    private Integer status;

    /**
     * message 描述：本次接口调用的结果描述
     */
    private String message;

    /**
     * data 数据：本次返回的数据。
     */
    private T data;

    /**
     * 按需加入其他扩展值，比如我们就在返回对象中添加了接口调用时间
     */
    private Long timestamp;


    public JsonResult(){
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功（带数据）
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(T data){
        JsonResult<T> result = new JsonResult<>();
        result.setStatus(ReturnCodeEnum.RC100.getCode());
        result.setMessage(ReturnCodeEnum.RC100.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> JsonResult<T> fail(Integer code,String message){
        JsonResult<T> result = new JsonResult<>();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }


}
