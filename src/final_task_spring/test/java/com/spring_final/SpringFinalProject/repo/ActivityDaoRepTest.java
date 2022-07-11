package final_task_spring.test.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ActivityDaoRepTest {

    @Autowired
    private ActivityDaoRep underTestActivityRepo;
    @Autowired
    private UserDaoRep userDaoRep;

    @BeforeEach
    void setUp() {
        underTestActivityRepo.deleteAll();
    }

    @Test
    void itShouldReturnActivityByName() {
        // given
        Activity expected = new Activity(null, "Football", "Active", "Playing football", 231234, new Date(24 - 01 - 2003), new Date(30 - 06 - 2022), null, new HashSet<>(), new HashSet<>());
        underTestActivityRepo.save(expected);

        // when
        Activity actual = underTestActivityRepo.getByName("Football");

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void itShouldNotReturnActivityByNameAsActivityIsNotRepresented() {
        // given nothing

        // when
        Activity actual = underTestActivityRepo.getByName("Football");

        // then
        assertThat(actual).isNull();
    }
}