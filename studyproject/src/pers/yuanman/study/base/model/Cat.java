package pers.yuanman.study.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 猫
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    /**
     * 名字
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 毛色
     */
    private String hairColor;
}
