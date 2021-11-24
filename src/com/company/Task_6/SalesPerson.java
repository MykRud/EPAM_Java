package com.company.Task_6;

import java.math.BigDecimal;

public class SalesPerson extends Employee{

    private BigDecimal percent;

    public SalesPerson(String lastName, BigDecimal salary, BigDecimal percent) {
        super(lastName, salary);
        this.percent = percent;
    }

    public void setBonus(BigDecimal bonus){
        if(percent.compareTo(BigDecimal.valueOf(99)) == 1 && percent.compareTo(BigDecimal.valueOf(200)) == -1)
            bonus = bonus.multiply(BigDecimal.valueOf(2));
        else if(percent.compareTo(BigDecimal.valueOf(199)) == 1)
            bonus = bonus.multiply(BigDecimal.valueOf(3));
        setBasicBonus(bonus);
    }
}
