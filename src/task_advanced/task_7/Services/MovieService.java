package task_advanced.task_7.Services;

import task_advanced.task_7.DAOs.ActorDAO;
import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Entity;
import task_advanced.task_7.Entities.Movie;

import java.util.List;

public class MovieService extends AbstractService<Integer, Movie>{
    @Override
    public List<Movie> findAll() throws DAOException {
        return movieDAO.findAll();
    }

    @Override
    public Movie findById(Integer id) throws DAOException {
        return movieDAO.findById(id);
    }

    @Override
    public boolean delete(Movie entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Movie add(Movie entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Movie alter(Movie entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
