package org.example;

import java.sql.*;

public class ConnectToSQLServer {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName = Dictionary_Data;" +
            "integratedSecurity = true;";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "219907";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int flag = -1;
        try {
            connection = ConnectToSQLServer.getConnection();
            preparedStatement = connection.prepareStatement
                    ("SELECT password FROM user_info WHERE username COLLATE Latin1_General_CS_AS =?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {//username khong dung
                flag = 0;
            } else {
                while (resultSet.next()) {
                    String pass = resultSet.getString("password");
                    if (pass.equals(password)) {//dang nhap thanh cong
                       flag = 2;
                    } else {//password khong dung
                        flag = 1;
                    }
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return flag;
    }
}