
# 个人的学习笔记--springboot

- **示例代码地址**`https://github.com/yuanman-one/studying.git`
- **springbootdemo Project 使用JDK1.8版本**

##项目相关工具类
- 统一接口请求响应工具类`com.example.springbootdemo.base.util.ResponseUtil`
- 使用jackson的json工具类`com.example.springbootdemo.base.util.JsonUtil`

## mybatis-generator自动生成代码
- 引入相关依赖

        java源码解析器 javaparser-core、javaparser-symbol-solver-core
        MyBatis 生成器 mybatis-generator-core

- 项目代码所在包 `com.example.springbootdemo.utilMBGenerator`

- 自动生成启动类`com.example.springbootdemo.utilMBGenerator.MybatisGeneratorStartRun`

- 相关配置文件`src/main/resources/generator`

