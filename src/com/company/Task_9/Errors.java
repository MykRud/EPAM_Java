package com.company.Task_9;

public enum Errors {
    BAD_ROW_ERROR("Рядок з таким індексом відсутній у матриці"),
    BAD_COLUMN_ERROR("Стовпчик з таким індексом відсутній у матриці"),
    BAD_SIZE_ERROR("Ви ввели некорестні дані про прозмір матриці. Він має бути більшим від нуля"),
    BAD_MATRIX_LENGTH_ERROR("Матриці відрізняють кількістю рядків чи стовпців"),
    ROWS_DO_NOT_MATCH("Значення рядків не співпадає з реальною кількість рядків матриці"),
    COLUMNS_DO_NOT_MATCH("Значення стовпців не співпадає з реальною кількість рядків матриці");

    private final String meaning;

    Errors(String translation){
        this.meaning = translation;
    }

    @Override
    public String toString(){
        return meaning;
    }
}
