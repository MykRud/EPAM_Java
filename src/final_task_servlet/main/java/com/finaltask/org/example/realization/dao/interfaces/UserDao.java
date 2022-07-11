package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.User;

/**
 * Abstract DAO class, which is responsible for carrying out operations on users
 *
 * @see CommonDao
 * @see User
 *
 * @author Misha Rudyk
 */
public abstract class UserDao extends CommonDao<User> implements Pageable<User>{

    /**
     * Method that find user by username
     * @param username Username
     * @return User model
     * @throws DaoException
     */
    public abstract User find(String username) throws DaoException;

    /**
     * Method that find number of users
     * @return number of users
     * @throws DaoException
     */
    public abstract int findNumberOfUsers() throws DaoException;

}
