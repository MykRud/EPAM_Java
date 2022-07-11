package final_task_spring.test.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDaoRep userRepo;
    @Mock
    private ActivityDaoRep activityRepo;
    @Mock
    private ActivityRequestDaoRep requestRepo;
    @Mock
    private TypesOfActivitiesDaoRep typeRepo;
    @Mock
    private RoleDaoRep roleRepo;
    private UserService underTestUserService;

    @BeforeEach
    void setUp() {
        underTestUserService = new UserService(new BCryptPasswordEncoder());
        underTestUserService.setUserDao(userRepo);
        underTestUserService.setRoleDao(roleRepo);
        underTestUserService.setActivityDao(activityRepo);
        underTestUserService.setRequestDao(requestRepo);
        underTestUserService.setTypeDao(typeRepo);
    }

    @Test
    void addUser() {
        // given
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("john");
        user.setPassword("1234");

        // when
        underTestUserService.addUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepo).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

        // given(userRepo.selectExistsUsername(user.getUsername()))
        //              .willReturn(true);

        // or

        // given(userRepo.selectExistsUsername(anyString()))
        //              .willReturn(true);

        // assertThatThrownBy(()->underTestUserService.addUser(user))
        //              .instanceOf(BadRequestException.class)
        //              .hasMessageContaining("Username " + user.getUsername() + " taken");

        // to ensure that method is not invoked. our mock will never save user
        // verify(userRepo, never()).save(any));
    }

    @Test
    void saveRole() {
        // given
        Role role = new Role();
        role.setId(1);
        role.setName("USER");

        // when
        underTestUserService.saveRole(role);

        // then
        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);

        verify(roleRepo).save(roleArgumentCaptor.capture());

        Role capturedRole = roleArgumentCaptor.getValue();

        assertThat(capturedRole).isEqualTo(role);
    }

    @Test
    void canGetUserByUsername() {
        // when
        underTestUserService.getUser("john");

        // then
        verify(userRepo).getByUsername("john");
    }

    @Test
    void addRoleToUser() {
        // given
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("john");
        user.setPassword("1234");
        when(userRepo.getByUsername("john")).thenReturn(user);

        Role userRole = new Role(null, "USER");
        when(roleRepo.findByName("USER")).thenReturn(userRole);

        // when
        underTestUserService.addRoleToUser("john", "USER");

        // then
        verify(userRepo).getByUsername("john");
        verify(roleRepo).findByName("USER");
    }

    @Test
    void getRoles() {
        // when
        underTestUserService.getRoles();

        // then
        verify(roleRepo).findAll();
    }

    @Test
    void getRole() {
        // when
        underTestUserService.getRole("USER");

        // then
        verify(roleRepo).findByName("USER");
    }

    @Test
    void canGetUserById() {
        // when
        underTestUserService.getUser(1);

        // then
        verify(userRepo).findById(1);
    }

    @Test
    void getUsers() {
        // when
        underTestUserService.getUsers();

        // then
        verify(userRepo).findAll();
    }

    @Test
    void deleteUser() {
        // when
        underTestUserService.deleteUser(1);

        // then
        verify(userRepo).deleteById(1);

    }

    @Test
    void updateProfile() {
        // given
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("john");
        user.setPassword("1234");
        underTestUserService.addUser(user);

        // when
        user.setFirstName("Johnny");
        user.setUsername("johnny");
        underTestUserService.updateProfile(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepo, times(2)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void updateUser() {
        // given
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Travolta");
        user.setUsername("john");
        user.setPassword("1234");
        underTestUserService.addUser(user);

        // when
        user.setFirstName("Johnny");
        user.setUsername("johnny");
        underTestUserService.updateUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepo, times(2)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }
}