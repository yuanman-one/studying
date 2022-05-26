package com.example.springbootdemo.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import sun.nio.cs.Surrogate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGeneratorUtil {

    public static void main(String[] args) {
        try {
            //dao层 执行过程中的警告信息
            List<String> warnings = new ArrayList<String>();
            //当生成的代码重复时，覆盖原代码
            boolean overwrite = true;
            //读取我们的dao配置文件
            InputStream is = Surrogate.Generator.class.getResourceAsStream("/generator/mybatis-generator.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            is.close();

            DefaultShellCallback callback = new MyDefaultShellCallback(overwrite);
            //创建
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            //执行生成代码
            myBatisGenerator.generate(null);
            //输出警告信息
            for (String warning : warnings) {
                System.out.println(warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



