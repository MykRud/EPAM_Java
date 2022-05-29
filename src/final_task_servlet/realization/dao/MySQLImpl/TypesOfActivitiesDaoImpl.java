package final_task_servlet.realization.dao.MySQLImpl;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.interfaces.CommonDao;
import com.finaltask.org.example.realization.dao.interfaces.TypeOfActivitiesDao;
import com.finaltask.org.example.realization.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypesOfActivitiesDaoImpl extends TypeOfActivitiesDao {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";

    private static final String SQL_ADD_TYPE =
            "INSERT INTO Type(name) VALUES(?)";
    private static final String SQL_ADD_TYPE_WITH_ID =
            "INSERT INTO Type(type_id, name) VALUES(?, ?)";
    private static final String SQL_GET_ALL = "SELECT * FROM Type";
    private static final String SQL_GET_TYPE =
            "SELECT * FROM Type WHERE type_id=?";
    private static final String SQL_GET_TYPE_BY_NAME =
            "SELECT * FROM Type WHERE name=?";
    private static final String SQL_GET_USERS_OF_TYPE =
            "SELECT ac.user_id FROM UsersAndActivities ac, Activity activ, Type typ WHERE ac.activity_id=(SELECT activity_id FROM Activity WHERE activ.type_id=?)";
    private static final String SQL_GET_ACTIVITIES_OF_TYPE =
            "SELECT activity_id FROM Activity WHERE type_id=?";

    @Override
    public boolean add(TypeOfActivity type) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);
            preparedStatement = connection.prepareStatement(SQL_ADD_TYPE);
            preparedStatement.setString(1, type.getName());
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
    public boolean add(TypeOfActivity type, int id) throws DaoException {
        // Check if is already in db
        int addedRecords = 0;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(OFF_FOREIGN_KEY);
            preparedStatement = connection.prepareStatement(SQL_ADD_TYPE_WITH_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, type.getName());
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

    private List<TypeOfActivity> fillArray(ResultSet resultSet) throws SQLException {
        List<TypeOfActivity> listOfTypes = new ArrayList<>();
        while(resultSet.next()){
            int typeId = resultSet.getInt("type_id");
            String name = resultSet.getString("name");
            listOfTypes.add(new TypeOfActivity(typeId, name));
        }
        return listOfTypes;
    }

    @Override
    public TypeOfActivity find(int id) throws DaoException {
        transaction.init(this);
        TypeOfActivity type = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TYPE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TypeOfActivity> types = fillArray(resultSet);
            if(types.isEmpty()){
                return null;
            }
            type = types.get(0);

            preparedStatement = connection.prepareStatement(SQL_GET_USERS_OF_TYPE);
            List<User> listOfUsers = new ArrayList<>();
            preparedStatement.setInt(1, type.getId());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while(resultSet1.next())
                listOfUsers.add(CommonDao.userDao.find(resultSet1.getInt("user_id")));
            type.setUsers(listOfUsers);


            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITIES_OF_TYPE);
            List<Activity> listOfActivities = new ArrayList<>();
            preparedStatement.setInt(1, type.getId());
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while(resultSet2.next())
                listOfActivities.add(CommonDao.activityDao.find(resultSet1.getInt("activity_id")));
            type.setActivities(listOfActivities);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return type;
    }

    public TypeOfActivity find(String name) throws DaoException {
        transaction.init(this);
        TypeOfActivity type = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TYPE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TypeOfActivity> types = fillArray(resultSet);
            if(types.isEmpty()){
                return null;
            }
            type = types.get(0);

            preparedStatement = connection.prepareStatement(SQL_GET_USERS_OF_TYPE);
            List<User> listOfUsers = new ArrayList<>();
            preparedStatement.setInt(1, type.getId());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while(resultSet1.next())
                listOfUsers.add(CommonDao.userDao.find(resultSet1.getInt("user_id")));
            type.setUsers(listOfUsers);


            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITIES_OF_TYPE);
            List<Activity> listOfActivities = new ArrayList<>();
            preparedStatement.setInt(1, type.getId());
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while(resultSet2.next())
                listOfActivities.add(CommonDao.activityDao.find(resultSet1.getInt("activity_id")));
            type.setActivities(listOfActivities);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return type;
    }

    public TypeOfActivity findTypeWithoutReferences(int id) throws DaoException {
        transaction.init(this);
        TypeOfActivity type = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TYPE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TypeOfActivity> types = fillArray(resultSet);
            if(types.isEmpty()){
                return null;
            }
            type = types.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return type;
    }

    public TypeOfActivity findTypeWithoutReferences(String name) throws DaoException {
        transaction.init(this);
        TypeOfActivity type = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TYPE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TypeOfActivity> typesList = fillArray(resultSet);
            if(typesList.isEmpty())
                return null;
            type = typesList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return type;
    }

    @Override
    public List<TypeOfActivity> getAll() throws DaoException {
        transaction.init(this);
        List<TypeOfActivity> listOfTypes = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            listOfTypes = fillArray(resultSet);
            preparedStatement = connection.prepareStatement(SQL_GET_ACTIVITIES_OF_TYPE);
            for(TypeOfActivity type : listOfTypes){
                List<Activity> listOfActivities = new ArrayList<>();
                preparedStatement.setInt(1, type.getId());
                ResultSet resultSet1 = preparedStatement.executeQuery();
                while(resultSet1.next())
                    listOfActivities.add(CommonDao.activityDao.findWithoutReferences(resultSet1.getInt("activity_id")));
                type.setActivities(listOfActivities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end();
        }
        return listOfTypes;
    }

    private static final String SQL_DELETE_TYPE =
            "DELETE FROM Type WHERE type_id=?";

    @Override
    public boolean delete(int id) throws DaoException {
        transaction.init(this);
        int numberOfDeletedRecords = 0;
        try{

            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(SQL_DELETE_TYPE);
            preparedStatement.setInt(1, id);
            numberOfDeletedRecords = preparedStatement.executeUpdate();
            close(preparedStatement);

            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            close(preparedStatement);

            if(numberOfDeletedRecords == 0)
                return false;
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private static String UPDATE_TYPE =
            "UPDATE Type SET name=? WHERE type_id=?";



    @Override
    public void update(TypeOfActivity type) throws DaoException{
        transaction.init(this);
        try{
            preparedStatement = connection.prepareStatement(OFF_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(UPDATE_TYPE);
            preparedStatement.setString(1, type.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(ON_FOREIGN_KEY);
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
