package com.mofany.entities;

import lombok.*;

import java.io.Serializable;

/**
 * @author MoFany-J
 * @date 2023/2/14
 * @description Payment 主实体类
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    /**
     * 主键id字段
     * */
    private Long id;
    /**
     *
     * */
    private String serial;

    /**
     * 可序列化接口实现类必须有一个 serialVersionUID
     */
    private static final long serialVersionUID = -2322665254768493702L;
}
