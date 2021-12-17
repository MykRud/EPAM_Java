package com.company.FinalTask.business.entities;



import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.NONE)
public class City implements Serializable {
    @XmlElement
    private int codeOfCity;
    @XmlElement
    private String nameOfCity;
    @XmlElement
    private int population;
    @XmlElement
    private boolean isCapital;
    @XmlElement
    private String countryOfCity;

    public City() {
        codeOfCity++;
    }

    public City(String nameOfCity, String countryOfCity, int population, boolean isCapital, int code) {
        this.countryOfCity = countryOfCity;
        this.nameOfCity = nameOfCity;
        this.population = population;
        this.isCapital = isCapital;
        this.codeOfCity = ++code;
    }

    public int getCodeOfCity() {
        return codeOfCity;
    }

    public String getCountryOfCity() {
        return countryOfCity;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public int getPopulation() {
        return population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override

    public String toString(){
        return nameOfCity;
    }

    public String deepToString() {
        return "Код міста=" + codeOfCity +
                ", назва міста='" + nameOfCity + '\'' +
                ", населення=" + population +
                ", столиця=" + isCapital +
                ", до якої країни відноситься='" + countryOfCity + '\'' +
                '}';
    }
}
