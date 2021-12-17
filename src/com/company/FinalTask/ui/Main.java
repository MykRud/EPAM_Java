package com.company.FinalTask.ui;

import com.company.FinalTask.business.exception.ServiceExceptions;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;



public class Main {

    public static void main(String[] args) throws ServiceExceptions, JAXBException, SAXException {
        Menu.run();
    }
}
