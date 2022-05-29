package final_task_servlet.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.interfaces.ActivityDao;
import com.finaltask.org.example.realization.dao.interfaces.CommonDao;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.model.TypeOfActivity;
import com.finaltask.org.example.realization.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl extends ActivityDao {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;


    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";

    private static final String SQL_ADD_ACTIVITY =
            "INSERT INTO Activity(name, description, type_id, status) VALUES(?, ?, ?, ?)";
    private static final String SQL_ADD_ACTIVITY_BY_ID =
            "INSERT INTO Activity(activity_id, name, description, type_id, status) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL = "SELECT * FROM Activity";
    private static final String SQL_GET_ACTIVITY =
            "SELECT * FROM Activity WHERE activity_id=?";
    private static final String SQL_GET_USERS_OF_ACTIVITY =
            "SELECT user_id FROM UsersAndActivities " +
                    "WHERE activity_id=?";
    private static final String SQL_GET_NUMBER_OF_ACTIVITIES =
            "SELECT COUNT(*) FROM Activity";
    private static final String SQL_GET_ACTIVITIES_IN_LIMIT =
            "SELECT * from Activity order by type_id LIMIT ?, ?";

    @Override
    public boolean add(Activity activity) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);

            preparedStatement = connection.prepareStatement(SQL_ADD_ACTIVITY);
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setInt(3, activity.getType().getId());
            preparedStatement.setString(4, "Pending");
            addedRecords = preparedStatement.executeUpdate();

            statement.executeUpdate(ON_FOREIGN_KEY);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(statement);
            transaction.end();
        }
        return true;
    }

    @Override
    public boolean add(Activity activity, int id) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);

            preparedStatement = connection.prepareStatement(SQL_ADD_ACTIVITY_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, activity.getName());
            preparedStatement.setString(3, activity.getDescription());
            preparedStatement.setInt(4, activity.getType().getId());
            preparedStatement.setString(5, "Pending");
            addedRecords = preparedStatement.executeUpdate();

            statement.executeUpdate(ON_FOREIGN_KEY);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(statement);
            transaction.end();
        }
        return true;
    }

    @Override
    public Activity find(int id) throws DaoException {
        transaction.init(this);
        Activity activity = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Activity> activities = fillArray(resultSet);
            if(activities.isEmpty()){
                return null;
            }
            activity = activities.get(0);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            close(statement);
        }
        return activity;
    }

    public Activity findWithoutReferences(int id) throws DaoException {
        transaction.init(this);
        Activity activity = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Activity> activities = fillArray(resultSet);
            if(activities.isEmpty()){
                return null;
            }
            activity = activities.get(0);

        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            close(statement);
        }
        return activity;
    }

    public int getNumberOfActivities() throws DaoException {
        int numberOfActivities = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_ACTIVITIES);
            resultSet.next();
            numberOfActivities = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            transaction.end();
        }
        return numberOfActivities;
    }

    public List<Activity> findInLimit(int size, int page) throws DaoException {
        transaction.init(this);
        List<Activity> listOfActivity = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITIES_IN_LIMIT);
            preparedStatement.setInt(1, size * page);
            preparedStatement.setInt(2, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            listOfActivity = fillArray(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfActivity;
    }

    public static final String SQL_DELETE_ACTIVITY = "DELETE FROM Activity WHERE activity_id=?";

    @Override
    public boolean delete(int id) throws DaoException{
        transaction.init(this);
        boolean isDeleted = false;
        try{
            Activity activity = find(id);
            connection.createStatement().executeUpdate(OFF_FOREIGN_KEY);
            preparedStatement = connection.prepareStatement(SQL_DELETE_ACTIVITY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();

            ActivityRequestDaoImpl requestDao = new ActivityRequestDaoImpl();
            requestDao.delete(activity);

            connection.createStatement().executeUpdate(ON_FOREIGN_KEY);
            if(result != 0)
                isDeleted = true;
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return isDeleted;
    }



    private List<Activity> fillArray(ResultSet resultSet) throws SQLException, DaoException {
        List<Activity> listOfActivities = new ArrayList<>();
        while(resultSet.next()){
            int activityId = resultSet.getInt("activity_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String status = resultSet.getString("status");
            int duration = resultSet.getInt("duration");
            Timestamp startTime = resultSet.getTimestamp("start_time");
            Timestamp endTime = resultSet.getTimestamp("end_time");
            int typeId = resultSet.getInt("type_id");

            TypesOfActivitiesDaoImpl typeDao = new TypesOfActivitiesDaoImpl();
            TypeOfActivity activityType = typeDao.findTypeWithoutReferences(typeId);

            Activity activity = new Activity(activityId, name, activityType);
            activity.setDescription(description);
            activity.setStatus(status);
            activity.setDuration(duration);
            activity.setStartTime(startTime);
            activity.setEndTime(endTime);

            ActivityRequestDaoImpl requestDao = new ActivityRequestDaoImpl();
            List<ActivityRequest> listOfRequests = requestDao.findByActivity(activity);

            List<User> usersOfActivity = new ArrayList<>();
            for(ActivityRequest activityRequest : listOfRequests){
                boolean isAvailable = false;
                for(User user : usersOfActivity) {
                    if (user.getUsername().equals(activityRequest.getUser().getUsername())) {
                        isAvailable = true;
                        break;
                    }
                }
                if(isAvailable)
                    continue;
                usersOfActivity.add(activityRequest.getUser());
            }
            activity.setUsers(usersOfActivity);

            listOfActivities.add(activity);
        }
        return listOfActivities;
    }

    @Override
    public List<Activity> getAll() throws DaoException {
        transaction.init(this);
        List<Activity> listOfActivities = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            listOfActivities = fillArray(resultSet);
            preparedStatement = connection.prepareStatement(SQL_GET_USERS_OF_ACTIVITY);
            for(Activity activity : listOfActivities){
                List<User> listOfUsers = new ArrayList<>();
                preparedStatement.setInt(1, activity.getId());
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while(resultSet1.next())
                  listOfUsers.add(CommonDao.userDao.find(resultSet1.getInt("user_id")));
                activity.setUsers(listOfUsers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfActivities;
    }

    private static String UPDATE_ACTIVITY =
            "UPDATE Activity SET duration=?, start_time=?, end_time=?, status=? WHERE activity_id=?";

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void update(Activity activity) throws DaoException {
        transaction.init(this);
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(UPDATE_ACTIVITY);
            preparedStatement.setInt(1, activity.getDuration());
            preparedStatement.setTimestamp(2, activity.getSqlStartTime());
            preparedStatement.setTimestamp(3, activity.getSqlEndTime());
            preparedStatement.setString(4, activity.getStatus());
            preparedStatement.setInt(5, activity.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
