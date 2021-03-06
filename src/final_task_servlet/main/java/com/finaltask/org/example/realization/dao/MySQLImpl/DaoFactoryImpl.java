package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoFactory;
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
public class DaoFactoryImpl extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public ActivityDao createActivityDao() {
        return new ActivityDaoImpl();
    }

    @Override
    public ActivityRequestDao createRequestDao() {
        return new ActivityRequestDaoImpl();
    }

    @Override
    public TypeOfActivitiesDao createTypesOfActivitiesDao() {
        return new TypesOfActivitiesDaoImpl();
    }
}
