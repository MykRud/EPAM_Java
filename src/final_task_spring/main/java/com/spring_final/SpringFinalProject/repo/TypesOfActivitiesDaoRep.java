package final_task_spring.main.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The repository for performing type-related CRUD operations with database
 *
 * @author Misha Rudyk
 * @see JpaRepository
 * @see Activity
 */
@Repository
@Transactional
public interface TypesOfActivitiesDaoRep extends JpaRepository<TypeOfActivity, Integer> {

    /**
     * Method that allows to get type by name
     *
     * @param name Name of type
     * @return TypeOfActivity instance
     */
    TypeOfActivity getByName(String name);

}
