package task_advanced.task_2.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import task_advanced.task_2.businessLogic.collections.Container;
import task_advanced.task_2.businessLogic.entities.City;

import java.io.File;
import java.io.IOException;

public class JSONFormatter implements Formatter {

    public static final String DEFAULT_PATH_JSON = "src\\task_advanced\\task_2\\newCities.json";

    @Override
    public void read(Container container, String file) {
        Object[] array;
        ObjectMapper mapper = new ObjectMapper();
        try {
            array = mapper.readValue(new File(file), City[].class);

            container.setList(array);

        } catch (IOException e) {
            e.getMessage();
        }

    }

    @Override
    public void save(Container container, String file) {
        Object[] array = container.asList();

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(file), array);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
