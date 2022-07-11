package final_task_servlet.main.java.com.finaltask.org.example.realization.service;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.MySQLImpl.DaoFactoryImpl;
import com.finaltask.org.example.realization.dao.MySQLImpl.UserDaoImpl;
import com.finaltask.org.example.realization.dao.interfaces.*;
import com.finaltask.org.example.realization.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Main business service that allows you to execute business operations
 *
 * @see com.finaltask.org.example.realization.dao.DaoFactory
 * @see UserDao
 * @see ActivityDao
 * @see TypeOfActivitiesDao
 * @see ActivityRequestDao
 * @see CommonDao
 * @see User
 * @see Activity
 * @see TypeOfActivity
 * @see ActivityRequest
 *
 * @author Misha Rudyk
 */
public class BusinessServiceImpl {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DaoFactoryImpl daoFactory = new DaoFactoryImpl();

    private final UserDao userDao = daoFactory.createUserDao();
    private final ActivityDao activityDao = daoFactory.createActivityDao();
    private final TypeOfActivitiesDao typesDao = daoFactory.createTypesOfActivitiesDao();
    private final ActivityRequestDao requestDao = daoFactory.createRequestDao();

    {
        CommonDao.userDao = userDao;
        CommonDao.activityDao = activityDao;
        CommonDao.typeDao = typesDao;
        CommonDao.requestDao = requestDao;
    }

    /**
     * Method that add user
     * @param user User
     * @return if user has been added
     */
    public boolean addUser(User user){
        try {
            return userDao.add(user);
        } catch (DaoException e) {
            LOGGER.error("Cannot add user");
            return false;
        }
    }

    /**
     * Method that add user by ID
     * @param user User
     * @param id Id
     * @return if user has been added
     */
    public boolean addUser(User user, int id){
        try {
            return userDao.add(user, id);
        } catch (DaoException e) {
            LOGGER.error("Cannot add user");
            return false;
        }
    }

    /**
     * Method that returns all users
     * @return list of users
     */
    public List<User> getUsers(){

        try {
            return userDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get users");
            return Collections.emptyList();
        }
    }

    /**
     * Method that find user by id
     * @param id Id
     * @return User model
     */
    public User findUser(int id){
        try {
            return userDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find user");
            return null;
        }
    }

    /**
     * Method that find user by username
     * @param username Username
     * @return User model
     */
    public User findUser(String username){
        try {
            return userDao.find(username);
        } catch (DaoException e) {
            LOGGER.error("Cannot find user");
            return null;
        }
    }

    /**
     * Method that returns number of users
     * @return number of users
     */
    public int getNumberOfUsers(){
        try{
            return userDao.findNumberOfUsers();
        } catch(DaoException e) {
            LOGGER.error("Cannot find users");
            return -1;
        }
    }

    /**
     * Method that returns number of activities
     * @return number of activities
     */
    public int getNumberOfActivities(){
        try{
            return activityDao.getNumberOfActivities();
        } catch(DaoException e) {
            LOGGER.error("Cannot find activities");
            return -1;
        }
    }

    /**
     * Method that returns some number of users
     * @return list of users
     */
    public List<User> getUsersInLimit(int size, int page){
        try{
            return userDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get users");
            return Collections.emptyList();
        }
    }

