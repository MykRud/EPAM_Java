package task_advanced.task_7.Services;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Director;
import task_advanced.task_7.Entities.Entity;

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

    @Override
    public boolean delete(Director entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Director add(Director entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Director alter(Director entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
