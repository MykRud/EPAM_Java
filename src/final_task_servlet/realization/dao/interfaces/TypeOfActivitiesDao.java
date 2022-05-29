package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.TypeOfActivity;

public abstract class TypeOfActivitiesDao extends CommonDao<TypeOfActivity>{

    public abstract TypeOfActivity findTypeWithoutReferences(int id) throws DaoException;

    public abstract TypeOfActivity findTypeWithoutReferences(String name) throws DaoException;

    }
