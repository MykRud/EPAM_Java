package com.company.Task_6;

import java.math.BigDecimal;

public abstract class Employee {

    private final String lastName;
    private BigDecimal salary = new BigDecimal("");
    protected BigDecimal bonus = new BigDecimal("");

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee(String lastName, BigDecimal salary) {
        this.lastName = lastName;
        this.salary = salary;
    }

    public abstract void setBonus(BigDecimal bonus);

    public BigDecimal getBonus() {
        return bonus;
    }

    public BigDecimal toPay() {
        return salary.add(bonus);
    }
}
