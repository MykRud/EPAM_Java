package task_advanced.task_7;

import task_advanced.task_7.DAOs.DAO;
import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.OutOfDateClasses.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private Connection connection;

    public void initTransaction(DAO ... daos) throws DAOException{
        try {
            if(connection == null) {
                connection = ConnectionCreator.createConnection();
            }
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        for(DAO dao : daos){
            dao.setConnection(connection);
        }
    }

    public void endTransaction() throws DAOException{
        try{
            if(connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        connection = null;
    }

    public void init(DAO dao) throws DAOException{
        try {
            if(connection == null) {
                connection = ConnectionPool.createConnection();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        dao.setConnection(connection);
    }

    public void end() throws DAOException{
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        connection = null;
    }

    public void commit() throws DAOException{
        try{
            if(connection != null) {
                connection.commit();
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    public void rollback() throws DAOException{
        try{
            if(connection != null) {
                connection.rollback();
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }
}
