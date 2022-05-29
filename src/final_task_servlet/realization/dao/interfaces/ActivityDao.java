package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;

public abstract class ActivityDao extends CommonDao<Activity> implements Pageable<Activity>{

    public abstract int getNumberOfActivities() throws DaoException;

    public abstract Activity findWithoutReferences(int id) throws DaoException;


    }
