package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.User;

import java.util.List;

public abstract class UserDao extends CommonDao<User> implements Pageable<User>{

    public abstract User find(String username) throws DaoException;

    public abstract int findNumberOfUsers() throws DaoException;

}
