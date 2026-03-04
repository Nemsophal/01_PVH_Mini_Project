package db;

import util.CredentialsLoader;

import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static final Properties props = CredentialsLoader.loadProperties();

    private static final String URL = props.getProperty("db.url");
    private static final String USER = props.getProperty("db.user");
    private static final String PASS = props.getProperty("db.password");

    //prevent object creation
    private DBConnection() {}

    public static Connection getConnection() throws SQLException {

        if (URL == null || USER == null || PASS == null) {
            throw new SQLException("Database configuration missing in application.properties");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    //test conn
    public static void main(String[] args) throws SQLException {
        System.out.println("Testing connection...");
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Connection Successful!");
        } else {
            System.out.println("Connection Failed.");
        }
    }
}