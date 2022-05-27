package com.example.springbootdemo.utilMBGenerator.impl;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.util.List;

public class MyIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {
    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings, ProgressCallback progressCallback) {
        super.calculateXmlMapperGenerator(javaClientGenerator, warnings, progressCallback);
    }

    @Override
    protected void calculateJavaClientAttributes() {
        super.calculateJavaClientAttributes();


    }

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        return super.calculateMyBatis3XmlMapperFileName();

    }
}
