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

    @Override
    public boolean delete(Actor entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Actor add(Actor entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Actor alter(Actor entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
