package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kataschema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

     public static Connection getConnection() {
         Connection connection = null;
        try  {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            if (!connection.isClosed()) {
                System.out.println("Подключение прошло успешно!");
            }
        } catch (SQLException e) {
            System.out.println("Не получилось подключиться.");
        }
        return connection;
    }

}
