package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.EntityTransaction;
import com.finaltask.org.example.realization.model.Entity;
import com.finaltask.org.example.realization.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class CommonDao <K extends Entity>{
    public static EntityTransaction transaction = new EntityTransaction();
    public static ActivityDao activityDao;
    public static UserDao userDao;
    public static TypeOfActivitiesDao typeDao;
    public static ActivityRequestDao requestDao;

    public static EntityTransaction getTransaction() {
        return transaction;
    }

    public static void setTransaction(EntityTransaction transaction) {
        CommonDao.transaction = transaction;
    }

    public abstract boolean add(K obj) throws DaoException;

    public abstract boolean add(K obj, int id) throws DaoException;

    public abstract K find(int id) throws DaoException;

    public abstract List<K> getAll() throws DaoException;

    public abstract boolean delete(int id) throws DaoException;

    public abstract void update(K obj) throws DaoException;

    public abstract void setConnection(Connection connection);

    public void close(Statement statement){
        try{
            if(statement != null)
                statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
