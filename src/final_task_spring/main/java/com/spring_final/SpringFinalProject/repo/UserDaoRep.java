package final_task_spring.main.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * The repository for performing user-related CRUD operations with database
 *
 * @author Misha Rudyk
 * @see JpaRepository
 * @see Activity
 */
@Repository
@Transactional
public interface UserDaoRep extends JpaRepository<User, Integer> {

    /**
     * Method that allows to get user by username
     *
     * @param username Username of some user
     * @return User instance
     */
    User getByUsername(String username);

    /**
     * Method that allows to delete all user roles
     *
     * @param userId Id of some user
     */
    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id=:userId", nativeQuery = true)
    void deleteAuthorities(@Param("userId") int userId);

}
