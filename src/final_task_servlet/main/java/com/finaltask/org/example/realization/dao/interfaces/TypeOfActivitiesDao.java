package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.TypeOfActivity;

/**
 * Abstract DAO class, which is responsible for carrying out operations on types
 *
 * @see CommonDao
 * @see TypeOfActivity
 *
 * @author Misha Rudyk
 */
public abstract class TypeOfActivitiesDao extends CommonDao<TypeOfActivity>{

    /**
     * Method that find type by id without any references
     * @param id identifier of type
     * @return Type model
     * @throws DaoException
     */
    public abstract TypeOfActivity findTypeWithoutReferences(int id) throws DaoException;

    /**
     * Method that find type by name without any references
     * @param name Name of type
     * @return Type model
     * @throws DaoException
     */
    public abstract TypeOfActivity findTypeWithoutReferences(String name) throws DaoException;

    }
