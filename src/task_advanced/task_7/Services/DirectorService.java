package task_advanced.task_7.Services;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Director;

import java.util.List;

public class DirectorService extends AbstractService<Integer, Director>{
    @Override
    public List<Director> findAll() throws DAOException {
        return directorDAO.findAll();
    }

    @Override
    public Director findById(Integer id) throws DAOException {
        return directorDAO.findById(id);
    }

    public List<Director> findDirectorsByLastName(String name) throws DAOException{
        return directorDAO.findDirectorsByLastName(name);
    }

    public List<Director> findDirectorsByBirthDate(String birthDate) throws DAOException{
        return directorDAO.findDirectorsByBirthDate(birthDate);
    }

    public Director findDirectorOfMovie(String movieName) throws DAOException{
        return directorDAO.findDirectorOfMovie(movieName);

    }

    @Override
    public boolean delete(Director entity) throws DAOException {
        boolean isDeleted = directorDAO.delete(entity);
        if(isDeleted)
            Director.decreaseNumberOfDirector();
        return isDeleted;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        boolean isDeleted = directorDAO.delete(id);
        if(isDeleted)
            Director.decreaseNumberOfDirector();
        return isDeleted;
    }

    @Override
    public boolean add(Director entity) throws DAOException {
        return directorDAO.create(entity);
    }

    @Override
    public boolean alter(Director entity) throws DAOException {
        return directorDAO.update(entity);
    }
}
