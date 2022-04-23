package task_advanced.task_5.Airport;

public class NotAvailableException extends Exception{
    public NotAvailableException(){
        super("Not available. Resource is full");
    }
}
