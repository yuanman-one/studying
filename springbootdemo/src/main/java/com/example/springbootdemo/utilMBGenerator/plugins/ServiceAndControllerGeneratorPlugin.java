package com.example.springbootdemo.utilMBGenerator.plugins;


import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

public class ServiceAndControllerGeneratorPlugin extends PluginAdapter {

    // 项目目录，一般为 src/main/java
    private String targetProject;

    // service包名，如：com.example.springbootdemo.service
    private String servicePackage;

    // service实现类包名，如：com.example.springbootdemo.service.impl
    private String serviceImplPackage;
    // Controlle类包名，如：com.example.springbootdemo.controller
    private String controllerPackage;
    // service接口名前缀
    private String servicePreffix;

    // service接口名后缀
    private String serviceSuffix;

    // service接口的父接口
    private String superServiceInterface;

    // service实现类的父类
    private String superServiceImpl;
    // controller类的父类
    private String superController;

    // dao接口基类
    private String superDaoInterface;

    // Example类的包名
    private String examplePacket;

    private String recordType;

    private String modelName;

    private FullyQualifiedJavaType model;

    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private String daoFieldName;

    @Override
    public boolean validate(List<String> warnings) {
        boolean valid = true;
        targetProject = properties.getProperty("targetProject");
        servicePackage = properties.getProperty("servicePackage");
        serviceImplPackage = properties.getProperty("serviceImplPackage");
        servicePreffix = properties.getProperty("servicePreffix");
        servicePreffix = stringHasValue(servicePreffix) ? servicePreffix : "";
        serviceSuffix = properties.getProperty("serviceSuffix");
        serviceSuffix = stringHasValue(serviceSuffix) ? serviceSuffix : "";
        superServiceInterface = properties.getProperty("superServiceInterface");
        superServiceImpl = properties.getProperty("superServiceImpl");
        superDaoInterface = properties.getProperty("superDaoInterface");
        controllerPackage = properties.getProperty("controllerPackage");
        superController = properties.getProperty("superController");
        return valid;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        //获取实体类全类路径
        recordType = introspectedTable.getBaseRecordType();
        //实体类名
        modelName = recordType.substring(recordType.lastIndexOf(".") + 1);
        model = new FullyQualifiedJavaType(recordType);
        //拼接接口全类路径
        serviceName = servicePackage + "." + servicePreffix + modelName + serviceSuffix;
        //拼接实现类全类路径
        serviceImplName = serviceImplPackage + "." + modelName + serviceSuffix + "Impl";
        examplePacket = recordType.substring(0, recordType.lastIndexOf("."));
        controllerName = controllerPackage.concat(".").concat(modelName).concat("Controller");
        List<GeneratedJavaFile> answer = new ArrayList<>();
        GeneratedJavaFile gjf = generateServiceInterface(introspectedTable);
        GeneratedJavaFile gjf2 = generateServiceImpl(introspectedTable);
        GeneratedJavaFile gjf3 = generateController(introspectedTable);
        answer.add(gjf);
        answer.add(gjf2);
        answer.add(gjf3);
        return answer;
    }

