package com.mofany.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author MoFany-J
 * @date 2023/2/14
 * @description CommonResult Json封装体类
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
    /**
     * 可序列化接口实现类必须有一个 serialVersionUID
     */
    private static final long serialVersionUID = -5161884966657763669L;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
