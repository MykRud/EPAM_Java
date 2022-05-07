package task_advanced.task_7.DAOs;

import task_advanced.task_7.Entities.Director;

import java.util.List;

public class DirectorDAO extends DAO<Integer, Director> {

    public List<Director> findDirectorByLastName(String name) throws DAOException{
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Director> findAll() throws DAOException {
        return null;
    }

    @Override
    public Director findById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public boolean delete(Director entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public Director create(Director entity) throws DAOException {
        return null;
    }

    @Override
    public Director update(Director entity) throws DAOException {
        return null;
    }
}
