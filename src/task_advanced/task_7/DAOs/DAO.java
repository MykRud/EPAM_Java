package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Entity;
import task_advanced.task_7.EntityTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DAO<K, L extends Entity> {
    protected Connection connection;
    public static final EntityTransaction transaction = new EntityTransaction();
    public static final ActorDAO actorDAO = new ActorDAO();
    public static final MovieDAO movieDAO = new MovieDAO();
    public static final DirectorDAO directorDAO = new DirectorDAO();


    public abstract List<L> findAll() throws DAOException;

    public abstract L findById(K id) throws DAOException;

    public abstract boolean delete(L entity) throws DAOException;

    public abstract boolean delete(K id) throws DAOException;

    public abstract boolean create(L entity) throws DAOException;

    public abstract boolean update(L entity) throws DAOException;

    public abstract boolean isConnected();

    public abstract void setIfConnected(boolean isConnected);

    public void close(Statement statement){
        try{
            if(statement != null)
                statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
