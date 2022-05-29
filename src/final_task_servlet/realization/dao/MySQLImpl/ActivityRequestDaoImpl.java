package final_task_servlet.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.interfaces.ActivityRequestDao;
import com.finaltask.org.example.realization.dao.interfaces.CommonDao;
import com.finaltask.org.example.realization.model.Activity;
import com.finaltask.org.example.realization.model.ActivityRequest;
import com.finaltask.org.example.realization.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRequestDaoImpl extends ActivityRequestDao {

    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;


    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";

    private static final String SQL_ADD_REQUEST =
            "INSERT INTO ActivityRequest(activity_id, user_id, action, status) VALUES(?, ?, ?, ?)";
    private static final String SQL_ADD_REQUEST_BY_ID =
            "INSERT INTO ActivityRequest(id, activity_id, user_id, action, status) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL = "SELECT * FROM ActivityRequest";
    private static final String SQL_GET_REQUEST =
            "SELECT * FROM ActivityRequest WHERE id=?";

    private static final String SQL_GET_REQUEST_BY_USER_AND_ACTIVITY =
            "SELECT * FROM ActivityRequest WHERE user_id=? AND activity_id=?";
    private static final String SQL_GET_USER_REQUESTS =
            "SELECT * FROM ActivityRequest WHERE user_id=?";
    private static final String SQL_GET_ACTIVITY_REQUESTS =
            "SELECT * FROM ActivityRequest WHERE activity_id=?";


    private static final String SQL_DELETE =
            "DELETE FROM ActivityRequest WHERE id=?";
    private static final String SQL_DELETE_BY_ACTIVITY =
            "DELETE FROM ActivityRequest WHERE activity_id=?";
    private static final String SQL_DELETE_BY_USER =
            "DELETE FROM ActivityRequest WHERE user_id=?";

    private static final String SQL_GET_NUMBER_OF_REQUESTS =
            "SELECT COUNT(*) FROM ActivityRequest";
    private static final String SQL_GET_REQUESTS_IN_LIMIT =
            "SELECT * from ActivityRequest order by id LIMIT ?, ?";

    @Override
    public boolean add(ActivityRequest activityRequest) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);

            preparedStatement = connection.prepareStatement(SQL_ADD_REQUEST);
            preparedStatement.setInt(1, activityRequest.getActivity().getId());
            preparedStatement.setInt(2, activityRequest.getUser().getId());
            preparedStatement.setString(3, activityRequest.getAction());
            preparedStatement.setString(4, activityRequest.getStatus());
            addedRecords = preparedStatement.executeUpdate();

            statement.executeUpdate(ON_FOREIGN_KEY);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return true;
    }

    @Override
    public boolean add(ActivityRequest activityRequest, int id) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);

            preparedStatement = connection.prepareStatement(SQL_ADD_REQUEST_BY_ID);
            preparedStatement.setInt(1, activityRequest.getId());
            preparedStatement.setInt(2, activityRequest.getActivity().getId());
            preparedStatement.setInt(3, activityRequest.getUser().getId());
            preparedStatement.setString(4, activityRequest.getAction());
            preparedStatement.setString(5, activityRequest.getStatus());
            addedRecords = preparedStatement.executeUpdate();

            statement.executeUpdate(ON_FOREIGN_KEY);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return true;
    }

    @Override
    public ActivityRequest find(int id) throws DaoException {
        transaction.init(this);
        ActivityRequest activityRequest = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_REQUEST);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ActivityRequest> activityRequests = fillArray(resultSet);
            if(activityRequests.isEmpty()){
                return null;
            }
            activityRequest = activityRequests.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            transaction.end();
        }
        return activityRequest;
    }

    public ActivityRequest findByUserAndActivity(User user, Activity activity) throws DaoException {
        transaction.init(this);
        ActivityRequest activityRequest = null;
        try {
            List<ActivityRequest> requests = new ArrayList<>();
            preparedStatement = connection.prepareStatement(SQL_GET_REQUEST_BY_USER_AND_ACTIVITY);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, activity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            requests = fillArray(resultSet, user);
            if(requests.isEmpty())
                return null;
            activityRequest = requests.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            transaction.end();
        }
        return activityRequest;
    }

    public List<ActivityRequest> findByUser(User user) throws DaoException {
        transaction.init(this);
        List<ActivityRequest> activityRequestList = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_REQUESTS);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            activityRequestList = fillArray(resultSet, user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return activityRequestList;
    }

    public List<ActivityRequest> findByActivity(Activity activity) throws DaoException {
        transaction.init(this);
        List<ActivityRequest> activityRequestList = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITY_REQUESTS);
            preparedStatement.setInt(1, activity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            activityRequestList = fillArray(resultSet, activity);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return activityRequestList;
    }

    private List<ActivityRequest> fillArray(ResultSet resultSet) throws SQLException, DaoException {
        List<ActivityRequest> activityRequestList = new ArrayList<>();
        while(resultSet.next()){
            int requestId = resultSet.getInt("id");
            int activityId = resultSet.getInt("activity_id");
            int userId = resultSet.getInt("user_id");
            String action = resultSet.getString("action");
            String status = resultSet.getString("status");
            ActivityDaoImpl activityDao = new ActivityDaoImpl();
            Activity activity = activityDao.find(activityId);

            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.find(userId);

            activityRequestList.add(new ActivityRequest(requestId, activity, user,
                    action, status));
        }
        return activityRequestList;
    }

    private List<ActivityRequest> fillArray(ResultSet resultSet, User user) throws SQLException, DaoException {
        List<ActivityRequest> activityRequestList = new ArrayList<>();
        while(resultSet.next()){
            int requestId = resultSet.getInt("id");
            int activityId = resultSet.getInt("activity_id");
            int userId = resultSet.getInt("user_id");
            String action = resultSet.getString("action");
            String status = resultSet.getString("status");
            ActivityDaoImpl activityDao = new ActivityDaoImpl();
            Activity activity = activityDao.find(activityId);

            activityRequestList.add(new ActivityRequest(requestId, activity, user,
                    action, status));
        }
        return activityRequestList;
    }

    private List<ActivityRequest> fillArray(ResultSet resultSet, Activity activity) throws SQLException, DaoException {
        List<ActivityRequest> activityRequestList = new ArrayList<>();
        while(resultSet.next()){
            int requestId = resultSet.getInt("id");
            int activityId = resultSet.getInt("activity_id");
            int userId = resultSet.getInt("user_id");
            String action = resultSet.getString("action");
            String status = resultSet.getString("status");

            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.findWithoutActivities(userId);

            activityRequestList.add(new ActivityRequest(requestId, activity, user,
                    action, status));
        }
        return activityRequestList;
    }

    @Override
    public List<ActivityRequest> getAll() throws DaoException {
        transaction.init(this);
        List<ActivityRequest> listOfActivityRequests = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            listOfActivityRequests = fillArray(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfActivityRequests;
    }

    @Override
    public boolean delete(int id) throws DaoException {
        transaction.init(this);
        int numberOfDeletedRecords = 0;
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            numberOfDeletedRecords = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            throw new DaoException(e);
        }
        if(numberOfDeletedRecords == 0)
            return false;
        return true;
    }

    public boolean delete(Activity activity) throws DaoException {
        transaction.init(this);
        int numberOfDeletedRecords = 0;
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ACTIVITY);
            preparedStatement.setInt(1, activity.getId());
            numberOfDeletedRecords = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            throw new DaoException(e);
        }
        if(numberOfDeletedRecords == 0)
            return false;
        return true;
    }

    public boolean delete(User user) throws DaoException {
        int numberOfDeletedRecords = 0;
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_USER);
            preparedStatement.setInt(1, user.getId());
            numberOfDeletedRecords = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            throw new DaoException(e);
        }
        if(numberOfDeletedRecords == 0)
            return false;
        return true;
    }

    public List<ActivityRequest> findInLimit(int size, int page) throws DaoException {
        transaction.init(this);
        List<ActivityRequest> listOfActivityRequests = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_REQUESTS_IN_LIMIT);
            preparedStatement.setInt(1, size * page);
            preparedStatement.setInt(2, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            listOfActivityRequests = fillArray(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfActivityRequests;
    }

    @Override
    public int getNumberOfActivityRequests() throws DaoException {
        int numberOfRequests = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_REQUESTS);
            resultSet.next();
            numberOfRequests = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            transaction.end();
        }
        return numberOfRequests;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static String UPDATE_REQUEST =
            "UPDATE ActivityRequest SET activity_id=?, user_id=?, action=?, status=? WHERE id=?";

    public void update(ActivityRequest request) throws DaoException {
        transaction.init(this);
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(UPDATE_REQUEST);
            preparedStatement.setInt(1, request.getActivity().getId());
            preparedStatement.setInt(2, request.getUser().getId());
            preparedStatement.setString(3, request.getAction());
            preparedStatement.setString(4, request.getStatus());
            preparedStatement.setInt(5, request.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}