package com.lingyun.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResult
 * @Description TODO
 * @Author LingYun
 * @Date 2022/2/27 9:48
 * @Version
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
    public static CommonResult ok(){
        return new CommonResult(200,"success");
    }
    public static CommonResult ok(String msg){
        return new CommonResult(200,msg);
    }
    public static <T> CommonResult<T> ok(T data){
        return new CommonResult(200,"success",data);
    }

    public static CommonResult error(){
        return new CommonResult(500,"error");
    }

    public static CommonResult error(String msg){
        return new CommonResult(500,msg);
    }

}
