package com.company.FinalTask;

import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;


public class Main {

    public static void main(String[] args) throws ServiceExceptions, JAXBException, SAXException {
        Service service = new Service();
        service.run();

        service.addCountry("Ukraine");
        service.addCountry("USA");
        service.addCountry("America");
        service.addCountry("UK");
        service.addCountry("Brasil");
        service.addCity("Kyiv", "Ukraine", 3000000, true);
        service.addCity("California", "USA", 30000000, true);
        service.addCity("California", "USA", 3000000, true);
        service.addCity("London", "UK", 3000000, true);
        service.addCity("Ba-Ba", "Brasil", 3000000, true);

        service.save();

        //countryService.removeCountry("UK");
        //service.save();

        //System.out.println(service.getListOfCountries());


    }
}
