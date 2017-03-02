package com.xueyou.xueyoucto;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Connection connection = MysqlConnPool.getInstance().getConnection();
        ResultSet rs = MysqlHelper.executeQuery(connection, "select * from user");
        try {
            if (rs.next()) {
                String str = rs.getString(2);
                System.out.println(str);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        //这里也可以继续使用connection这个连接，只要上面不关闭即可
        Connection connection1 = MysqlConnPool.getInstance().getConnection();
        int exeCount = 0;
        try {
            exeCount = MysqlHelper.executeUpdate(connection1, "update user set age = 21 where id = 2");
            connection1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("受影响的行数为：" + exeCount);
        /*ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement("select * from user");
            rs = ps.executeQuery();
            if (rs.next()) {
                String s = rs.getString(2);
                System.out.println(s);
            }
            connection.close();
            connection = MysqlConnPool.getInstance().getConnection();
            ps = connection.prepareStatement("select * from product");
            rs = ps.executeQuery();
            if(rs.next()){
                String s = rs.getString(2);
                System.out.println(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
