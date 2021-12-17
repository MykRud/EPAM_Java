package com.company.FinalTask.test;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {
    Service service = new Service();

    @BeforeEach
    public void setup() throws JAXBException, SAXException, ServiceExceptions {
        service.run();
        service.addCountry("NewCountry");
        service.addCity("NewCity", "NewCountry", 2000000, "ні");
    }

    @Test
    void add()  {
        String exc = "NewCity";
        String real = "NoCity";
        for(City city : service.getCityService().getList())
            if(Objects.equals(city.getNameOfCity(), "NewCity"))
                real = city.getNameOfCity();
        assertEquals(exc, real);
    }

    @Test
    void exceptionAddCity() {
        assertThrows(ServiceExceptions.class, () -> service.addCity("NewCity", "NewCountry", 2000000, "ні"));
        assertThrows(ServiceExceptions.class, () -> service.addCity("321", "NewCountry", 2000000, "ні"));
        assertThrows(ServiceExceptions.class, () -> service.addCity("", "NewCountry", 2000000, "ні"));
    }

    @Test
    void remove() throws ServiceExceptions {
        service.removeCity("NewCity");
        boolean isRemoved = true;
        for(City city : service.getCityService().getList())
            if (city.getNameOfCity().equals("NewCity")) {
                isRemoved = false;
                break;
            }
        assertTrue(isRemoved);
    }

    @Test
    void changePopulationOfCity() throws ServiceExceptions {
        service.changePopulationOfCity("NewCity", 123123);
        assertEquals(123123, service.getCityService().find("NewCity").getPopulation());
    }

    @Test
    void exceptionChangePopulationOfCity() throws ServiceExceptions {
        service.removeCity("NewCity");
        assertThrows(ServiceExceptions.class, () -> service.changePopulationOfCity("NewCity", 123123));
        service.addCity("NewCity", "NewCountry", 2000000, "ні");
        assertThrows(ServiceExceptions.class, () -> service.changePopulationOfCity("NewCity", -100));
    }

    @Test
    void find() {
        City city = service.getCityService().find("NewCity");
        assertEquals("NewCity", city.getNameOfCity());
    }


    @Test
    void testFind() throws ServiceExceptions {
        service.removeCity("NewCity");
        service.removeCountry("NewCountry");
        assertThrows(ServiceExceptions.class, () -> service.addCity("NewCity", "NewCountry", 2000000, "ні"));
        assertThrows(ServiceExceptions.class, () -> service.addCity("123", "NewCountry", 2000000, "ні"));
        assertThrows(ServiceExceptions.class, () -> service.addCity("", "NewCountry", 2000000, "ні"));
    }

    @Test
    void getList() {
        String exc = "NewCity";
        String real = "NoCity";
        for(City city : service.getCityService().getList())
            if(Objects.equals(city.getNameOfCity(), "NewCity"))
                real = city.getNameOfCity();
        assertEquals(exc, real);
    }
}