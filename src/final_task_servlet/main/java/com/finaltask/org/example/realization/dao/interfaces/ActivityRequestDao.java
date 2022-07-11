package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.model.User;

/**
 * Abstract DAO class, which is responsible for carrying out operations on requests
 *
 * @see CommonDao
 * @see ActivityRequest
 *
 * @author Misha Rudyk
 */
public abstract class ActivityRequestDao extends CommonDao<ActivityRequest> implements Pageable<ActivityRequest>{

    /**
     * Method that find number of requests
     * @return number of requests
     * @throws DaoException
     */
    public abstract int getNumberOfActivityRequests() throws DaoException;

    /**
     * Method that find request by user and activity
     * @param user User in request
     * @param activity Activity in request
     * @return Activity Request
     * @throws DaoException
     */
    public abstract ActivityRequest findByUserAndActivity(User user, Activity activity) throws DaoException;

    }
