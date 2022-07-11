package final_task_spring.main.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The repository for performing request-related CRUD operations with database
 *
 * @author Misha Rudyk
 * @see JpaRepository
 * @see ActivityRequest
 */
@Repository
@Transactional
public interface ActivityRequestDaoRep extends JpaRepository<ActivityRequest, Integer> {

    /**
     * Method that allows to get request with some user id and activity id
     *
     * @param userId     Id of user
     * @param activityId Id of activity
     * @return ActivityRequest instance
     */
    @Query("FROM ActivityRequest WHERE user_id = :userId AND activity_id = :activityId")
    List<ActivityRequest> findByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

}
