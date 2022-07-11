package final_task_spring.main.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The repository for performing role-related CRUD operations with database
 *
 * @author Misha Rudyk
 * @see JpaRepository
 * @see Role
 */
@Repository
@Transactional
public interface RoleDaoRep extends JpaRepository<Role, Integer> {

    /**
     * Method that allows to get role by name
     *
     * @param name Name of role
     * @return
     */
    Role findByName(String name);

}
