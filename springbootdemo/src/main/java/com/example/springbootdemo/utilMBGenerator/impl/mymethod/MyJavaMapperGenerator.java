package com.example.springbootdemo.utilMBGenerator.impl.mymethod;

import com.example.springbootdemo.utilMBGenerator.impl.mymethod.javamapper.*;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class MyJavaMapperGenerator extends JavaMapperGenerator {
    public MyJavaMapperGenerator() {
        super();
    }

    public MyJavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
        super(requiresMatchedXMLGenerator);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        //return super.getCompilationUnits();
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3JavaMapperType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);
        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
                    rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }
        //自定义方法
        addAddMethod(interfaze);
        addModifyByIdMethod(interfaze);
        addDeleteByIdMethod(interfaze);
        addGetByIdMethod(interfaze);
        addCountMethod(interfaze);
        addCountByExampleMethod(interfaze);
//        addDeleteByExampleMethod(interfaze);
        //addDeleteByPrimaryKeyMethod(interfaze);
        //addInsertMethod(interfaze);
        //addInsertSelectiveMethod(interfaze);
//        addSelectByExampleWithBLOBsMethod(interfaze);
//        addSelectByExampleWithoutBLOBsMethod(interfaze);
        //addSelectByPrimaryKeyMethod(interfaze);
//        addUpdateByExampleSelectiveMethod(interfaze);
//        addUpdateByExampleWithBLOBsMethod(interfaze);
//        addUpdateByExampleWithoutBLOBsMethod(interfaze);
        // addUpdateByPrimaryKeySelectiveMethod(interfaze);
//        addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
//        addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().clientGenerated(interfaze, null,
                introspectedTable)) {
            answer.add(interfaze);
        }

        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }

    private void addCountMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new CountMethodGenerator();
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addDeleteByIdMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByIdMethodGenerator(false);
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addModifyByIdMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new ModifyByIdMethodGenerator();
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }


    private void addAddMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new AddMethodGenerator(false);
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    private void addGetByIdMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new GetByIdMethodGenerator(false);
        this.initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        // return super.getMatchedXMLGenerator();
        return new MyXMLMapperGenerator();
    }


}
