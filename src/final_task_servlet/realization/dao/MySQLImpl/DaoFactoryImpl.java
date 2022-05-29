package final_task_servlet.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoFactory;
import com.finaltask.org.example.realization.dao.interfaces.ActivityDao;
import com.finaltask.org.example.realization.dao.interfaces.ActivityRequestDao;
import com.finaltask.org.example.realization.dao.interfaces.TypeOfActivitiesDao;
import com.finaltask.org.example.realization.dao.interfaces.UserDao;

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
