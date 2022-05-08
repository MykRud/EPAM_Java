package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Actor;
import task_advanced.task_7.Entities.Director;
import task_advanced.task_7.Entities.Movie;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MovieDAO extends DAO<Integer, Movie>{

    private boolean isConnected = false;
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
    private static final String SQL_SELECT_MOVIES_BY_RELEASE_DATE = "SELECT movie_id, name, release_date, country, lastname AS director " +
            "FROM MOVIE mov INNER JOIN DIRECTOR dir ON mov.director_id=dir.director_id WHERE mov.release_date=?";
    private static final String SQL_SELECT_MOVIES_BY_COUNTRY = "SELECT movie_id, name, release_date, country, lastname AS director " +
            "FROM MOVIE mov INNER JOIN DIRECTOR dir ON mov.director_id=dir.director_id WHERE mov.country=?";
    private static final String SQL_GET_NUMBER_OF_MOVIES =
            "SELECT COUNT(*) FROM Movie";

    public MovieDAO(){
        try {
            Movie.setNumberOfMovies(findNumberOfRecords());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private int findNumberOfRecords() throws DAOException {
        int numberOfMovies = 0;
        Statement statement = null;
        transaction.init(this);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_NUMBER_OF_MOVIES);
            resultSet.next();
            numberOfMovies = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            transaction.end(this);
        }
        return numberOfMovies;
    }

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
        transaction.init(this);
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
            transaction.end(this);
        }
        return movieList;
    }

    public List<Movie> findMoviesByDateRelease(String dateRelease) throws DAOException{
        if(!dateRelease.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"))
            return Collections.emptyList();
        transaction.init(this);
        List<Movie> listOfMovies = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_RELEASE_DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateRelease);
            Date date = new Date(utilDate.getTime());
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            listOfMovies = fillArray(resultSet);
        } catch (SQLException | ParseException e){
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return listOfMovies;
    }

    public List<Movie> findMoviesByCountry(String country) throws DAOException{
        transaction.init(this);
        List<Movie> listOfMovies = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_SELECT_MOVIES_BY_COUNTRY);
            statement.setString(1, country);
            ResultSet resultSet = statement.executeQuery();
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return listOfMovies;
    }

    private List<Movie> fillArray(ResultSet resultSet) throws DAOException{
        List<Movie> movieList = new ArrayList<>();
        try{
            while(resultSet.next()) {
                int movieId = resultSet.getInt("movie_id");
                String name = resultSet.getString("name");
                Date releaseDate = resultSet.getDate("release_date");
                String country = resultSet.getString("country");
                String director = resultSet.getString("director");
                List<Actor> listOfActors = actorDAO.findActorsByMovie(name);
                movieList.add(new Movie(movieId, name, releaseDate, country, director, listOfActors));
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
        return movieList;
    }

    @Override
    public List<Movie> findAll() throws DAOException {
        transaction.init(this);
        Statement statement = null;
        List<Movie> listOfMovies;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_MOVIES);
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            close(statement);
            if(isConnected)
                transaction.end(this);
        }
        return listOfMovies;
    }

    @Override
    public Movie findById(Integer id) throws DAOException {
        if(!isConnected)
            transaction.init(this);
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
            close(statement);
            if(!isConnected)
                transaction.end(this);
        }
        if(listOfMovies.isEmpty())
            return null;
        return listOfMovies.get(0);
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
    private static final String SQL_DELETE_MOVIE_BY_ID =
            "DELETE FROM Movie WHERE movie_id=?";
    private static final String SQL_DELETE_ACTORS_FROM_MOVIE =
            "DELETE FROM ActorsInMovies WHERE movie_id=?";

    @Override
    public boolean delete(Movie entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_MOVIE_BY_ID);
            statement.setInt(1, entity.getId());
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0){
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_DELETE_ACTORS_FROM_MOVIE);
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
            statement = connection.prepareStatement(SQL_DELETE_MOVIE_BY_ID);
            statement.setInt(1, id);
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_DELETE_ACTORS_FROM_MOVIE);
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

    private static final String SQL_INSERT_MOVIE = "INSERT INTO Movie(" +
            "movie_id, name, release_date, country, director_id)" +
            "VALUES(?, ?, ?, ?, ?)";

    @Override
    public boolean create(Movie entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_INSERT_MOVIE);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setDate(3, entity.getReleaseDate());
            statement.setString(4, entity.getCountry());
            List<Director> director = directorDAO.findDirectorsByLastName(entity.getDirectorName());
            statement.setInt(5, director.get(0).getId());
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

    private static final String SQL_UPDATE_MOVIE =
            "UPDATE Movie SET name=?, release_date=?, country=?, director_id=? WHERE movie_id=?";

    @Override
    public boolean update(Movie entity) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_UPDATE_MOVIE);
            statement.setString(1, entity.getName());
            statement.setDate(2, entity.getReleaseDate());
            statement.setString(3, entity.getCountry());
            List<Director> director = directorDAO.findDirectorsByLastName(entity.getDirectorName());
            statement.setInt(4, director.get(0).getId());
            statement.setInt(5, entity.getId());
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

    private static final String SQL_ADD_ACTOR_TO_MOVIE =
            "INSERT INTO ActorsInMovies(movie_id, actor_id)" +
                    "VALUES(?, ?)";

    public boolean addActorToMovie(Movie movie, Actor actor) throws DAOException{
        transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_ADD_ACTOR_TO_MOVIE);
            if(actorDAO.findById(actor.getId()) == null)
                actorDAO.create(actor);
            statement.setInt(1, movie.getId());
            statement.setInt(2, actor.getId());
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

    private static final String SQL_FIND_MOVIES_WITH_INTERVAL_LESS =
            "SELECT * FROM Movie WHERE release_date>DATE_SUB(NOW(), INTERVAL ? YEAR)";
    private static final String SQL_FIND_MOVIES_WITH_INTERVAL_GREATER =
            "SELECT * FROM Movie WHERE release_date<DATE_SUB(NOW(), INTERVAL ? YEAR)";


    // 1: Знайти всі фільми, що вийшли на екран у теперішньому та попередньому роках
    public List<Movie> findThisYearAndPreviousYearMovies() throws DAOException {
        if(!isConnected)
            transaction.init(this);
        List<Movie> listOfMovies = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQL_FIND_MOVIES_WITH_INTERVAL_LESS);
            statement.setInt(1, 2);
            ResultSet resultSet = statement.executeQuery();
            listOfMovies = fillArray(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            if(!isConnected)
                transaction.end(this);
        }
        if(listOfMovies.isEmpty())
            return Collections.emptyList();
        return listOfMovies;
    }

    private static final String SQL_DELETE_MOVIES_WITH_INTERVAL_GREATER =
            "DELETE FROM Movie WHERE release_date<DATE_SUB(NOW(), INTERVAL ? YEAR)";

    private static final String SQL_DELETE_RELATIONSHIP_BETWEEN_MOVIES_AND_ACTORS =
        "DELETE FROM ActorsInMovies WHERE NOT EXISTS( " +
                "SELECT * FROM Movie AS T1 WHERE T1.movie_id=ActorsInMovies.movie_id )";

    // 5: Видалити усі фільми, дата виходу яких є більшою за певну кількісті років
    public boolean deleteMoviesThatHaveReleaseDateGreaterThanN(int n) throws DAOException {
        if(!isConnected)
            transaction.init(this);
        PreparedStatement statement = null;
        int updatedRecords = 0;
        try {
            statement = connection.prepareStatement(OFF_FOREIGN_KEY);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_MOVIES_WITH_INTERVAL_GREATER);
            statement.setInt(1, n);
            updatedRecords = statement.executeUpdate();
            if(updatedRecords == 0) {
                statement = connection.prepareStatement(ON_FOREIGN_KEY);
                statement.executeUpdate();
                return false;
            }
            statement = connection.prepareStatement(SQL_DELETE_RELATIONSHIP_BETWEEN_MOVIES_AND_ACTORS);
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
}
