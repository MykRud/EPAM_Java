package final_task_spring.main.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The service with business logic related to user
 *
 * @author Misha Rudyk
 * @see UserDaoRep
 * @see User
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserDaoRep userDao;
    @Autowired
    private RoleDaoRep roleDao;
    @Autowired
    private ActivityDaoRep activityDao;
    @Autowired
    private ActivityRequestDaoRep requestDao;
    @Autowired
    private TypesOfActivitiesDaoRep typeDao;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method that allows to add user
     *
     * @param user User to add
     */
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        log.info("New user {} saved to the database", user.getUsername());
        User foundUser = getUser(user.getUsername());
        if (foundUser != null)
            addRoleToUser(foundUser.getUsername(), "USER");
    }

    /**
     * Method that allows to add role
     *
     * @param role Role to add
     */
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleDao.save(role);
    }

    /**
     * Method that allows to get user by id
     *
     * @param id Id of user
     * @return User with desired id
     */
    public User getUser(int id) {
        User user;
        try {
            user = userDao.findById(id).get();
        } catch (Exception e) {
            log.warn("User not found");
            return null;
        }
        log.info("User found");
        return user;
    }

    /**
     * Method that allows to add role to user
     *
     * @param username Username of desired user
     * @param roleName Name of desired role
     */
    public void addRoleToUser(String username, String roleName) {
        User user = userDao.getByUsername(username);
        Role role = roleDao.findByName(roleName);
        user.getRoles().add(role);
        log.info("Role {} added to user {}", roleName, username);
    }

    /**
     * Method that allows to get list of roles
     *
     * @return list of roles
     */
    public List<Role> getRoles() {
        List<Role> roles = roleDao.findAll();
        if (roles.isEmpty())
            log.info("No roles found");
        return roles;
    }

    /**
     * Method that allows to get role by its name
     *
     * @param name Name of role
     * @return Role with desired name
     */
    public Role getRole(String name) {
        Role role = roleDao.findByName(name);
        if (role == null) {
            log.warn("Role not found");
        } else {
            log.info("Role found");
        }
        return role;
    }

    /**
     * Method that allows to get user by username
     *
     * @param username Username of user
     * @return User with desired username
     */
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userDao.getByUsername(username);
    }

    /**
     * Method that allows to get list of users
     *
     * @return list of users
     */
    public List<User> getUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty())
            log.warn("No users found");
        return users;
    }

    /**
     * Method that allows to delete user by id
     *
     * @param id Id of user
     */
    public void deleteUser(int id) {
        activityDao.deleteByUserId(id);
        userDao.deleteAuthorities(id);
        userDao.deleteById(id);
        log.info("User deleted");
    }

    /**
     * Method that allows to update user profile
     *
     * @param user User that has to be updated
     */
    public void updateProfile(User user) {
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
            userDao.save(user);
        } catch (Exception e) {
            log.warn("User cannot be updated");
            return;
        }
        log.info("User updated");
    }

    /**
     * Method that allows to update information related to some user
     *
     * @param user User that has to be updated
     */
    public void updateUser(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            log.warn("User cannot be updated");
            return;
        }
        log.info("User updated");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User is not found");

        return new UserPrincipal(user);
    }

    public UserDaoRep getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoRep userDao) {
        this.userDao = userDao;
    }

    public RoleDaoRep getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDaoRep roleDao) {
        this.roleDao = roleDao;
    }

    public ActivityDaoRep getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDaoRep activityDao) {
        this.activityDao = activityDao;
    }

    public ActivityRequestDaoRep getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(ActivityRequestDaoRep requestDao) {
        this.requestDao = requestDao;
    }

    public TypesOfActivitiesDaoRep getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypesOfActivitiesDaoRep typeDao) {
        this.typeDao = typeDao;
    }
}
