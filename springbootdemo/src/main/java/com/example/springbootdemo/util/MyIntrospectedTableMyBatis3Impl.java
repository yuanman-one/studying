package com.example.springbootdemo.util;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.text.MessageFormat;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

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
