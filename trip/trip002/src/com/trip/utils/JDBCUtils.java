package com.trip.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC的工具类 使用的是Druid连接池
 */
public class JDBCUtils {
    public static DataSource dataSource;
    /**
     * 1.加载配置文件
     * 2.初始化连接池
     */
    static {
        //1.加载配置文件
        Properties properties = new Properties();
        //2.使用ClassLoader加载配置文件，获取字节输入流
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        System.out.println(resourceAsStream);
        try {
            properties.load(resourceAsStream);
            //3.初始化连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 获取Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
