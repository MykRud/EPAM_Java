package task_advanced.task_7.Services;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Actor;
import task_advanced.task_7.Entities.Entity;

import java.util.List;

public class ActorService extends AbstractService<Integer, Actor>{
    @Override
    public List<Actor> findAll() throws DAOException {
        return actorDAO.findAll();
    }

    @Override
    public Actor findById(Integer id) throws DAOException {
        return actorDAO.findById(id);
    }

    public List<Actor> findActorsByMovie(String movieName) throws DAOException{
        return actorDAO.findActorsByMovie(movieName);
    }

    public List<Actor> findActorsByBirthDate(String birthDate) throws DAOException{
        return actorDAO.findActorsByBirthDate(birthDate);
    }

    public List<Actor> findActorByLastName(String name) throws DAOException{
        return actorDAO.findActorByLastName(name);
    }

    @Override
    public boolean delete(Actor entity) throws DAOException {
        return actorDAO.delete(entity);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return actorDAO.delete(id);
    }

    @Override
    public boolean add(Actor entity) throws DAOException {
        return actorDAO.create(entity);
    }

    @Override
    public boolean alter(Actor entity) throws DAOException {
        return actorDAO.update(entity);
    }
}