    // 生成service接口
    private GeneratedJavaFile generateServiceInterface(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        Interface serviceInterface = new Interface(service);
        //添加注解
        serviceInterface.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        String remarks = introspectedTable.getRemarks();
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        String[] var5 = remarkLines;
        int var6 = remarkLines.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String remarkLine = var5[var7];
            serviceInterface.addJavaDocLine(" * " + remarkLine + "的服务接口");
        }
        sb.append(" * @mbg.generated 自动生成的标识，如修改请删除");
        serviceInterface.addJavaDocLine(sb.toString());
        serviceInterface.addJavaDocLine("*/");
        serviceInterface.setVisibility(JavaVisibility.PUBLIC);
        // 添加父接口
        if (stringHasValue(superServiceInterface)) {
            String superServiceInterfaceName = superServiceInterface.substring(superServiceInterface.lastIndexOf(".") + 1);
            serviceInterface.addImportedType(new FullyQualifiedJavaType(superServiceInterface));
            serviceInterface.addImportedType(new FullyQualifiedJavaType(recordType));
            serviceInterface.addSuperInterface(new FullyQualifiedJavaType(superServiceInterfaceName + "<" + modelName + ">"));
        }
        //添加依赖引入类
        serviceInterface.addImportedType(model);
        //添加方法
        addCustomMethod(null, serviceInterface);
        GeneratedJavaFile gjf = new GeneratedJavaFile(serviceInterface, targetProject, context.getJavaFormatter());
        return gjf;
    }

    // 生成serviceImpl实现类
    private GeneratedJavaFile generateServiceImpl(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        FullyQualifiedJavaType serviceImpl = new FullyQualifiedJavaType(serviceImplName);
        TopLevelClass clazz = new TopLevelClass(serviceImpl);
        //添加注解
        clazz.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        String remarks = introspectedTable.getRemarks();
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        String[] var5 = remarkLines;
        int var6 = remarkLines.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String remarkLine = var5[var7];
            clazz.addJavaDocLine(" * " + remarkLine + "的服务实现");
        }
        sb.append(" * <pre>\n");
        sb.append(" * 注意：\n");
        sb.append(" * 1. 查询数据库操作的方法，不用设置默认 @Transactional\n");
        sb.append(" *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS\n");
        sb.append(" *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED\n");
        sb.append(" * 2. 事务不会针对受控异常（checked exception）回滚\n");
        sb.append(" *    要想回滚事务，须抛出运行时异常(RuntimeException)\n");
        sb.append(" * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置\n");
        sb.append(" *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED\n");
        sb.append(" * </pre>\n");
        sb.append(" * @mbg.generated 自动生成的标识，如修改请删除");
        clazz.addJavaDocLine(sb.toString());
        clazz.addJavaDocLine("*/");

        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);
        //描述类 引入的类
        clazz.addImportedType(service);
        clazz.addImportedType(model);
        //描述类 的实现接口类
        clazz.addSuperInterface(service);
        if (stringHasValue(superServiceImpl)) {
            String superServiceImplName = superServiceImpl.substring(superServiceImpl.lastIndexOf(".") + 1);
            clazz.addImportedType(superServiceImpl);
            clazz.addImportedType(recordType);
            clazz.setSuperClass(superServiceImplName + "<" + modelName + ">");
        }
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Propagation"));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
        clazz.addAnnotation("@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)");
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        clazz.addAnnotation("@Service");

        String daoFieldType = introspectedTable.getMyBatis3JavaMapperType();
        this.daoFieldName = firstCharToLowCase(daoFieldType.substring(daoFieldType.lastIndexOf(".") + 1));
        //描述类的成员属性
        Field daoField = new Field(daoFieldName, new FullyQualifiedJavaType(daoFieldType));
        clazz.addImportedType(new FullyQualifiedJavaType(daoFieldType));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        //描述成员属性 的注解
        daoField.addAnnotation("@Autowired");
        //描述成员属性修饰符
        daoField.setVisibility(JavaVisibility.PRIVATE);
        clazz.addField(daoField);
        addCustomMethod(clazz, null);
        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, targetProject, context.getJavaFormatter());
        return gjf2;
    }


    // 生成controller类
    private GeneratedJavaFile generateController(IntrospectedTable introspectedTable) {

        FullyQualifiedJavaType controller = new FullyQualifiedJavaType(controllerName);
        TopLevelClass clazz = new TopLevelClass(controller);
        //描述类的作用域修饰符
        clazz.setVisibility(JavaVisibility.PUBLIC);
        //添加注解
        clazz.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        String remarks = introspectedTable.getRemarks();
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        String[] var5 = remarkLines;
        int var6 = remarkLines.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String remarkLine = var5[var7];
            clazz.addJavaDocLine(" * " + remarkLine);
        }
        sb.append(" * @mbg.generated 自动生成的标识，如修改请删除");
        clazz.addJavaDocLine(sb.toString());
        clazz.addJavaDocLine("*/");

        //添加@Controller注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
        clazz.addAnnotation("@RestController");
        //添加@RequestMapping注解，并引入相应的类
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
        clazz.addAnnotation("@RequestMapping(\"/" + firstCharToLowCase(modelName) + "\")");
        //添加@Api注解，并引入相应的类
