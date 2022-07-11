package final_task_servlet.main.test.connection;

import com.finaltask.org.example.realization.dao.ConnectionPool;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;

public class ConnectionPoolTest {

    @Test
    public void testConnection() throws SQLException {
        Connection connection = ConnectionPool.createConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}
