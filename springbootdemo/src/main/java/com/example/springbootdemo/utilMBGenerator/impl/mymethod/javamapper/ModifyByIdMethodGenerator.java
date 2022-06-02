package com.example.springbootdemo.utilMBGenerator.impl.mymethod.javamapper;

import com.example.springbootdemo.utilMBGenerator.util.JavaTagUtil;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class ModifyByIdMethodGenerator extends UpdateByPrimaryKeySelectiveMethodGenerator {
    public ModifyByIdMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        //super.addInterfaceElements(interfaze);
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
        FullyQualifiedJavaType parameterType;
        if (this.introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(this.introspectedTable.getRecordWithBLOBsType());
        } else {
            parameterType = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        }

        importedTypes.add(parameterType);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        //method.setName(this.introspectedTable.getUpdateByPrimaryKeySelectiveStatementId());
        method.setName("modifyById");
        method.addParameter(new Parameter(parameterType, "record"));
        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        this.addMapperAnnotations(method);

        method.getJavaDocLines().clear();
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * 根据ID修改记录 ");
        method.addJavaDocLine(sb.toString());
        JavaTagUtil.addJavadocTag(method);
        method.addJavaDocLine(" */");

        if (this.context.getPlugins().clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, this.introspectedTable)) {
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
