package com.example.springbootdemo.utilMBGenerator.impl.mymethod.javamapper;

import com.example.springbootdemo.utilMBGenerator.util.JavaTagUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DeleteByIdMethodGenerator extends DeleteByPrimaryKeyMethodGenerator {

    private boolean isSimple;

    public DeleteByIdMethodGenerator(boolean isSimple) {
        super(isSimple);
        this.isSimple = isSimple;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        //super.addInterfaceElements(interfaze);
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        //method.setName(this.introspectedTable.getDeleteByPrimaryKeyStatementId());
        method.setName("deleteById");
        if (!this.isSimple && this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
            importedTypes.add(type);
            method.addParameter(new Parameter(type, "key"));
        } else {
            List<IntrospectedColumn> introspectedColumns = this.introspectedTable.getPrimaryKeyColumns();
            boolean annotate = introspectedColumns.size() > 1;
            if (annotate) {
                importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
            }

            StringBuilder sb = new StringBuilder();

            Parameter parameter;
            for(Iterator var7 = introspectedColumns.iterator(); var7.hasNext(); method.addParameter(parameter)) {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn)var7.next();
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
                parameter = new Parameter(type, introspectedColumn.getJavaProperty());
                if (annotate) {
                    sb.setLength(0);
                    sb.append("@Param(\"");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append("\")");
                    parameter.addAnnotation(sb.toString());
                }
            }
        }

        this.context.getCommentGenerator().addGeneralMethodComment(method, this.introspectedTable);
        this.addMapperAnnotations(method);

        method.getJavaDocLines().clear();
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * 根据ID删除记录 ");
        method.addJavaDocLine(sb.toString());
        JavaTagUtil.addJavadocTag(method);
        method.addJavaDocLine(" */");

        if (this.context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, this.introspectedTable)) {
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
