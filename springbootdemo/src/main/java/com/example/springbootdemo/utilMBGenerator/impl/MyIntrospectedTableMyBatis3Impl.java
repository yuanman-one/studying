package com.example.springbootdemo.utilMBGenerator.impl;

import com.example.springbootdemo.utilMBGenerator.impl.mymethod.MyXMLMapperGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.javamapper.AnnotatedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.MixedClientGenerator;
import org.mybatis.generator.internal.ObjectFactory;

import java.util.List;

public class MyIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {
    public MyIntrospectedTableMyBatis3Impl() {
        super();
    }

    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings, ProgressCallback progressCallback) {
        //super.calculateXmlMapperGenerator(javaClientGenerator, warnings, progressCallback);
        if (javaClientGenerator == null) {
            if (context.getSqlMapGeneratorConfiguration() != null) {
                xmlMapperGenerator = new MyXMLMapperGenerator();
            }
        } else {
            xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
            //xmlMapperGenerator = new MyXMLMapperGenerator();
        }

        initializeAbstractGenerator(xmlMapperGenerator, warnings,
                progressCallback);
    }

    @Override
    protected void calculateJavaClientAttributes() {
        super.calculateJavaClientAttributes();
    }

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        return super.calculateMyBatis3XmlMapperFileName();

    }

    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        //return super.createJavaClientGenerator();
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }

        String type = context.getJavaClientGeneratorConfiguration()
                .getConfigurationType();

        AbstractJavaClientGenerator javaGenerator;
        if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new JavaMapperGenerator();
        } else if ("MIXEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new MixedClientGenerator();
        } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new AnnotatedClientGenerator();
        } else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new JavaMapperGenerator();
        } else {
            javaGenerator = (AbstractJavaClientGenerator) ObjectFactory
                    .createInternalObject(type);
        }

        return javaGenerator;
    }


    @Override
    public void calculateGenerators(List<String> warnings, ProgressCallback progressCallback) {
        super.calculateGenerators(warnings, progressCallback);
    }

    @Override
    protected AbstractJavaClientGenerator calculateClientGenerators(List<String> warnings, ProgressCallback progressCallback) {
        return super.calculateClientGenerators(warnings, progressCallback);
    }

    @Override
    protected void calculateJavaModelGenerators(List<String> warnings, ProgressCallback progressCallback) {
        super.calculateJavaModelGenerators(warnings, progressCallback);
    }

    @Override
    protected void initializeAbstractGenerator(AbstractGenerator abstractGenerator, List<String> warnings, ProgressCallback progressCallback) {
        super.initializeAbstractGenerator(abstractGenerator, warnings, progressCallback);
    }

    @Override
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        return super.getGeneratedJavaFiles();
    }

    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        return super.getGeneratedXmlFiles();
    }

    @Override
    public int getGenerationSteps() {
        return super.getGenerationSteps();
    }

    @Override
    public boolean isJava5Targeted() {
        return super.isJava5Targeted();
    }

    @Override
    public boolean requiresXMLGenerator() {
        return super.requiresXMLGenerator();
    }
}
