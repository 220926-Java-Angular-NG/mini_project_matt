package com.revature.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManager {
    private static Connection conn;
    private static Properties properties;

    private ConnManager(){}

    public static Connection getConn() throws SQLException {
        if (properties==null){
            properties = loadProperties();
        }

        if (conn == null || conn.isClosed()){
            conn = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
        }

        return conn;
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/jdbc.properties");
            properties.load(fileInputStream);
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

}
