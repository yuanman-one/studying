package com.example.springbootdemo.utilMBGenerator.impl.mymethod.javamapper;

import com.example.springbootdemo.utilMBGenerator.util.JavaTagUtil;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

/**
 * 修改默认的insert方法名为add
 */
public class AddMethodGenerator extends InsertMethodGenerator {
    private boolean isSimple;

    public AddMethodGenerator(boolean isSimple) {
        super(isSimple);

    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        // super.addInterfaceElements(interfaze);
        Method method = new Method();
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        //method.setName(this.introspectedTable.getInsertStatementId());
        method.setName("add");
        FullyQualifiedJavaType parameterType;
        if (this.isSimple) {
            parameterType = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        } else {
            parameterType = this.introspectedTable.getRules().calculateAllFieldsClass();
        }

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));
        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        this.addMapperAnnotations(method);
        //注解
        method.getJavaDocLines().clear();
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * 添加记录 ");
        method.addJavaDocLine(sb.toString());
        JavaTagUtil.addJavadocTag(method);
        method.addJavaDocLine(" */");
        //插件
        if (this.context.getPlugins().clientInsertMethodGenerated(method, interfaze, this.introspectedTable)) {
            this.addExtraImports(interfaze);
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }

    }

    @Override
    public void addMapperAnnotations(Method method) {
        super.addMapperAnnotations(method);
    }

    @Override
    public void addExtraImports(Interface interfaze) {
        super.addExtraImports(interfaze);
    }
}
