package final_task_servlet.realization.dao;

import com.finaltask.org.example.realization.dao.interfaces.CommonDao;
import com.finaltask.org.example.realization.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private static final Logger LOGGER = LogManager.getLogger();
    private Connection connection;

    @SafeVarargs
    public final void initTransaction(CommonDao<? extends Entity>... daos) throws DaoException{
        try {
            if(connection == null) {
                connection = ConnectionCreator.createConnection();
            }
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Cannot initialize transaction");
        }

        for(CommonDao<? extends Entity> dao : daos){
            dao.setConnection(connection);
        }
    }

    public void endTransaction() throws DaoException{
        try{
            if(connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e){
            LOGGER.error("Cannot end transaction");
        }
        connection = null;
    }

    public void init(CommonDao<? extends Entity> dao) throws DaoException{
        try {
            if(connection == null) {
                connection = ConnectionPool.createConnection();
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot initialize transaction");
        }
        dao.setConnection(connection);
    }

    public void end() throws DaoException{
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot end transaction");
            }
        }
        connection = null;
    }

    public void commit() throws DaoException{
        try{
            if(connection != null) {
                connection.commit();
            }
        } catch (SQLException e){
            LOGGER.error("Cannot commit");
        }
    }

    public void rollback() throws DaoException{
        try{
            if(connection != null) {
                connection.rollback();
            }
        } catch (SQLException e){
            LOGGER.error("Cannot rollback");
        }
    }

}
