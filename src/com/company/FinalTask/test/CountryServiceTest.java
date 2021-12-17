package com.company.FinalTask.test;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {
    Service service = new Service();

    @BeforeEach
    public void setup() throws JAXBException, SAXException, ServiceExceptions {
        service.run();
        service.addCountry("NewCountry");
    }

    @Test
    void addCountry() {
        String exc = "NewCountry";
        String real = "NoCountry";
        for(Country country : service.getCountryService().getList())
            if(Objects.equals(country.getNameOfCountry(), "NewCountry"))
                real = country.getNameOfCountry();
        assertEquals(exc, real);

    }

    @Test
    void exceptionAddCountry() {
        assertThrows(ServiceExceptions.class, () -> service.addCountry("NewCountry"));
        assertThrows(ServiceExceptions.class, () -> service.addCountry("123"));
        assertThrows(ServiceExceptions.class, () -> service.addCountry(""));
    }

    @Test
    void remove() throws ServiceExceptions {
        service.removeCountry("NewCountry");
        boolean isRemoved = true;
        for(Country country : service.getCountryService().getList())
            if (country.getNameOfCountry().equals("NewCountry")) {
                isRemoved = false;
                break;
            }
        assertTrue(isRemoved);
    }

    @Test
    void find() {
        Country country = service.getCountryService().find("NewCountry");
        assertEquals("NewCountry", country.getNameOfCountry());
    }

    @Test
    void testFind() throws ServiceExceptions {
        service.getCountryService().remove("NewCountry");
        assertThrows(ServiceExceptions.class, () -> service.getCountryService().remove("NewCountry"));
        assertThrows(ServiceExceptions.class, () -> service.getCountryService().remove(""));
    }

    @Test
    void findAllCitiesOfCountry() throws ServiceExceptions {
        service.addCity("NewCity", "NewCountry", 2000000, "ні");
        List<City> real = service.getCountryService().findAllCitiesOfCountry("NewCountry");
        assertEquals("NewCity", real.get(0).getNameOfCity());
    }

    @Test
    void exceptionFindAllCitiesOfCountry() throws JAXBException, SAXException {
        service.run();
        assertThrows(ServiceExceptions.class, () -> service.getCountryService().findAllCitiesOfCountry("123"));
    }

    @Test
    void getListTest() {
        String exc = "NewCountry";
        String real = "NoCountry";
        for(Country country : service.getCountryService().getList())
            if(Objects.equals(country.getNameOfCountry(), "NewCountry"))
                real = country.getNameOfCountry();
        assertEquals(exc, real);
    }
}