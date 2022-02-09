package task_basic.FinalTask.ui;

import task_basic.FinalTask.business.exception.ServiceExceptions;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;


public class Main {

    public static void main(String[] args) throws ServiceExceptions, JAXBException, SAXException {
        Menu.run();
    }
}
