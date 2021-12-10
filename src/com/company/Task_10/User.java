package com.company.Task_10;

import java.math.BigDecimal;

public class User {
    private String name;
    private String position;
    private BigDecimal salary;
    private BigDecimal bonusForYears;
    private BigDecimal bonusForWork;
    private BigDecimal bonusForMonth;
    private BigDecimal vacation;
    private BigDecimal summaryIncome;
    private BigDecimal restIncome;

    public User(String name, String position, BigDecimal salary, BigDecimal bonusForYears, BigDecimal bonusForWork,
                BigDecimal bonusForMonth, BigDecimal vacation, BigDecimal summaryIncome) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.bonusForYears = bonusForYears;
        this.bonusForWork = bonusForWork;
        this.bonusForMonth = bonusForMonth;
        this.vacation = vacation;
        this.summaryIncome = summaryIncome;
        calculateRestIncome();
    }

    private void calculateRestIncome() {
        this.restIncome = summaryIncome.subtract(salary.add(bonusForYears).add(bonusForWork).add(bonusForMonth).add(vacation));
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", bonusForYears=" + bonusForYears +
                ", bonusForWork=" + bonusForWork +
                ", bonusForMonth=" + bonusForMonth +
                ", vacation=" + vacation +
                ", summaryIncome=" + summaryIncome +
                ", restIncome=" + restIncome +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public BigDecimal getBonusForYears() {
        return bonusForYears;
    }

    public BigDecimal getBonusForWork() {
        return bonusForWork;
    }

    public BigDecimal getBonusForMonth() {
        return bonusForMonth;
    }

    public BigDecimal getVacation() {
        return vacation;
    }

    public BigDecimal getSummaryIncome() {
        return summaryIncome;
    }

    public BigDecimal getRestIncome() {
        return restIncome;
    }
}
