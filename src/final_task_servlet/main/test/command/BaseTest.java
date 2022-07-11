package final_task_servlet.main.test.command;

import com.finaltask.org.example.realization.dao.ConnectionPool;
import com.finaltask.org.example.realization.dao.MySQLImpl.ActivityDaoImpl;
import com.finaltask.org.example.realization.dao.MySQLImpl.ActivityRequestDaoImpl;
import com.finaltask.org.example.realization.dao.MySQLImpl.TypesOfActivitiesDaoImpl;
import com.finaltask.org.example.realization.dao.MySQLImpl.UserDaoImpl;
import com.finaltask.org.example.realization.dao.interfaces.ActivityDao;
import com.finaltask.org.example.realization.dao.interfaces.ActivityRequestDao;
import com.finaltask.org.example.realization.dao.interfaces.TypeOfActivitiesDao;
import com.finaltask.org.example.realization.dao.interfaces.UserDao;
import com.finaltask.org.example.realization.model.*;
import com.finaltask.org.example.realization.service.BusinessServiceImpl;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class BaseTest extends Mockito {
    protected static UserDao userDao;
    protected static ActivityDao activityDao;
    protected static ActivityRequestDao requestDao;
    protected static TypeOfActivitiesDao typeDao;
    protected static User user;
    static {

        createUser();

        try {
            Connection connection = ConnectionPool.createConnection();

            userDao = new UserDaoImpl();
            activityDao = new ActivityDaoImpl();
            requestDao = new ActivityRequestDaoImpl();
            typeDao = new TypesOfActivitiesDaoImpl();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Activity createActivity(){
        BusinessServiceImpl service = new BusinessServiceImpl();
        Activity activity = service.findActivity(1);
        if(activity == null) {
            activity = new Activity(1, "Football", new TypeOfActivity(1, "Physics"));
            activity.setDescription("Playing Football");
            service.addActivity(activity, 1);
        }
        activity.setStatus("Pending");
        activity.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        activity.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        activity.setDuration(0);
        service.updateActivity(activity);

        return activity;
    }

    public ActivityRequest createRequest(){
        BusinessServiceImpl service = new BusinessServiceImpl();
        ActivityRequest request = service.findRequest(1);
        if(request == null) {
            request = new ActivityRequest(1, createActivity(), user, "Add", "Approved");
            service.addRequest(request, 1);
        }

        return request;
    }

    public static void createUser(){
        BusinessServiceImpl service = new BusinessServiceImpl();
        user = service.findUser(1);
        if(user != null) {
            user.setAuthorities(new ArrayList<>(Arrays.asList(Authority.USER, Authority.ADMIN)));
            service.updateUser(user);
        } else {
            user = new User(1, "admin", "admin", "admin", "MR!QAZ2wsx", 19, "Male", "0980689690");
            user.setAuthorities(new ArrayList<>(
                    Arrays.asList(
                            Authority.USER,
                            Authority.ADMIN
                    )
            ));
            service.addUser(user, 1);
        }
    }
}
