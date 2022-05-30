package com.example.springbootdemo.utilMBGenerator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

public class SerializablePlugin extends PluginAdapter {
    private FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
    private boolean isSerializable;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.isSerializable=Boolean.valueOf(properties.getProperty("isSerializable"));
    }

    //如果此方法返回false，则插件中不会再调用其他方法
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setInitializationString("1L");
        field.getJavaDocLines().add(" ");
        //确保序列化字段在第一个
        topLevelClass.getFields().add(0, field);
        topLevelClass.addImportedType(this.serializable);
        topLevelClass.addSuperInterface(this.serializable);

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }
}
