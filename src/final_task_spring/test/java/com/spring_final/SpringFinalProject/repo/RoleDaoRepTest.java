package final_task_spring.test.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleDaoRepTest {

    @Autowired
    private RoleDaoRep underTestRoleRepo;

    @AfterEach
    void tearDown() {
        underTestRoleRepo.deleteAll();
    }

    @Test
    void itShouldFindRoleByName() {
        // given
        Role expected = new Role(null, "USER");
        underTestRoleRepo.save(expected);

        // when
        Role actual = underTestRoleRepo.findByName("USER");

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void itShouldNotFindRoleByNameAsRoleIsNotRepresented() {
        // given nothing

        // when
        Role actual = underTestRoleRepo.findByName("USER");

        // then
        assertThat(actual).isNull();
    }
}