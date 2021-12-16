package com.company.FinalTask.business.services;

public enum Errors {
    EMPTY_COUNTRY_NAME_ERROR("Назва країни повинна бути задана"),
    EMPTY_CITY_NAME_ERROR("Назва міста повинна бути задана"),
    EMPTY_COUNTRY_OF_CITY_ERROR("Місто повинно відноситися до певної країни"),
    NEGATIVE_POPULATION_ERROR("Населення міста не може бути меншим за 0"),
    COUNTRY_NOT_FOUND_ERROR("Країна відсутня у списку країн. Будь-ласка, додайте її перед використанням"),
    CITY_NOT_FOUND_ERROR("Місто відсутнє у списку міст. Будь-ласка, додайте його перед використанням");

    private final String meaning;

    Errors(String translation) {
        this.meaning = translation;
    }

    @Override
    public String toString() {
        return meaning;
    }

}
