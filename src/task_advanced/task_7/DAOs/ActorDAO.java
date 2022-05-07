package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Actor;
import task_advanced.task_7.Entities.Movie;
import task_advanced.task_7.OutOfDateClasses.ConnectionCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends DAO<Integer, Actor> {

    private static final String SQL_SELECT_ACTORS_FROM_MOVIE =
            "SELECT ac.* FROM Actor ac, ActorsInMovies aim WHERE aim.actor_id=ac.actor_id " +
                    "AND aim.movie_id=(SELECT movie_id FROM Movie WHERE name=?)";

    public List<Actor> findActorByLastName(String name) throws DAOException{
        throw new UnsupportedOperationException();
    }

    public List<Actor> findActorsByBirthDate(Date birthDate) throws DAOException{
        throw new UnsupportedOperationException();
    }

    public List<Actor> findActorsByMovie(String movieName) throws DAOException{
        List<Actor> listOfActors = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACTORS_FROM_MOVIE);
            statement.setString(1, movieName);
            ResultSet resultSetOfActors = statement.executeQuery();

            while(resultSetOfActors.next()){
                int actorId = resultSetOfActors.getInt("actor_id");
                String firstNameActor = resultSetOfActors.getString("firstname");
                String lastNameActor = resultSetOfActors.getString("lastname");
                Date birth_date = resultSetOfActors.getDate("birth_date");
                listOfActors.add(new Actor(actorId, firstNameActor, lastNameActor, birth_date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return listOfActors;
    }

    @Override
    public List<Actor> findAll() throws DAOException {
        return null;
    }

    @Override
    public Actor findById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Actor entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public Actor create(Actor entity) throws DAOException {
        return null;
    }

    @Override
    public Actor update(Actor entity) throws DAOException {
        return null;
    }
}