//        clazz.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.Api"));
//        String controllerSimpleName = controllerName.substring(controllerName.lastIndexOf(".") + 1);
//        clazz.addAnnotation("@Api(tags = \"" + controllerSimpleName + "\", description = \"" + controllerSimpleName + "\")");

        //引入controller的父类和model，并添加泛型
        if (stringHasValue(superController)) {
            clazz.addImportedType(superController);
            clazz.addImportedType(recordType);
            FullyQualifiedJavaType superInterfac = new FullyQualifiedJavaType(superController + "<" + modelName + ">");
            clazz.addSuperInterface(superInterfac);
        }

        //引入Service
        FullyQualifiedJavaType service = new FullyQualifiedJavaType(serviceName);
        clazz.addImportedType(service);

        //添加Service成员变量
        String serviceFieldName = firstCharToLowCase(serviceName.substring(serviceName.lastIndexOf(".") + 1));
        Field daoField = new Field(serviceFieldName, new FullyQualifiedJavaType(serviceName));
        clazz.addImportedType(new FullyQualifiedJavaType(serviceName));
        clazz.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        //描述成员属性 的注解
        daoField.addAnnotation("@Autowired");
        //描述成员属性修饰符
        daoField.setVisibility(JavaVisibility.PRIVATE);
        clazz.addField(daoField);

        GeneratedJavaFile gjf2 = new GeneratedJavaFile(clazz, targetProject, context.getJavaFormatter());
        return gjf2;
    }


    /**
     * 给类或接口添加方法，传类则接口为null，反之接口为null
     *
     * @param clazz 类
     * @param inter 接口
     */
    public void addCustomMethod(TopLevelClass clazz, Interface inter) {
        addInsertMethod(clazz, inter);
        addInsertSelectiveMethod(clazz, inter);
        addUpdateByPrimaryKeyMethod(clazz, inter);
        addUpdateByPrimaryKeySelectiveMethod(clazz, inter);
        addDeleteByPrimaryKeyMethod(clazz, inter);
        addSelectByPrimaryKeyMethod(clazz, inter);
    }

    private void addInsertMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"insert"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(modelName), "mo"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType("int"));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                method.addAnnotation("@Transactional(readOnly = false, propagation = Propagation.REQUIRED)");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(mo);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    void addInsertSelectiveMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"insertSelective"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(modelName), "mo"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType("int"));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                method.addAnnotation("@Transactional(readOnly = false, propagation = Propagation.REQUIRED)");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(mo);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    void addSelectByPrimaryKeyMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"selectByPrimaryKey"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(Integer.class.getTypeName()), "id"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType(modelName));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(id);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"updateByPrimaryKeySelective"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(modelName), "mo"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType("int"));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                method.addAnnotation("@Transactional(readOnly = false, propagation = Propagation.REQUIRED)");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(mo);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    void addUpdateByPrimaryKeyMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"updateByPrimaryKey"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(modelName), "mo"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType("int"));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                method.addAnnotation("@Transactional(readOnly = false, propagation = Propagation.REQUIRED)");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(mo);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    void addDeleteByPrimaryKeyMethod(TopLevelClass clazz, Interface inter) {
        String[] methodName = {"deleteByPrimaryKey"};
        for (int i = 0; i < methodName.length; i++) {
            // 描述 方法名
            Method method = new Method(methodName[i]);
            //修饰符
            method.setVisibility(JavaVisibility.PUBLIC);
            //方法参数
            method.addParameter(new Parameter(new FullyQualifiedJavaType(Integer.class.getTypeName()), "id"));
            //返回值
            method.setReturnType(new FullyQualifiedJavaType("int"));
            this.addJavadocTag(method);
            if (inter == null) {
                //方法注解
                method.addAnnotation("@Override");
                method.addAnnotation("@Transactional(readOnly = false, propagation = Propagation.REQUIRED)");
                //方法体，逻辑代码
                method.addBodyLine("return " + daoFieldName + "." + methodName[i] + "(id);");
                clazz.addMethod(method);
            } else {
                inter.addMethod(method);
            }
        }
    }

    private void addJavadocTag(Method method) {
        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        if (method.getName().equals("deleteByPrimaryKey")) {
            sb.append(" * 根据ID删除记录 ");
        } else if (method.getName().equals("insert")) {
            sb.append(" * 添加完整字段记录 ");
        } else if (method.getName().equals("insertSelective")) {
            sb.append(" * 添加记录 ");
        } else if (method.getName().equals("selectByPrimaryKey")) {
            sb.append(" * 根据ID查询记录 ");
        } else if (method.getName().equals("updateByPrimaryKeySelective")) {
            sb.append(" * 根据ID修改记录 ");
        } else if (method.getName().equals("updateByPrimaryKey")) {
            sb.append(" * 根据ID修改完整字段记录 ");
        }
        sb.append("\n");
        sb.append(" * @mbg.generated 自动生成的标识，如修改请删除");
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine("*/");
    }

    private String firstCharToLowCase(String str) {
        char[] chars = new char[1];
        //String str="ABCDE1234";
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            return str.replaceFirst(temp, temp.toLowerCase());
        }
        return str;
    }
}