    /**
     * Method that returns some number of activities
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimit(int size, int page){
        try{
            return activityDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    /**
     * Method that returns some number of activities sorted by name
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByName(int size, int page){
        try{
            return activityDao.findInLimitByName(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    /**
     * Method that returns some number of activities sorted by type
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByType(int size, int page){
        try{
            return activityDao.findInLimitByType(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    /**
     * Method that returns some number of activities sorted by number of users
     * @return list of activities
     */
    public List<Activity> getActivitiesInLimitByNumberOfUsers(int size, int page){
        try{
            return activityDao.findInLimitByNumberOfUsers(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    /**
     * Method that allows you to add activity
     * @param activity Activity to add
     * @return if activity has been added
     */
    public boolean addActivity(Activity activity){
        try {
            return activityDao.add(activity);
        } catch (DaoException e) {
            LOGGER.error("Cannot add activity");
            return false;
        }
    }

    /**
     * Method that allows you to add activity by id
     * @param activity Activity to add
     * @return if activity has been added
     */
    public boolean addActivity(Activity activity, int id){
        try {
            return activityDao.add(activity, id);
        } catch (DaoException e) {
            LOGGER.error("Cannot add activity");
            return false;
        }
    }

    /**
     * Method that returns all activities
     * @return list of activities
     */
    public List<Activity> getActivities(){

        try {
            return activityDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    /**
     * Method that update activity in datasource
     * @param activity Activity to update
     */
    public void updateActivity(Activity activity){

        try {
            activityDao.update(activity);
        } catch (DaoException e) {
            LOGGER.error("Cannot шзвфеу activities");
        }
    }

    /**
     * Method that find activity by id
     * @param id Id
     * @return Activity model
     */
    public Activity findActivity(int id){
        try {
            return activityDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find activity");
            return null;
        }
    }

    /**
     * Method that find activity by name
     * @param name Name
     * @return Activity model
     */
    public Activity findActivity(String name){
        try {
            return activityDao.find(name);
        } catch (DaoException e) {
            LOGGER.error("Cannot find activity");
            return null;
        }
    }

    /**
     * Method that delete activity from datasource by id
     * @param id Id
     * @return if activity has been deleted
     */
    public boolean deleteActivity(int id){
        try{
            return activityDao.delete(id);
        } catch(DaoException e){
            LOGGER.error("Cannot delete activity");
            return false;
        }
    }

    /**
     * Method that allows to mark time for some activity
     * @param id Id of activity
     * @param user User
     * @param duration Time to mark
     * @return if time has been marked
     */
    public boolean takeActivityTime(int id, User user, int duration){
        try{
            Activity activity = activityDao.find(id);
            if(activity.getStatus().equals("Active")){
                activity.setDuration(duration);
                activityDao.update(activity);
                return true;
            } else
                return false;
        } catch(DaoException e){
            LOGGER.error("Cannot mark time for activity");
            return false;
        }
    }

    /**
     * Method that allows you to add type
     * @param type Type to add
     * @return if type has been added
     */
    public boolean addType(TypeOfActivity type){
        try {
            return typesDao.add(type);
        } catch (DaoException e) {
            LOGGER.error("Cannot add type");
            return false;
        }
    }

    /**
     * Method that returns all types
     * @return list of types
     */
    public List<TypeOfActivity> getTypes(){

        try {
            return typesDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get types");
            return Collections.emptyList();
        }
    }

    /**
     * Method that find type by id
     * @param id Id
     * @return Type model
     */
    public TypeOfActivity findType(int id){
        try {
            return typesDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find type");
            return null;
        }
    }

    /**
     * Method that find activity by name
     * @param name Name
     * @return Type model
     */
    public TypeOfActivity findType(String name){
        try {
            return typesDao.findTypeWithoutReferences(name);
        } catch (DaoException e) {
            LOGGER.error("Cannot find type");
            return null;
        }
    }

    /**
     * Method that allows you to delete user from datasource by id
     * @param userId User Id
     * @return if user has been deleted
     */
    public boolean deleteUser(int userId) {
        try {
            return userDao.delete(userId);
        } catch (DaoException e) {
            LOGGER.error("Cannot delete user");
            return false;
        }
    }

    /**
     * Method that allows you to update user record in datasource
     * @param user User to update
     */
    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            LOGGER.error("Cannot update user");
        }
    }

    /**
     * Method that returns number of requests
     * @return number of requests
     */
    public int getNumberOfRequests(){
        try {
            return requestDao.getNumberOfActivityRequests();
        } catch (DaoException e) {
            LOGGER.error("Cannot get requests");
            return -1;
        }
    }

    /**
     * Method that returns some number of requests
     * @return list of requests
     */
    public List<ActivityRequest> getRequestsInLimit(int size, int page){
        try{
            return requestDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get requests");
            return Collections.emptyList();
        }
    }

    /**
     * Method that allows user to make request for adding activity
     * @param user User
     * @param activity Activity
     */
    public void makeAddRequest(User user, Activity activity) {
        try {
            ActivityRequest activityRequest = requestDao.findByUserAndActivity(user, activity);
            if(activityRequest != null)
                return;

        if(activity.getStatus().equals("Completed")) {
            LOGGER.warn("Activity is already completed");
            return;
        }
        else if(activity.getStatus().equals("Active")){
            LOGGER.warn("User has already activated this activity");
            return;
        }

        activityRequest = new ActivityRequest();
        activityRequest.setUser(user);
        activityRequest.setActivity(activity);
        activityRequest.setAction("Add");
        activityRequest.setStatus("Pending");

        requestDao.add(activityRequest);


        } catch (DaoException e) {
            LOGGER.error("Cannot add request");
        }
    }

    /**
     * Method that allows user to make request for complete activity
     * @param user User
     * @param activity Activity
     */
    public void makeCompleteRequest(User user, Activity activity){
        try {
            ActivityRequest request = requestDao.findByUserAndActivity(user, activity);
            if(request == null)
                return;

            if(activity.getStatus().equals("Completed")) {
                LOGGER.warn("Activity is already completed");
                return;
            }
            else if(activity.getStatus().equals("Pending")) {
                LOGGER.warn("Activity is not activated yet");
                return;
            }
            else if(activity.getStatus().equals("Active")){
                request = new ActivityRequest();
                request.setUser(user);
                request.setActivity(activity);
                request.setAction("Complete");
                request.setStatus("Pending");

                requestDao.add(request);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that allows you to add request for adding activity in datasource
     * @param request Request
     */
    public void addRequest(ActivityRequest request, int id){
        try{
            requestDao.add(request, id);
        } catch (DaoException e){
            LOGGER.warn("Cannot add request");
        }
    }

    /**
     * Method that allows you to find request by id
     * @param id Id
     * @return Request model
     */
    public ActivityRequest findRequest(int id){
        try {
            return requestDao.find(id);
        } catch (DaoException e) {
            LOGGER.warn("Cannot find request");
            return null;
        }
    }

    /**
     * Method that allows admin to approve requests
     * @param activityRequest Request to approve
     */
    public void approveRequest(ActivityRequest activityRequest){
        try {
            Activity activity = activityRequest.getActivity();
            User user = activityRequest.getUser();

            String status = activity.getStatus();
            if(status.equals("Pending")){
                activity.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
                activity.setStatus("Active");
                activity.addUser(user);
                user.addActivity(activity);
                activityRequest.setStatus("Approved");

                activityDao.update(activity);
            } else if(status.equals("Active")){
                activity.addUser(user);
                user.addActivity(activity);
                activityRequest.setStatus("Approved");

                activityDao.update(activity);
            } else if(status.equals("Completed")){
                activityRequest.setStatus("Rejected");
            }

            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot approve request");
        }
    }

    /**
     * Method that allows you to add request for completing activity in datasource
     * @param activityRequest Request
     */
    public void completeRequest(ActivityRequest activityRequest) {
        try {
            Activity activity = activityRequest.getActivity();
            User user = activityRequest.getUser();

            String status = activity.getStatus();
            if(status.equals("Pending")){
                LOGGER.warn("Activity is not activated yet");
                return;
            } else if(status.equals("Active")){
                activity.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
                activity.setStatus("Completed");
                activityRequest.setStatus("Approved");

                activityDao.update(activity);
            } else if(status.equals("Completed")){
                activityRequest.setStatus("Rejected");
            }

            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot complete request");
        }
    }

    /**
     * Method that allows admin to reject requests
     * @param activityRequest Request to reject
     */
    public void rejectRequest(ActivityRequest activityRequest) {
        try {
            activityRequest.setStatus("Rejected");
            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot reject request");
        }
    }

    /**
     * Method that allows admin to set authorities for user
     * @param user_id User
     * @param authority Authority to set
     */
    public void setAuthority(int user_id, Authority authority){
        try{
            UserDaoImpl userDao = new UserDaoImpl();
            List<Authority> authorityList = userDao.findAuthority(user_id);
            for(Authority currentAuthority : authorityList){
                if(currentAuthority == authority)
                    return;
            }
            userDao.addAuthority(user_id, authority);
        } catch (DaoException e){
            LOGGER.warn("Cannot set authority");
        }
    }

    /**
     * Method that allows admin to delete authority from user
     * @param user_id User
     * @param authority Authority to delete
     */
    public void deleteUserAuthority(int user_id, Authority authority){
        try{
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.deleteUserAuthority(user_id, authority);
        } catch (DaoException e){
            LOGGER.warn("Cannot delete authority for user");
        }
    }
}
