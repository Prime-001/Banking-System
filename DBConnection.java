package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String User = "root";
    private static final String Password = "Prem@2004";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,User, Password);
    }
}
