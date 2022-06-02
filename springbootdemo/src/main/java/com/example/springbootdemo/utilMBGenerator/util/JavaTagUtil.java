package com.example.springbootdemo.utilMBGenerator.util;

import org.mybatis.generator.api.dom.java.JavaElement;

public class JavaTagUtil {
    public static void addJavadocTag(JavaElement javaElement ) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@mbg.generated 自动生成的标识，如修改请删除");
        javaElement.addJavaDocLine(sb.toString());
    }
}
