package com.mds.DataBaseConnection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {

    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname = "users";
    private static Integer portnumber = 3306;
    private static String password = "SQL_mds_project";


    public static Connection getConnection() {
//        Connection connection = null;
//        MysqlDataSource dataSource = new MysqlDataSource();
//
//        dataSource.setServerName(servername);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        dataSource.setPortNumber(portnumber);
//        dataSource.setDatabaseName(dbname);
//
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Logger.getLogger("get Connection ->" + connection.getClass().getName()).log(Level.SEVERE, null, e);
//        }
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts?serverTimezone=UTC",
                    "root", "SQL_mds_project");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `users`");
//            System.out.println(statement.executeQuery("select * from `users`"));
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1)+ resultSet.getString(2));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}