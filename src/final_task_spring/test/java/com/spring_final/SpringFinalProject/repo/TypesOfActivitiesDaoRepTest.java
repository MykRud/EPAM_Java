package final_task_spring.test.java.com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TypesOfActivitiesDaoRepTest {

    @Autowired
    private TypesOfActivitiesDaoRep underTestTypeRepo;

    @AfterEach
    void tearDown() {
        underTestTypeRepo.deleteAll();
    }

    @Test
    void itShouldGetTypeByName() {
        // given
        TypeOfActivity expected = new TypeOfActivity();
        expected.setName("Physics");
        underTestTypeRepo.save(expected);

        // when
        TypeOfActivity actual = underTestTypeRepo.getByName("Physics");

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void itShouldNotGetTypeByNameAsTypeIsNotRepresented() {
        // given nothung

        // when
        TypeOfActivity actual = underTestTypeRepo.getByName("Physics");

        // then
        assertThat(actual).isNull();
    }
}