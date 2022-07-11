package final_task_servlet.main.java.com.finaltask.org.example.realization.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Create connection from property file
 * @see Connection
 * @see DriverManager
 *
 * @author Misha Rudyk
 */
public class ConnectionCreator {
    private static final Properties properties = new Properties();

    /**
     * Gets properties
     */
    private static final String DATABASE_URL;
    static {
        try {
            properties.load(new FileReader("C:\\Users\\Keeper\\IdeaProjects\\Final_Task\\src\\main\\resources\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        DATABASE_URL = (String) properties.get("db.url");
    }
    private ConnectionCreator(){}

    /**
     * Method that return connection
     * @return Connection
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
