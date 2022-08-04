package com.example.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuanman
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String xm;
    /**
     * 年龄
     */
    private String age;
    /**
     * 性别 1男2女
     */
    private String xb;
}
