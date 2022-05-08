package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Actor;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActorDAO extends DAO<Integer, Actor> {

    private boolean isConnected = false;
    private static final String SQL_SELECT_ACTORS = "SELECT * FROM Actor";
    private static final String SQL_SELECT_ACTOR_BY_ID = "SELECT * FROM Actor WHERE actor_id=?";
    private static final String SQL_SELECT_ACTORS_BY_LASTNAME = "SELECT * FROM Actor WHERE lastname=?";
    private static final String SQL_SELECT_ACTORS_BY_BIRTH_DATE = "SELECT * FROM Actor WHERE birth_date=?";
    private static final String SQL_SELECT_ACTORS_FROM_MOVIE =
            "SELECT ac.* FROM Actor ac, ActorsInMovies aim WHERE aim.actor_id=ac.actor_id " +
                    "AND aim.movie_id=(SELECT movie_id FROM Movie WHERE name=?)";
    private static final String SQL_GET_NUMBER_OF_ACTORS =
            "SELECT COUNT(*) FROM Actor";

    public ActorDAO(){
        try {
            Actor.setNumberOfActors(findNumberOfRecords());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private int findNumberOfRecords() throws DAOException {
        int numberOfActors = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_ACTORS);
            resultSet.next();
            numberOfActors = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return numberOfActors;
    }


    public List<Actor> findActorByLastName(String name) throws DAOException{
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTORS_BY_LASTNAME);
            statement.setString(1, name);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfActors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfActors;
    }

    public List<Actor> findActorsByBirthDate(String birthDate) throws DAOException{
        if(!birthDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"))
            return Collections.emptyList();
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTORS_BY_BIRTH_DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(birthDate);
            Date date = new Date(utilDate.getTime());
            statement.setDate(1, date);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfActors = fillArray(resultSetOfActors);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfActors;
    }

    // 2: Вивести усю інформацію про актерів, за знімалися у фільмі
    public List<Actor> findActorsByMovie(String movieName) throws DAOException{
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTORS_FROM_MOVIE);
            statement.setString(1, movieName);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfActors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            //if(!movieDAO.isConnected())
                //transaction.end(this);
        }
        return listOfActors;
    }

    @Override
    public List<Actor> findAll() throws DAOException {
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSetOfActors = statement.executeQuery(SQL_SELECT_ACTORS);
            listOfActors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            transaction.end(this);
        }
        return listOfActors;
    }

    @Override
    public Actor findById(Integer id) throws DAOException {
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTOR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfActors = fillArray(resultSetOfActors);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            if(!movieDAO.isConnected())
                transaction.end(this);
        }
        if(listOfActors.isEmpty())
            return null;
        return listOfActors.get(0);
    }

    private List<Actor> fillArray(ResultSet resultSetOfActors) throws SQLException {
        List<Actor> listOfActors = new ArrayList<>();
        while(resultSetOfActors.next()){
            int actorId = resultSetOfActors.getInt("actor_id");
            String firstNameActor = resultSetOfActors.getString("firstname");
            String lastNameActor = resultSetOfActors.getString("lastname");
            Date birth_date = resultSetOfActors.getDate("birth_date");
            listOfActors.add(new Actor(actorId, firstNameActor, lastNameActor, birth_date));
        }
        return listOfActors;
    }

    @Override
    public boolean isConnected(){
        return isConnected;
    }

    @Override
    public void setIfConnected(boolean isConnected){
        this.isConnected = isConnected;
    }

    private static final String SQL_DELETE_ACTOR_BY_ID =
            "DELETE FROM Actor WHERE actor_id=?";
    private static final String SQL_DELETE_ACTOR_FROM_MOVIE =
            "DELETE FROM ActorsInMovies WHERE actor_id=?";

    @Override
    public boolean delete(Actor entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_ACTOR_BY_ID);
            statement.setInt(1, entity.getId());
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_DELETE_ACTOR_FROM_MOVIE);
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
            statement = connection.prepareStatement(SQL_DELETE_ACTOR_BY_ID);
            statement.setInt(1, id);
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_DELETE_ACTOR_FROM_MOVIE);
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

    private static final String SQL_INSERT_ACTOR = "INSERT INTO Actor(" +
            "actor_id, firstname, lastname, birth_date)" +
            "VALUES(?, ?, ?, ?)";

    @Override
    public boolean create(Actor entity) throws DAOException {
        transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_INSERT_ACTOR);
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

    private static final String ON_FOREIGN_KEY = "SET foreign_key_checks = 1";
    private static final String OFF_FOREIGN_KEY = "SET foreign_key_checks = 0";
    private static final String SQL_UPDATE_ACTOR =
            "UPDATE Actor SET firstname=?, lastname=?, birth_date=? WHERE actor_id=?";

    @Override
    public boolean update(Actor entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_UPDATE_ACTOR);
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

    // 3: Вивести усю інформацію про актерів, за знімалися як мінімум у N фільмах
    private static final String SQL_FIND_ACTORS_THAT_ACT_AT_LEAST_IN_N_MOVES =
            "SELECT ac.* FROM Actor ac, ActorsInMovies aim " +
                    "WHERE ac.actor_id=aim.actor_id GROUP BY aim.actor_id HAVING count(*) > ?";

    public List<Actor> findActorsThatActAtLeastInNMovies(Integer n) throws DAOException {
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND_ACTORS_THAT_ACT_AT_LEAST_IN_N_MOVES);
            statement.setInt(1, n);
            ResultSet resultSetOfActors = statement.executeQuery();
            listOfActors = fillArray(resultSetOfActors);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            if(!movieDAO.isConnected())
                transaction.end(this);
        }
        if(listOfActors.isEmpty())
            return null;
        return listOfActors;
    }

    // 4: Вивести інформацію про акторів, які бути режисерами хоча би одного із фільмів
    private static final String SQL_FIND_ACTORS_THAT_ARE_DIRECTORS_IN_AT_LEAST_ONE_MOVIE =
            "SELECT ac.* FROM Movie mov, Actor ac, Director dir " +
                    "WHERE mov.director_id=dir.director_id " +
                    "AND dir.lastname=ac.lastname";

    public List<Actor> findActorsThatAreDirectorsInAtLeastOneMovie() throws DAOException {
        transaction.init(this);
        List<Actor> listOfActors = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSetOfActors = statement.executeQuery(SQL_FIND_ACTORS_THAT_ARE_DIRECTORS_IN_AT_LEAST_ONE_MOVIE);
            listOfActors = fillArray(resultSetOfActors);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            if(!movieDAO.isConnected())
                transaction.end(this);
        }
        if(listOfActors.isEmpty())
            return null;
        return listOfActors;
    }
}
