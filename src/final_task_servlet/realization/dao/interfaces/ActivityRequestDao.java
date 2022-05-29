package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.model.User;

public abstract class ActivityRequestDao extends CommonDao<ActivityRequest> implements Pageable<ActivityRequest>{

    public abstract int getNumberOfActivityRequests() throws DaoException;

    public abstract ActivityRequest findByUserAndActivity(User user, Activity activity) throws DaoException;

    }
