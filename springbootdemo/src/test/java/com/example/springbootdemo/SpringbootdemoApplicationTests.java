//package com.example.springbootdemo;
//
//import com.example.springbootdemo.dao.StudentMapper;
//import com.example.springbootdemo.model.Student;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.io.InputStream;
//
//@SpringBootTest
//class SpringbootdemoApplicationTests {
//
//    @Resource
//    private StudentMapper studentMapper;
////    @Autowired
////    private StudentMapper studentMapper;
//    @Test
//    void contextLoads() throws IOException {
//        test01();
//    }
//
//    public SqlSessionFactory getSqlSessionFactory() throws IOException {
//        String resource = "mybatis/mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        return new SqlSessionFactoryBuilder().build(inputStream);
//    }
//
//    @Test
//    public void test01() throws IOException {
//       // SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        //SqlSession openSession = sqlSessionFactory.openSession();
//        try {
//            // 获取接口的实现类对象
//            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
//           // StudentMapper mapper = openSession.getMapper(StudentMapper.class);
//            Student student = studentMapper.selectByPrimaryKey(1);
//            System.out.println(student);
//        } finally {
//            //openSession.close();
//        }
//    }
//
//}
