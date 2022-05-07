package task_advanced.task_7.Services;

import task_advanced.task_7.DAOs.ActorDAO;
import task_advanced.task_7.DAOs.*;
import task_advanced.task_7.Entities.Entity;
import task_advanced.task_7.EntityTransaction;

import java.util.List;

public abstract class AbstractService<K, L extends Entity> {
    public static final EntityTransaction transaction = new EntityTransaction();
    public static final ActorDAO actorDAO = DAO.actorDAO;
    public static final  MovieDAO movieDAO = DAO.movieDAO;
    public static final  DirectorDAO directorDAO = DAO.directorDAO;

    public abstract List<L> findAll() throws DAOException;

    public abstract L findById(K id) throws DAOException;

    public abstract boolean delete(L entity) throws DAOException;

    public abstract boolean delete(K id) throws DAOException;

    public abstract boolean add(L entity) throws DAOException;

    public abstract boolean alter(L entity) throws DAOException;

}
