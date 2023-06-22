package javaResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB1 {
    private static final String JDBC_URL = "jdbc:mysql://172.16.20.89:3306/hanahr?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "hanaro";
    private static final String PASSWORD = "hanaro6666!";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
