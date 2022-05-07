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

    public List<Movie> findMoviesByCountry(String countryName) throws DAOException {
        return movieDAO.findMoviesByCountry(countryName);
    }

    public List<Movie> findMoviesByDateRelease(String dateRelease) throws DAOException{
        return movieDAO.findMoviesByDateRelease(dateRelease);
    }

    public List<Movie> findMoviesByActor(String patternName) throws DAOException{
        return movieDAO.findMoviesByActor(patternName);
    }

    public List<Movie> findMoviesByName(String name) throws DAOException{
        return movieDAO.findMoviesByName(name);
    }

    @Override
    public boolean delete(Movie entity) throws DAOException {
        return movieDAO.delete(entity);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return movieDAO.delete(id);
    }

    @Override
    public boolean add(Movie entity) throws DAOException {
        return movieDAO.create(entity);
    }

    @Override
    public boolean alter(Movie entity) throws DAOException {
        return movieDAO.update(entity);
    }
}
