package final_task_servlet.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.interfaces.UserDao;
import com.finaltask.org.example.realization.model.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends UserDao {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";

    private static final String SQL_ADD_USER =
            "INSERT INTO User(first_name, last_name, username, password, age, contact, gender) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_USER_WITH_ID =
            "INSERT INTO User(user_id, first_name, last_name, username, password, age, contact, gender) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_GET_ALL =
            "SELECT * FROM User;";
    private static final String SQL_GET_USER_ACTIVITIES =
            "SELECT activity_id FROM ActivityRequest WHERE user_id=? AND status='Approved'";
    private static final String SQL_GET_USER =
            "SELECT * FROM User WHERE user_id=?";
    private static final String SQL_GET_USER_BY_USERNAME =
            "SELECT * FROM User WHERE username=?";
    private static final String SQL_GET_USER_AUTHORITIES =
            "SELECT authority_name FROM Authority WHERE user_id=?";
    private static final String SQL_SET_USER_AUTHORITIES =
            "INSERT INTO Authority(user_id, authority_name) VALUES (?, ?)";
    private static final String SQL_GET_NUMBER_OF_USERS =
            "SELECT COUNT(*) FROM User";
    private static final String SQL_GET_USERS_IN_LIMIT =
    "SELECT * from User order by last_name, first_name LIMIT ?, ?";

    public UserDaoImpl(){

    }

    public int findNumberOfUsers() throws DaoException {
        int numberOfUsers = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_USERS);
            resultSet.next();
            numberOfUsers = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            transaction.end();
        }
        return numberOfUsers;
    }

    private static final String SQL_ADD_AUTHORITY =
            "INSERT INTO Authority(user_id, authority_name)" +
                    "VALUES(?, ?)";
    @Override
    public boolean add(User user) throws DaoException{
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);
            //close(statement);
            preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            preparedStatement.setString(4, hashed);
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getContact());
            preparedStatement.setString(7, user.getGender());
            addedRecords = preparedStatement.executeUpdate();
            // close(preparedStatement);
            User userFind = find(user.getUsername());
            preparedStatement = connection.prepareStatement(SQL_ADD_AUTHORITY);
            preparedStatement.setInt(1, userFind.getId());
            preparedStatement.setString(2, Authority.USER.toString());
            preparedStatement.executeUpdate();
            // close(preparedStatement);
            statement.executeUpdate(ON_FOREIGN_KEY);
            //close(statement);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(preparedStatement);
            transaction.end();
        }
        return true;
    }

    @Override
    public boolean add(User user, int id) throws DaoException{
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);
            //close(statement);
            preparedStatement = connection.prepareStatement(SQL_ADD_USER_WITH_ID);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUsername());
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            preparedStatement.setString(5, hashed);
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getContact());
            preparedStatement.setString(8, user.getGender());
            addedRecords = preparedStatement.executeUpdate();
            // close(preparedStatement);
            User userFind = find(user.getUsername());
            preparedStatement = connection.prepareStatement(SQL_ADD_AUTHORITY);
            preparedStatement.setInt(1, userFind.getId());
            preparedStatement.setString(2, Authority.USER.toString());
            preparedStatement.executeUpdate();
            // close(preparedStatement);
            statement.executeUpdate(ON_FOREIGN_KEY);
            //close(statement);
            if(addedRecords == 0)
                return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(preparedStatement);
            transaction.end();
        }
        return true;
    }

    @Override
    public User find(int id) throws DaoException{
        transaction.init(this);
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = fillArray(resultSet);
            if(users.isEmpty()){
                return null;
            }
            user = users.get(0);

            preparedStatement = connection.prepareStatement(SQL_GET_USER_ACTIVITIES);
            List<Activity> listOfActivities = new ArrayList<>();
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet1 = preparedStatement.executeQuery();

            ActivityDaoImpl activityDao = new ActivityDaoImpl();
            while(resultSet1.next())
                listOfActivities.add(activityDao.findWithoutReferences(resultSet1.getInt("activity_id")));
            user.setActivities(listOfActivities);

            ActivityRequestDaoImpl requestDao = new ActivityRequestDaoImpl();
            List<ActivityRequest> requestList = requestDao.findByUser(user);
            user.setActivityRequests(requestList);

            user.setAuthorities(findAuthority(user.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return user;
    }

    public User find(String username) throws DaoException{
        transaction.init(this);
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = fillArray(resultSet);
            if(users.isEmpty()){
                return null;
            }
            user = users.get(0);

            preparedStatement = connection.prepareStatement(SQL_GET_USER_ACTIVITIES);
            List<Activity> listOfActivities = new ArrayList<>();
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            ActivityDaoImpl activityDao = new ActivityDaoImpl();
            while(resultSet1.next())
                listOfActivities.add(activityDao.findWithoutReferences(resultSet1.getInt("activity_id")));
            user.setActivities(listOfActivities);

            ActivityRequestDaoImpl requestDao = new ActivityRequestDaoImpl();
            List<ActivityRequest> requestList = requestDao.findByUser(user);
            user.setActivityRequests(requestList);

            user.setAuthorities(findAuthority(user.getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findWithoutActivities(int id) throws DaoException{
        transaction.init(this);
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = fillArray(resultSet);
            if(users.isEmpty()){
                return null;
            }
            user = users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return user;
    }

    public List<Authority> findAuthority(int userId) throws DaoException{
        transaction.init(this);
        List<Authority> authorityList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(SQL_GET_USER_AUTHORITIES);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String authorityName = resultSet.getString("authority_name");
                Authority authority = Authority.valueOf(authorityName);
                authorityList.add(authority);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return authorityList;
    }

    public void addAuthority(int user_id, Authority authority) throws DaoException {
        transaction.init(this);
        try {
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_ADD_AUTHORITY);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, authority.toString());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            close(preparedStatement);
        }
    }

    private static final String SQL_DELETE_USER_AUTHORITY =
            "DELETE FROM Authority WHERE user_id=? AND authority_name=?";

    public void deleteUserAuthority(int user_id, Authority authority) throws DaoException {
        transaction.init(this);
        try {
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_AUTHORITY);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, authority.toString());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            close(preparedStatement);
        }
    }

    private static final String SQL_DELETE_USER_AUTHORITIES =
            "DELETE FROM Authority WHERE user_id=?";

    public boolean deleteUserAuthorities(int user_id) throws DaoException {
        transaction.init(this);
        int deletedRows = 0;
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_AUTHORITIES);
            preparedStatement.setInt(1, user_id);
            deletedRows = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            close(preparedStatement);
        }
        return deletedRows != 0;
    }

    private List<User> fillArray(ResultSet resultSet) throws SQLException {
        List<User> listOfUsers = new ArrayList<>();
        while(resultSet.next()){
            int userId = resultSet.getInt("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String contact = resultSet.getString("contact");
            String gender = resultSet.getString("gender");
            listOfUsers.add(new User(userId, firstName, lastName, username, password,
                    age, contact, gender));
        }
        return listOfUsers;
    }

    @Override
    public List<User> getAll() throws DaoException {
        transaction.init(this);
        List<User> listOfUsers = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            listOfUsers = fillArray(resultSet);
            preparedStatement = connection.prepareStatement(SQL_GET_USER_ACTIVITIES);
            for(User user : listOfUsers){
                List<Activity> listOfActivities = new ArrayList<>();
                preparedStatement.setInt(1, user.getId());
                ResultSet resultSet1 = preparedStatement.executeQuery();
                ActivityDaoImpl activityDao = new ActivityDaoImpl();
                while(resultSet1.next())
                    listOfActivities.add(activityDao.findWithoutReferences(resultSet1.getInt("activity_id")));
                user.setActivities(listOfActivities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfUsers;
    }

    public List<User> findInLimit(int size, int page) throws DaoException {
        transaction.init(this);
        List<User> listOfUsers = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USERS_IN_LIMIT);
            preparedStatement.setInt(1, size * page);
            preparedStatement.setInt(2, size);
            ResultSet resultSet = preparedStatement.executeQuery();
            listOfUsers = fillArray(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfUsers;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_DELETE_USER =
            "DELETE FROM User WHERE user_id=?";

    @Override
    public boolean delete(int userId) throws DaoException {
        transaction.init(this);
        try{
            int numberOfDeletedRecords = 0;

            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, userId);
            numberOfDeletedRecords = preparedStatement.executeUpdate();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            close(preparedStatement);

            deleteUserAuthorities(userId);

            if(numberOfDeletedRecords == 0)
                return false;
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private static final String SQL_UPDATE_USER =
            "UPDATE User SET first_name=?, last_name=?, username=?, password=?, contact=?, age=? WHERE user_id=?";
    public void update(User user) throws DaoException{
        transaction.init(this);
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            preparedStatement.setString(4, hashed);
            preparedStatement.setString(5, user.getContact());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            close(preparedStatement);
            close(statement);
        }
    }
}
