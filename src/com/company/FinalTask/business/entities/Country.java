package com.company.FinalTask.business.entities;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class Country implements Serializable {
    @XmlElement
    private int codeOfCountry;
    @XmlElement
    private String nameOfCountry;


    @XmlElementWrapper(name = "listOfCities")
    @XmlElement(name = "city")
    private List<City> listOfCities = new ArrayList<>();

    public Country() {
        codeOfCountry++;
    }

    public List<City> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<City> listOfCities) {
        this.listOfCities = listOfCities;
    }

    public Country(String nameOfCountry, int code) {
        listOfCities = new ArrayList<>();
        codeOfCountry = ++code;
        this.nameOfCountry = nameOfCountry;
    }

    public int getCodeOfCountry() {
        return codeOfCountry;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setCodeOfCountry(int codeOfCountry) {
        this.codeOfCountry = codeOfCountry;
    }

    public void setNameOfCountry(String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }

    public String toString() {
        return nameOfCountry;
    }
}
