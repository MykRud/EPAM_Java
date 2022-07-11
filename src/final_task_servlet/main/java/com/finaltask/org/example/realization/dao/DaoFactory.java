package final_task_servlet.main.java.com.finaltask.org.example.realization.dao;

import com.finaltask.org.example.realization.dao.interfaces.ActivityDao;
import com.finaltask.org.example.realization.dao.interfaces.ActivityRequestDao;
import com.finaltask.org.example.realization.dao.interfaces.TypeOfActivitiesDao;
import com.finaltask.org.example.realization.dao.interfaces.UserDao;

/**
 * Abstract factory that returns daos
 *
 * @see ActivityDao
 * @see UserDao
 * @see TypeOfActivitiesDao
 * @see ActivityRequestDao
 *
 * @author Misha Rudyk
 */
public abstract class DaoFactory {

    public abstract UserDao createUserDao();

    public abstract ActivityDao createActivityDao();

    public abstract ActivityRequestDao createRequestDao();

    public abstract TypeOfActivitiesDao createTypesOfActivitiesDao();

}
