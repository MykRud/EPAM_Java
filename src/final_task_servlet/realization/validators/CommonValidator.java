package final_task_servlet.realization.validators;

public class CommonValidator {

    protected static boolean validateForBlankValue(String value){
        return !value.isBlank();
    }

    protected static boolean validateForSize(int minSize, int maxSize, String value){
        return value.length() >= minSize && value.length() <= maxSize;
    }

}
