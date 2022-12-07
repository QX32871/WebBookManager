package com.QX32871.Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 这个工具类用于初始化Mybatis，创建SqlSession对象,用于对数据库的增删改查等操作
 */
public class MybatisUtil {
    private static SqlSessionFactory factory;

    //在这里使用静态代码块在类加载的时候就初始化对象和读取mybatis配置文件
    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取SQLSession
    public static SqlSession getSession() {
        //从factory里面直接获取SqlSession
        return factory.openSession(true);
    }
}
