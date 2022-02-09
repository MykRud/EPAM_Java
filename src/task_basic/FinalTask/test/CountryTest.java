package task_basic.FinalTask.test;

import task_basic.FinalTask.business.exception.ServiceExceptions;
import task_basic.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class CountryTest {
    Service service = new Service();

    @BeforeEach
    public void setup() throws JAXBException, SAXException, ServiceExceptions {
        service.run();
        service.addCountry("NewCountry");
    }

    @Test
    void getListOfCities() throws ServiceExceptions {
        service.addCity("NewCity", "NewCountry", 2000000, "ні");
        int length = service.getCountryService().findAllCitiesOfCountry("NewCountry").size();
        Assertions.assertEquals("NewCity", service.getCountryService().find("NewCountry").getListOfCities().get(length-1).getNameOfCity());
    }

    @Test
    void getCodeOfCountry() {
        Assertions.assertEquals(service.getCountryService().find("NewCountry").getCodeOfCountry(), service.getCountryService().getList().size());
    }

    @Test
    void getNameOfCountry(){
        int length = service.getCityService().getList().size();
        Assertions.assertEquals(service.getCountryService().find("NewCountry").getNameOfCountry(), service.getCountryService().getList().get(length).getNameOfCountry());
    }
}