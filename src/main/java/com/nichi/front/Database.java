package com.nichi.front;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {

    private static final String URL="jdbc:mysql://192.168.1.92:3306/company";
    private static final String USERNAME="tanushkn";
    private static final String PASSWORD="Tanush@2304";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }


}
