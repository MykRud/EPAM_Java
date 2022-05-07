package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Actor;
import task_advanced.task_7.Entities.Movie;
import task_advanced.task_7.OutOfDateClasses.ConnectionCreator;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MovieDAO extends DAO<Integer, Movie>{

    private static final String SQL_SELECT_ALL_MOVIES =
            "SELECT movie_id, name, release_date, country, lastname AS director " +
                    "FROM MOVIE mov INNER JOIN DIRECTOR dir ON mov.director_id=dir.director_id";
    private static final String SQL_SELECT_MOVIES_BY_NAME =
            "SELECT movie_id, name, release_date, country, lastname AS director " +
                    "FROM MOVIE mov INNER JOIN DIRECTOR dir ON mov.director_id=dir.director_id WHERE mov.name=?";
    private static final String SQL_SELECT_MOVIES_BY_ACTOR =
            "SELECT movie_id FROM ActorsInMovies WHERE actor_id=(SELECT actor_id FROM Actor WHERE lastname=?)";
    private static final String SQL_SELECT_MOVIES_BY_ID = "SELECT movie_id, name, release_date, country, lastname AS director " +
            "FROM MOVIE mov INNER JOIN DIRECTOR dir ON mov.director_id=dir.director_id WHERE mov.movie_id=?";

    public List<Movie> findMoviesByName(String name) throws DAOException{
        transaction.init(this);
        List<Movie> listOfMovies = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return listOfMovies;
    }

    public List<Movie> findMoviesByActor(String patternName) throws DAOException{
        getTransaction().init(this);
        List<Movie> movieList = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_ACTOR);
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                movieList.add(findById(resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            getTransaction().end();
        }
        return movieList;
    }

    public List<Movie> findMoviesByDateRelease(Date dateRelease) throws DAOException{
        throw new UnsupportedOperationException();
    }

    public List<Movie> findMoviesByCountry(String country) throws DAOException{
        throw new UnsupportedOperationException();
    }

    private List<Movie> fillArray(ResultSet resultSet) throws DAOException{
        List<Movie> movieList = new ArrayList<>();
        try{
            while(resultSet.next()) {
                int movieId = resultSet.getInt("movie_id");
                String name = resultSet.getString("name");
                getTransaction().init(actorDAO);
                List<Actor> listOfActors = actorDAO.findActorsByMovie(name);
                Date releaseDate = resultSet.getDate("release_date");
                String country = resultSet.getString("country");
                String director = resultSet.getString("director");
                movieList.add(new Movie(movieId, name, releaseDate, country, director, listOfActors));
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> findAll() throws DAOException {
        getTransaction().init(this);
        Statement statement = null;
        List<Movie> listOfMovies;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MOVIES);
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            getTransaction().end();
            close(statement);
        }
        return listOfMovies;
    }

    @Override
    public Movie findById(Integer id) throws DAOException {
        getTransaction().init(this);
        List<Movie> listOfMovies = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            getTransaction().end();
        }
        if(listOfMovies.isEmpty())
            return null;
        return listOfMovies.get(0);
    }

    @Override
    public boolean delete(Movie entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public Movie create(Movie entity) throws DAOException {
        return null;
    }

    @Override
    public Movie update(Movie entity) throws DAOException {
        return null;
    }
}
