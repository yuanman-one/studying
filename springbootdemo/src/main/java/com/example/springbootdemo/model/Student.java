package com.example.springbootdemo.model;

import java.io.Serializable;
import lombok.Data;

/**
 *   学生信息表
 *
 * @author yuanman
 * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
 * @date 2022/05/27
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private Integer ID;

    /**
     *   姓名
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private String NAME;

    /**
     *   年龄
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private Integer AGE;

    /**
     *   性别 1男2女
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private Boolean SEX;

    /**
     *   身份证号
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private String ID_CARD;

    /**
     *   班级ID
     *
     * @mbg.generated 自动生成的标识，如修改请删除 Fri May 27 18:37:21 CST 2022
     */
    private Integer CLASS_ID;
}
