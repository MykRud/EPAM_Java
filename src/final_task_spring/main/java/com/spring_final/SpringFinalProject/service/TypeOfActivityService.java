package final_task_spring.main.java.com.spring_final.SpringFinalProject.service;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.repo.ActivityDaoRep;
import com.spring_final.SpringFinalProject.repo.TypesOfActivitiesDaoRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The service with business logic related to types
 *
 * @author Misha Rudyk
 * @see ActivityDaoRep
 * @see Activity
 */
@Component
@Slf4j
public class TypeOfActivityService {

    @Autowired
    private TypesOfActivitiesDaoRep typeDao;

    /**
     * Method that allows to add type
     *
     * @param type Type to add
     */
    public void addType(TypeOfActivity type) {
        typeDao.save(type);
        log.info("Type added");
    }

    /**
     * Method that allows to get list of types
     *
     * @return list of types
     */
    public List<TypeOfActivity> getTypes() {
        List<TypeOfActivity> types = typeDao.findAll();
        log.info("Types fetched");
        return types;
    }

    /**
     * Method that allows to get type by its id
     *
     * @param id Id of type
     * @return Type with desired id
     */
    public TypeOfActivity findType(int id) {

        TypeOfActivity type;
        try {
            type = typeDao.findById(id).get();
        } catch (Exception e) {
            log.warn("Type not found");
            return null;
        }

        log.info("Type found");
        return type;
    }

    /**
     * Method that allows to get activity by its type
     *
     * @param name Name of type
     * @return Type with desired name
     */
    public TypeOfActivity getType(String name) {
        TypeOfActivity type;
        try {
            type = typeDao.getByName(name);
        } catch (Exception e) {
            log.warn("Type {} not found", name);
            return null;
        }

        log.info("Type found");
        return type;
    }


    public TypesOfActivitiesDaoRep getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(TypesOfActivitiesDaoRep typeDao) {
        this.typeDao = typeDao;
    }
}
