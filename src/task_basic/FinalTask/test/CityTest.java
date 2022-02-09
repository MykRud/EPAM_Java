package task_basic.FinalTask.test;

import task_basic.FinalTask.business.exception.ServiceExceptions;
import task_basic.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class CityTest {
    Service service = new Service();

    @BeforeEach
    public void setup() throws JAXBException, SAXException, ServiceExceptions {
        service.run();
        service.addCountry("NewCountry");
        service.addCity("NewCity", "NewCountry", 2000000, "ні");
    }

    @Test
    void getCodeOfCity(){
        Assertions.assertEquals(service.getCityService().find("NewCity").getCodeOfCity(), service.getCityService().getList().size());
    }

    @Test
    void getCountryOfCity(){
        int length = service.getCityService().getList().size();
        Assertions.assertEquals("NewCountry", service.getCityService().getList().get(length-1).getCountryOfCity());
    }

    @Test
    void getNameOfCity(){
        int length = service.getCityService().getList().size();
        Assertions.assertEquals(service.getCityService().find("NewCity").getNameOfCity(), service.getCityService().getList().get(length-1).getNameOfCity());
    }

    @Test
    void getPopulation(){
        int length = service.getCityService().getList().size();
        Assertions.assertEquals(2000000, service.getCityService().getList().get(length-1).getPopulation());
    }

    @Test
    void isCapital(){
        int length = service.getCityService().getList().size();
        Assertions.assertFalse(service.getCityService().getList().get(length-1).isCapital());
    }

}