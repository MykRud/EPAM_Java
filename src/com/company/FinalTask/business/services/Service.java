package com.company.FinalTask.business.services;

import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.database.DataBase;
import com.company.FinalTask.interfaces.CRUDable;
import com.company.FinalTask.ui.View;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import org.xml.sax.SAXException;

@XmlAccessorType(XmlAccessType.NONE)
public class Service implements CRUDable {
    @XmlTransient
    private CountryService countryService;
    @XmlTransient
    private CityService cityService;
    @XmlTransient
    private static DataBase dataBase;
    @XmlTransient
    private static View view;



    public void run() throws JAXBException, SAXException {
        dataBase = new DataBase();
        view = new View();
        cityService = new CityService(true);
        countryService = new CountryService(cityService);
        cityService.setCountryService(countryService);

    }

    public static View getView() {
        return view;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public CityService getCityService() {
        return cityService;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void addCountry(String name) throws ServiceExceptions{
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_NAME_ERROR.toString());
        if(!name.matches("[a-zA-Z ]+") || name.matches(" +"))
            throw new ServiceExceptions(Errors.COUNTRY_SYNTAX_ERROR.toString());

        countryService.addCountry(name);
    }

    public void addCity(String name, String nameOfCountry, int population, String isCapital) throws ServiceExceptions {
        if (name.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_CITY_NAME_ERROR.toString());
        else if(!name.matches("[a-zA-Z ]+") || name.matches(" +"))
            throw new ServiceExceptions(Errors.CITY_SYNTAX_ERROR.toString());
        else if (nameOfCountry.isBlank())
            throw new ServiceExceptions(Errors.EMPTY_COUNTRY_OF_CITY_ERROR.toString());
        else if(!nameOfCountry.matches("[a-zA-Z ]+") || nameOfCountry.matches(" +"))
            throw new ServiceExceptions(Errors.COUNTRY_SYNTAX_ERROR.toString());
        else if (population < 0)
            throw new ServiceExceptions(Errors.NEGATIVE_POPULATION_ERROR.toString());
        else if(!isCapital.equals("так") && !isCapital.equals("ні"))
            throw new ServiceExceptions(Errors.SYNTAX_ERROR.toString());
        boolean cap = isCapital.equals("так");

        cityService.add(name, nameOfCountry, population, cap);
    }

    public void read() throws SAXException {
        countryService.read();
        cityService.read();
    }

    public void save(){
        countryService.save();
        cityService.save();
    }

    public void removeCountry(String name) throws ServiceExceptions{
        countryService.remove(name);
    }

    public void removeCity(String name) throws ServiceExceptions{
        cityService.remove(name);
    }

    public void getListOfCountries(){
        View.print(countryService.getList().toString());
    }

    public void getListOfCities(){
        View.print(cityService.getList().toString());
    }

    public void findCountry(String value) {
        try {
            if (value.matches("[0-9]+")) {
                View.print(countryService.find(Integer.parseInt(value)).toString());
                return;
            } else if (value.matches("[a-zA-Z]+")) {
                View.print(countryService.find(value).toString());
                return;
            }
            else{
                throw new ServiceExceptions(Errors.SYNTAX_ERROR.toString());
            }
        } catch(NullPointerException e) {
            View.print(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        } catch (ServiceExceptions serviceExceptions) {
            serviceExceptions.printStackTrace();
        }
    }

    public void findCity(String value){
        try {
            if (value.matches("[0-9]+"))
                View.print(cityService.find(Integer.parseInt(value)).deepToString());
            else if (value.matches("[a-zA-Z]+"))
                View.print(cityService.find(value).deepToString());
            else
                throw new ServiceExceptions(Errors.SYNTAX_ERROR.toString());
        } catch(NullPointerException e) {
            View.print(Errors.COUNTRY_NOT_FOUND_ERROR.toString());
        } catch (ServiceExceptions serviceExceptions) {
            serviceExceptions.printStackTrace();
        }
    }

    public void viewAllCitiesOfCountry(String value) throws ServiceExceptions{
        if(value.matches("[0-9]+"))
            View.print(countryService.findAllCitiesOfCountry(Integer.parseInt(value)).toString());
        else if(value.matches("[a-zA-Z]+"))
            View.print(countryService.findAllCitiesOfCountry(value).toString());
    }

    public void changePopulationOfCity(String nameOfCity, int newPopulation) throws ServiceExceptions {
        if(newPopulation < 0)
            throw new ServiceExceptions(Errors.NEGATIVE_POPULATION_ERROR.toString());
        cityService.changePopulationOfCity(nameOfCity, newPopulation);
    }

}
