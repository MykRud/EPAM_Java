package task_advanced.task_7;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static MysqlDataSource dataSource = new MysqlDataSource();
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileReader("src/task_advanced/task_7/resources/database.properties"));

            dataSource.setURL(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
