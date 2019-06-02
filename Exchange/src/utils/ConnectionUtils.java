package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static ConnectionUtils ourInstance = new ConnectionUtils();

    public static ConnectionUtils getInstance() {
        return ourInstance;
    }

    private ConnectionUtils() {
    }

    public static Connection getConnection() {
        Connection dbconn = null;
        try {
            dbconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExchangeDB?serverTimezone=UTC", "root", "sandu");
            if (dbconn == null) {
                System.out.println("Connection not established");
            }
            else {
                System.out.println("Connection established");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbconn;
    }
}