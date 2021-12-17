package com.company.FinalTask.business.services;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.exception.ServiceExceptions;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Countries")
public class CountryService extends Service {

    @XmlElement(name = "country")
    private List<Country> listOfCountries;

    public CountryService() throws JAXBException, IOException {
    } // Потребується для XML серіалізації

    public CountryService(CityService cityService) throws SAXException, JAXBException {
        listOfCountries = getDataBase().readCountries();
        setCityService(cityService);
        if (listOfCountries == null)
            listOfCountries = new ArrayList<>();
    }

    @Override
    public void save() {
        getDataBase().save(this);
    }

    public void read() throws SAXException {
        listOfCountries = getDataBase().readCountries();
    }

    public void addCountry(String name) throws ServiceExceptions {
        for(Country country : listOfCountries)
            if(country.getNameOfCountry().equals(name))
                throw new ServiceExceptions(Errors.COUNTRY_EXISTS.toString());
        listOfCountries.add(new Country(name, listOfCountries.size()));
    }

    public void remove(String name) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_NAME_ERROR.toString());
        listOfCountries.removeIf(city -> city.getNameOfCountry().equals(name));
        List<City> cities = findAllCitiesOfCountry(name);
        for (City city : cities)
            getCityService().remove(city.getNameOfCity());
    }

    public Country find(String nameOfCountry) {
        for (Country country : listOfCountries) {
            if (country.getNameOfCountry().equals(nameOfCountry))
                return country;
        }
        return null;
    }

    public Country find(int code) {
        for (Country country : listOfCountries) {
            if (country.getCodeOfCountry() == code)
                return country;
        }
        return null;
    }

    public List<City> findAllCitiesOfCountry(String nameOfCountry) throws ServiceExceptions {
        if (find(nameOfCountry) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        for(Country country : listOfCountries)
            if(country.getNameOfCountry().equals(nameOfCountry))
                return country.getListOfCities();
        return new ArrayList<>();
    }

    public List<City> findAllCitiesOfCountry(int code) throws ServiceExceptions {
        if (find(code) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        for(Country country : listOfCountries)
            if(country.getCodeOfCountry() == code)
                return country.getListOfCities();
        return new ArrayList<>();
    }

    public List<Country> getList() {
        return listOfCountries;
    }

}