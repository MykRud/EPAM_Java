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

    public void add(String name, String nameOfCountry, int population, boolean isCapital) throws ServiceExceptions {
        for(City city : listOfCities)
            if(city.getNameOfCity().equals(name))
                throw new ServiceExceptions(Errors.CITY_EXISTS.toString());

        else if (getCountryService().find(nameOfCountry) == null)
            throw new ServiceExceptions(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        City city = new City(name, nameOfCountry, population, isCapital, listOfCities.size());
        Country country = getCountryService().find(nameOfCountry);
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

    public void changePopulationOfCity(String nameOfCity, int newPopulation) throws ServiceExceptions {
        if (find(nameOfCity) == null)
            throw new ServiceExceptions(Errors.CITY_NOT_FOUND_ERROR.toString());
        for (City city : listOfCities)
            if (city.getNameOfCity().equals(nameOfCity))
                city.setPopulation(newPopulation);
    }

    public City find(String nameOfCity) {
        for (City city : listOfCities) {
            if (city.getNameOfCity().equals(nameOfCity))
                return city;
        }
        return null;
    }

    public City find(int code) {
        for (City city : listOfCities) {
            if (city.getCodeOfCity() == code)
                return city;
        }
        return null;
    }

    public List<City> getList() {
        return listOfCities;
    }
}
