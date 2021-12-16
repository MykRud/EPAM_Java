package com.company.FinalTask.business.services;

import com.company.FinalTask.business.entities.City;
import com.company.FinalTask.business.entities.Country;
import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.database.DataBase;
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

    private DataBase dataBase;

    public CountryService() throws JAXBException, IOException {
    } // Потребується для XML серіалізації

    public CountryService(CityService cityService) throws SAXException, JAXBException {
        dataBase = new DataBase();
        listOfCountries = dataBase.readCountries();
        setCityService(cityService);
        if (listOfCountries == null)
            listOfCountries = new ArrayList<>();
    }

    @Override
    public void save() {
        dataBase.save(this);
    }

    @Override
    public void read() throws SAXException {
        listOfCountries = dataBase.readCountries();
    }

    public void addCountry(String name) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_NAME_ERROR.toString());
        listOfCountries.add(new Country(name, listOfCountries.size()));
    }

    public void remove(String name) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_NAME_ERROR.toString());
        listOfCountries.removeIf(city -> city.getNameOfCountry().equals(name));
        List<City> cities = getCityService().findAllCitiesOfCountry(name);
        for (City city : cities)
            getCityService().remove(city.getNameOfCity());
    }

    public Country findCountry(String nameOfCountry) {
        for (Country country : listOfCountries) {
            if (country.getNameOfCountry().equals(nameOfCountry))
                return country;
        }
        return null;
    }

    public Country findCountry(int code) {
        for (Country country : listOfCountries) {
            if (country.getCodeOfCountry() == code)
                return country;
        }
        return null;
    }

    public List<Country> getListOfCountries() {
        return listOfCountries;
    }

    public void clearUp() {
        listOfCountries = null;
    }


}