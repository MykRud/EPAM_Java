package final_task_servlet.realization.dao.interfaces;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.model.Entity;

import java.util.List;

public interface Pageable<K extends Entity> {

    List<K> findInLimit(int size, int page) throws DaoException;

}
