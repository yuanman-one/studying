package com.example.springbootdemo.utilMBGenerator.plugins;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

/**
 * 给生成的实体类添加@Data注解，
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public Properties getProperties() {
        return super.getProperties();
    }

    //如果此方法返回false，则插件中不会再调用其他方法
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        //添加可能需要的校验依赖
        topLevelClass.addImportedType("javax.validation.constraints.NotBlank");
        topLevelClass.addImportedType("javax.validation.constraints.NotNull");
        topLevelClass.addImportedType("org.hibernate.validator.constraints.Length");
        topLevelClass.addImportedType("javax.validation.constraints.PositiveOrZero");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.clientInsertMethodGenerated(method, topLevelClass, introspectedTable);
    }
}
