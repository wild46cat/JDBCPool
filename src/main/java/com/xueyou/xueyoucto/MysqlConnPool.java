package com.xueyou.xueyoucto;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by wuxueyou on 2017/2/23.
 */

public class MysqlConnPool {

    public static String url = "jdbc:mysql://localhost:3306/xytest";
    public static String username = "root";
    public static String password = "123456";

    private static final MysqlConnPool instance = new MysqlConnPool();
    private static ComboPooledDataSource comboPooledDataSource;

    static {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            ResourceBundle rsb = ResourceBundle.getBundle("config");
            MysqlConnPool.url = rsb.getString("mysql.url");
            MysqlConnPool.username = rsb.getString("mysql.user");
            MysqlConnPool.password = rsb.getString("mysql.password");
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(username);
            comboPooledDataSource.setPassword(password);
            //下面是设置连接池的一配置
            comboPooledDataSource.setMaxPoolSize(20);
            comboPooledDataSource.setMinPoolSize(5);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Connection getConnection() {
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

    private MysqlConnPool() {
    }

    public static MysqlConnPool getInstance() {
        return instance;
    }
}
