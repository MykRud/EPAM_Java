package final_task_servlet.realization.service;

import com.finaltask.org.example.realization.dao.DaoException;
import com.finaltask.org.example.realization.dao.MySQLImpl.*;
import com.finaltask.org.example.realization.dao.interfaces.*;
import com.finaltask.org.example.realization.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    public boolean addUser(User user){
        try {
            return userDao.add(user);
        } catch (DaoException e) {
            LOGGER.error("Cannot add user");
            return false;
        }
    }

    public boolean addUser(User user, int id){
        try {
            return userDao.add(user, id);
        } catch (DaoException e) {
            LOGGER.error("Cannot add user");
            return false;
        }
    }

    public List<User> getUsers(){

        try {
            return userDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get users");
            return Collections.emptyList();
        }
    }

    public User findUser(int id){
        try {
            return userDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find user");
            return null;
        }
    }

    public User findUser(String username){
        try {
            return userDao.find(username);
        } catch (DaoException e) {
            LOGGER.error("Cannot find user");
            return null;
        }
    }

    public int getNumberOfUsers(){
        try{
            return userDao.findNumberOfUsers();
        } catch(DaoException e) {
            LOGGER.error("Cannot find users");
            return -1;
        }
    }

    public int getNumberOfActivities(){
        try{
            return activityDao.getNumberOfActivities();
        } catch(DaoException e) {
            LOGGER.error("Cannot find activities");
            return -1;
        }
    }

    public List<User> getUsersInLimit(int size, int page){
        try{
            return userDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get users");
            return Collections.emptyList();
        }
    }

    public List<Activity> getActivitiesInLimit(int size, int page){
        try{
            return activityDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    public boolean addActivity(Activity activity){
        try {
            return activityDao.add(activity);
        } catch (DaoException e) {
            LOGGER.error("Cannot add activity");
            return false;
        }
    }

    public boolean addActivity(Activity activity, int id){
        try {
            return activityDao.add(activity, id);
        } catch (DaoException e) {
            LOGGER.error("Cannot add activity");
            return false;
        }
    }

    public List<Activity> getActivities(){

        try {
            return activityDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get activities");
            return Collections.emptyList();
        }
    }

    public void updateActivity(Activity activity){

        try {
            activityDao.update(activity);
        } catch (DaoException e) {
            LOGGER.error("Cannot шзвфеу activities");
        }
    }

    public Activity findActivity(int id){
        try {
            return activityDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find activity");
            return null;
        }
    }

    public boolean deleteActivity(int id){
        try{
            return activityDao.delete(id);
        } catch(DaoException e){
            LOGGER.error("Cannot delete activity");
            return false;
        }
    }

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

    // Types

    public boolean addType(TypeOfActivity type){
        try {
            return typesDao.add(type);
        } catch (DaoException e) {
            LOGGER.error("Cannot add type");
            return false;
        }
    }

    public List<TypeOfActivity> getTypes(){

        try {
            return typesDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Cannot get types");
            return Collections.emptyList();
        }
    }

    public TypeOfActivity findType(int id){
        try {
            return typesDao.find(id);
        } catch (DaoException e) {
            LOGGER.error("Cannot find type");
            return null;
        }
    }

    public TypeOfActivity findType(String name){
        try {
            return typesDao.findTypeWithoutReferences(name);
        } catch (DaoException e) {
            LOGGER.error("Cannot find type");
            return null;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            return userDao.delete(userId);
        } catch (DaoException e) {
            LOGGER.error("Cannot delete user");
            return false;
        }
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            LOGGER.error("Cannot update user");
        }
    }

    public int getNumberOfRequests(){
        try {
            return requestDao.getNumberOfActivityRequests();
        } catch (DaoException e) {
            LOGGER.error("Cannot get requests");
            return -1;
        }
    }

    public List<ActivityRequest> getRequestsInLimit(int size, int page){
        try{
            return requestDao.findInLimit(size, page);
        } catch (DaoException e){
            LOGGER.error("Cannot get requests");
            return Collections.emptyList();
        }
    }

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

    public void addRequest(ActivityRequest request, int id){
        try{
            requestDao.add(request, id);
        } catch (DaoException e){
            LOGGER.warn("Cannot add request");
        }
    }

    public ActivityRequest findRequest(int id){
        try {
            return requestDao.find(id);
        } catch (DaoException e) {
            LOGGER.warn("Cannot find request");
            return null;
        }
    }

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
            } else if(status.equals("Complete")){
                activityRequest.setStatus("Rejected");
            }

            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot approve request");
        }
    }

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
            } else if(status.equals("Complete")){
                activityRequest.setStatus("Rejected");
            }

            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot complete request");
        }
    }


    public void rejectRequest(ActivityRequest activityRequest) {
        try {
            activityRequest.setStatus("Rejected");
            requestDao.update(activityRequest);
        } catch (DaoException e) {
            LOGGER.warn("Cannot reject request");
        }
    }

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

    public void deleteUserAuthority(int user_id, Authority authority){
        try{
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.deleteUserAuthority(user_id, authority);
        } catch (DaoException e){
            LOGGER.warn("Cannot delete authority for user");
        }
    }
}
