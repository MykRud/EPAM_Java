package task_advanced.task_2.businessLogic.entities;

import java.io.Serializable;

public class City implements Serializable {

    private String name;
    private int population;
    private boolean isCapital;
    private String country;
    private int codeOfCity;

    public City() {
    }

    public City(int codeOfCity, String name, int population, boolean isCapital, String country) {
        this.codeOfCity = codeOfCity;
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
        this.country = country;
    }

    public int getCodeOfCity() {
        return codeOfCity;
    }

    public void setCodeOfCity(int codeOfCity) {
        this.codeOfCity = codeOfCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override

    public String toString() {
        return name;
    }

    public String deepToString() {
        return "Код міста=" + codeOfCity +
                ", назва міста='" + name + '\'' +
                ", населення=" + population +
                ", столиця=" + isCapital +
                ", до якої країни відноситься='" + country + '\'' +
                '}';
    }
}
