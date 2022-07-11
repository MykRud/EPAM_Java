package final_task_servlet.main.java.com.finaltask.org.example.realization.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Custom connection pool that handle connections
 *
 * @see ConnectionCreator
 * @see Connection
 * @see Properties
 *
 * @author Misha Rudyk
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();

    private static MysqlDataSource dataSource = new MysqlDataSource();
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader("C:\\Users\\Keeper\\IdeaProjects\\Final_Task\\src\\main\\resources\\database.properties"));

            dataSource.setURL(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            LOGGER.error("Cannot find property file");
        }
    }

    /**
     * Create connection
     * @return Connection
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
