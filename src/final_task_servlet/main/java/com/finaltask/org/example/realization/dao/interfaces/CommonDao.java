package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.EntityTransaction;
import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Common DAO class, which is responsible for carrying out operations with data source
 *
 * @see CommonDao
 * @see com.finaltask.org.example.realization.model.Activity
 * @see ActivityDao
 * @see com.finaltask.org.example.realization.model.User
 * @see UserDao
 * @see com.finaltask.org.example.realization.model.TypeOfActivity
 * @see TypeOfActivitiesDao
 * @see Entity
 * @see EntityTransaction
 *
 * @author Misha Rudyk
 */
public abstract class CommonDao <K extends Entity>{
    public static EntityTransaction transaction = new EntityTransaction();
    public static ActivityDao activityDao;
    public static UserDao userDao;
    public static TypeOfActivitiesDao typeDao;
    public static ActivityRequestDao requestDao;

    /**
     * Method that gives transaction
     * @return transaction
     */
    public static EntityTransaction getTransaction() {
        return transaction;
    }

    /**
     * Method that sets transaction
     * @param transaction Transaction
     */
    public static void setTransaction(EntityTransaction transaction) {
        CommonDao.transaction = transaction;
    }

    /**
     * Method that allows to add entity
     * @param obj Entity to add
     * @return if entity has been added
     * @throws DaoException
     */
    public abstract boolean add(K obj) throws DaoException;

    /**
     * Method that allows you to add an entity by identifier
     * @param obj Entity to add
     * @param id Identifies
     * @return if entity has been added
     * @throws DaoException
     */
    public abstract boolean add(K obj, int id) throws DaoException;

    /**
     * Method that allows to find entity by id
     * @param id Identifier
     * @return Entity model
     * @throws DaoException
     */
    public abstract K find(int id) throws DaoException;

    /**
     * Method that allows to get all entities
     * @return List of entities
     * @throws DaoException
     */
    public abstract List<K> getAll() throws DaoException;

    /**
     * Method that allows to delete entity by id
     * @param id Identifier
     * @return if entity has benn deleted
     * @throws DaoException
     */
    public abstract boolean delete(int id) throws DaoException;

    /**
     * Method that allows to update record of the entity
     * @param obj Updated entity model
     * @throws DaoException
     */
    public abstract void update(K obj) throws DaoException;

    /**
     * Method that sets connection
     * @param connection Connection
     */
    public abstract void setConnection(Connection connection);

    /**
     * Method that close statement
     * @param statement Statement to close
     */
    public void close(Statement statement){
        try{
            if(statement != null)
                statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
