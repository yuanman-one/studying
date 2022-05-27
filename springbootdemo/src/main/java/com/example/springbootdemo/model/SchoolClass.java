package com.example.springbootdemo.model;

import java.io.Serializable;
import lombok.Data;

/**
 *   班级表
 *
 * @author yuanman
 * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
 * @date 2022/05/27
 */
@Data
public class SchoolClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private Integer ID;

    /**
     *   班级名称
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private String CLASS_NAME;
}
