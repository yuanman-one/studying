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
     * 体重/kg
     */
    public Float weight;
    /**
     * 种类
     */
    public String species;
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

    public  Cat(String name, Integer age, String hairColor) {
        this.name = name;
        this.age = age;
        this.hairColor = hairColor;
    }

    public void add(Integer age){
        this.age=this.age+age;
    }
}
