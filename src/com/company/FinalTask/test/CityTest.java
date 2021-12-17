package com.company.FinalTask.test;

import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(service.getCityService().find("NewCity").getCodeOfCity(), service.getCityService().getList().size());
    }

    @Test
    void getCountryOfCity(){
        int length = service.getCityService().getList().size();
        assertEquals("NewCountry", service.getCityService().getList().get(length-1).getCountryOfCity());
    }

    @Test
    void getNameOfCity(){
        int length = service.getCityService().getList().size();
        assertEquals(service.getCityService().find("NewCity").getNameOfCity(), service.getCityService().getList().get(length-1).getNameOfCity());
    }

    @Test
    void getPopulation(){
        int length = service.getCityService().getList().size();
        assertEquals(2000000, service.getCityService().getList().get(length-1).getPopulation());
    }

    @Test
    void isCapital(){
        int length = service.getCityService().getList().size();
        assertFalse(service.getCityService().getList().get(length-1).isCapital());
    }

}