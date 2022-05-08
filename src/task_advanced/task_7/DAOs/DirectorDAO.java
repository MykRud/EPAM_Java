package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Director;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectorDAO extends DAO<Integer, Director> {

    private boolean isConnected = false;
    private static final String SQL_SELECT_DIRECTORS = "SELECT * FROM Director";
    private static final String SQL_SELECT_DIRECTOR_BY_ID = "SELECT * FROM Director WHERE director_id=?";
    private static final String SQL_SELECT_DIRECTORS_BY_LASTNAME = "SELECT * FROM Director WHERE lastname=?";
    private static final String SQL_SELECT_DIRECTORS_BY_BIRTH_DATE = "SELECT * FROM Director WHERE birth_date=?";
    private static final String SQL_SELECT_DIRECTOR_OF_MOVIE =
            "SELECT dir.* FROM Director dir, Movie mov WHERE dir.director_id=mov.director_id AND mov.name=?";
    private static final String SQL_GET_NUMBER_OF_DIRECTORS =
            "SELECT COUNT(*) FROM Director";

    public DirectorDAO(){
        try {
            Director.setNumberOfDirectors(findNumberOfRecords());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private int findNumberOfRecords() throws DAOException {
        int numberOfDirectors = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_DIRECTORS);
            resultSet.next();
            numberOfDirectors = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return numberOfDirectors;
    }

    @Override
    public List<Director> findAll() throws DAOException {
        transaction.init(this);
        List<Director> listOfDirectors = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSetOfActors = statement.executeQuery(SQL_SELECT_DIRECTORS);
            listOfDirectors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfDirectors;
    }

    @Override
    public Director findById(Integer id) throws DAOException {
        transaction.init(this);
        List<Director> listOfDirectors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_DIRECTOR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfDirectors = fillArray(resultSetOfActors);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        if(listOfDirectors.isEmpty())
            return null;
        return listOfDirectors.get(0);
    }

    public List<Director> findDirectorsByLastName(String name) throws DAOException{
        if(!isConnected)
            transaction.init(this);
        List<Director> listOfDirectors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_DIRECTORS_BY_LASTNAME);
            statement.setString(1, name);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfDirectors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            if(!movieDAO.isConnected())
                transaction.end(this);
        }
        return listOfDirectors;
    }

    public List<Director> findDirectorsByBirthDate(String birthDate) throws DAOException{
        if(!birthDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"))
            return Collections.emptyList();
        transaction.init(this);
        List<Director> listOfDirectors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_DIRECTORS_BY_BIRTH_DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(birthDate);
            Date date = new Date(utilDate.getTime());
            statement.setDate(1, date);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfDirectors = fillArray(resultSetOfActors);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfDirectors;
    }

    public Director findDirectorOfMovie(String movieName) throws DAOException{
        transaction.init(this);
        List<Director> listOfDirectors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_DIRECTOR_OF_MOVIE);
            statement.setString(1, movieName);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfDirectors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfDirectors.get(0);
    }

    private List<Director> fillArray(ResultSet resultSet) throws DAOException {
        List<Director> listOfDirectors = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int directorId = resultSet.getInt("director_id");
                String firstNameDirector = resultSet.getString("firstname");
                String lastNameDirector = resultSet.getString("lastname");
                Date birth_date = resultSet.getDate("birth_date");
                listOfDirectors.add(new Director(directorId, firstNameDirector, lastNameDirector, birth_date));
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return listOfDirectors;
    }

    @Override
    public boolean isConnected(){
        return isConnected;
    }

    @Override
    public void setIfConnected(boolean isConnected){
        this.isConnected = isConnected;
    }

    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";
    private static final String SQL_DELETE_DIRECTOR_BY_ID =
            "DELETE FROM Director WHERE director_id=?";
    private static final String SQL_UPDATE_MOVIE_DIRECTOR =
            "UPDATE Movie SET director_id=0 WHERE director_id=?";

    @Override
    public boolean delete(Director entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_DIRECTOR_BY_ID);
            statement.setInt(1, entity.getId());
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_UPDATE_MOVIE_DIRECTOR);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(ON_FOREIGN_KEY);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return updatedRecords > 0;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_DIRECTOR_BY_ID);
            statement.setInt(1, id);
            updatedRecords = statement.executeUpdate();
            if (updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_UPDATE_MOVIE_DIRECTOR);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = connection.prepareStatement(ON_FOREIGN_KEY);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return updatedRecords > 0;
    }

    private static final String SQL_INSERT_DIRECTOR = "INSERT INTO Director(" +
            "director_id, firstname, lastname, birth_date)" +
            "VALUES(?, ?, ?, ?)";

    @Override
    public boolean create(Director entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_INSERT_DIRECTOR);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setDate(4, entity.getBirthDate());
            updatedRecords = statement.executeUpdate();
            statement = connection.prepareStatement(ON_FOREIGN_KEY);
            statement.executeUpdate();
            if(updatedRecords == 0)
                return false;
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return updatedRecords > 0;
    }

    private static final String SQL_UPDATE_DIRECTOR =
            "UPDATE Director SET firstname=?, lastname=?, birth_date=? WHERE director_id=?";

    @Override
    public boolean update(Director entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_UPDATE_DIRECTOR);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setDate(3, entity.getBirthDate());
            statement.setInt(4, entity.getId());
            updatedRecords = statement.executeUpdate();
            statement = connection.prepareStatement(ON_FOREIGN_KEY);
            statement.executeUpdate();
            if(updatedRecords == 0)
                return false;
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return updatedRecords > 0;
    }
}
