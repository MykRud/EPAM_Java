package final_task_servlet.main.java.com.finaltask.org.example.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Entity;

import java.util.List;

/**
 * Interface responsible for pagination of pages
 *
 * @see CommonDao
 * @see Entity
 *
 * @author Misha Rudyk
 */
public interface Pageable<K extends Entity> {

    /**
     * Method that returns list of entities in limit
     * @param size how many entities you want to receive
     * @param page page
     * @return list of entities
     * @throws DaoException
     */
    List<K> findInLimit(int size, int page) throws DaoException;

}
