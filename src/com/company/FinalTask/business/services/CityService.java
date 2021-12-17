package com.company.FinalTask.business.services;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.exception.ServiceExceptions;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.*;
import org.xml.sax.SAXException;


import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Cities")
public class CityService extends Service {

    @XmlElement(name = "city")
    private List<City> listOfCities;

    @Override
    public void save() {
        getDataBase().save(this);
    }

    public void read() throws SAXException {
        listOfCities = getDataBase().readCities();
    }

    public CityService() throws JAXBException, SAXException{
    } // Потребується для XML серіалізації

    public CityService(boolean b) throws JAXBException, SAXException {
        listOfCities = getDataBase().readCities();
        if (listOfCities == null)
            listOfCities = new ArrayList<>();
    }

    @Override
    public void addCity(String name, String nameOfCountry, int population, boolean isCapital) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_CITY_NAME_ERROR.toString());
        else if (nameOfCountry.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_OF_CITY_ERROR.toString());
        else if (population < 0)
            throw new ServiceExceptions(Errors.NEGATIVE_POPULATION_ERROR.toString());
        else if (getCountryService().findCountry(nameOfCountry) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        City city = new City(name, nameOfCountry, population, isCapital, listOfCities.size());
        Country country = getCountryService().findCountry(nameOfCountry);
        List<City> cities = country.getListOfCities();
        cities.add(city);
        country.setListOfCities(cities);
        listOfCities.add(city);
    }

    public void remove(String name) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_CITY_NAME_ERROR.toString());
        listOfCities.removeIf(city -> city.getNameOfCity().equals(name));
    }

    public List<City> findAllCitiesOfCountry(int code) throws ServiceExceptions {
        if (getCountryService().findCountry(code) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        List<City> citiesOfCountry = new ArrayList<>();
        for (City city : listOfCities) {
            if (city.getCodeOfCity() == code)
                citiesOfCountry.add(city);
        }
        return citiesOfCountry;
    }

    public List<City> findAllCitiesOfCountry(String nameOfCountry) throws ServiceExceptions {
        if (getCountryService().findCountry(nameOfCountry) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        List<City> citiesOfCountry = new ArrayList<>();
        for (City city : listOfCities) {
            if (city.getCountryOfCity().equals(nameOfCountry))
                citiesOfCountry.add(city);
        }
        return citiesOfCountry;
    }

    public void changePopulationOfCity(String nameOfCity, int newPopulation) throws ServiceExceptions {
        if (findCity(nameOfCity) == null)
            throw new ServiceExceptions(Errors.CITY_NOT_FOUND_ERROR.toString());
        for (City city : listOfCities)
            if (city.getNameOfCity().equals(nameOfCity))
                city.setPopulation(newPopulation);
    }

    public City findCity(String nameOfCity) {
        for (City city : listOfCities) {
            if (city.getNameOfCity().equals(nameOfCity))
                return city;
        }
        return null;
    }

    public City findCity(int code) {
        for (City city : listOfCities) {
            if (city.getCodeOfCity() == code)
                return city;
        }
        return null;
    }

    @Override
    public List<City> getListOfCities() {
        return listOfCities;
    }
}
