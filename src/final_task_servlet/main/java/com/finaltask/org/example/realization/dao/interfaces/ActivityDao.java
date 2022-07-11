package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;

import java.util.List;

/**
 * Abstract DAO class, which is responsible for carrying out operations on activities
 *
 * @see CommonDao
 * @see Activity
 *
 * @author Misha Rudyk
 */
public abstract class ActivityDao extends CommonDao<Activity> implements Pageable<Activity>{

    /**
     * Method that find number of activities
     * @return number of activities
     * @throws DaoException
     */
    public abstract int getNumberOfActivities() throws DaoException;

    /**
     * Method that find activity by name
     * @param name Activity name
     * @return Activity model
     * @throws DaoException
     */
    public abstract Activity find(String name) throws DaoException;

    /**
     * Method that find activity by id without any references
     * @param id identifier of activity
     * @return Activity model
     * @throws DaoException
     */
    public abstract Activity findWithoutReferences(int id) throws DaoException;

    /**
     * Method that find some number of sorted by type activities for page
     * @param size how many activities needs to find
     * @param page page number
     * @return List of activities
     * @throws DaoException
     */
    public abstract List<Activity> findInLimitByType(int size, int page) throws DaoException;

    /**
     * Method that find some number of sorted by name activities for page
     * @param size how many activities needs to find
     * @param page page number
     * @return List of activities
     * @throws DaoException
     */
    public abstract List<Activity> findInLimitByName(int size, int page) throws DaoException;

    /**
     * Method that find some number of sorted by number of users activities for page
     * @param size how many activities needs to find
     * @param page page number
     * @return List of activities
     * @throws DaoException
     */
    public abstract List<Activity> findInLimitByNumberOfUsers(int size, int page) throws DaoException;
}
