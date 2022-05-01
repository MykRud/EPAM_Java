package task_advanced.task_6.patterns;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;

public class NotStringHandler {
    public static void checkIfItsNotString(Object obj) throws IllegalFormatException {
        Class<?> clazz = obj.getClass();
        for(Field field : clazz.getDeclaredFields()){
            if(field.isAnnotationPresent(NotString.class)){
                if(field.getClass().getName() == "String.class"){
                    throw new IllegalFormatException();
                }
            }
        }
    }
}
