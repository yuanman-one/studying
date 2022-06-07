package com.example.springbootdemo.utilMBGenerator.impl.mymethod.javamapper;

import com.example.springbootdemo.utilMBGenerator.util.JavaTagUtil;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class CountMethodGenerator extends AbstractJavaMapperMethodGenerator {
    public CountMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
        importedTypes.add(fqjt);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("long"));
        method.setName("count");
        method.addParameter(new Parameter(fqjt, "record"));
        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        this.addMapperAnnotations(method);

        method.getJavaDocLines().clear();
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * 统计记录 ");
        method.addJavaDocLine(sb.toString());
        JavaTagUtil.addJavadocTag(method);
        method.addJavaDocLine(" */");

        this.addExtraImports(interfaze);
        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    public void addMapperAnnotations(Method method) {
    }

    public void addExtraImports(Interface interfaze) {
    }

}
