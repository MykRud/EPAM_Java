package final_task_spring.test.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.repo.TypesOfActivitiesDaoRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TypeOfActivityServiceTest {

    @Mock
    private TypesOfActivitiesDaoRep typeRepo;

    private TypeOfActivityService underTestTypeService;

    @BeforeEach
    void setUp() {
        underTestTypeService = new TypeOfActivityService();
        underTestTypeService.setTypeDao(typeRepo);
    }

    @Test
    void addType() {
        // given
        TypeOfActivity type = new TypeOfActivity();
        type.setName("Physical");

        // when
        underTestTypeService.addType(type);

        // then
        ArgumentCaptor<TypeOfActivity> typeArgumentCaptor = ArgumentCaptor.forClass(TypeOfActivity.class);

        verify(typeRepo).save(typeArgumentCaptor.capture());

        TypeOfActivity capturedType = typeArgumentCaptor.getValue();

        assertThat(capturedType).isEqualTo(type);
    }

    @Test
    void getTypes() {
        // when
        underTestTypeService.getTypes();

        // then
        verify(typeRepo).findAll();
    }

    @Test
    void findType() {
        // when
        underTestTypeService.getType("Physical");

        // then
        verify(typeRepo).getByName("Physical");
    }

    @Test
    void testFindType() {
        // when
        underTestTypeService.findType(1);

        // then
        verify(typeRepo).findById(1);
    }

    @Test
    void getType() {
        // when
        underTestTypeService.getType("Physical");

        // then
        verify(typeRepo).getByName("Physical");
    }
}