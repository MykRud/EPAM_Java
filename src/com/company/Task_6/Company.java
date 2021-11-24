package com.company.Task_6;

import java.math.BigDecimal;

public class Company {
    private final Employee[] employees;

    public Company(Employee[] employees) {
        this.employees = employees;
    }

    public void giveEverbodyBonus(BigDecimal companyBonus){
        for(Employee emp : employees)
            emp.setBonus(companyBonus);
    }

    public BigDecimal totalToPay(){
        BigDecimal sum = new BigDecimal("");
        for(Employee emp : employees) {
            sum = sum.add(emp.getSalary()).add(emp.getBonus());
        }
        return sum;
    }

    public String nameMaxSalary(){
        BigDecimal maxSum = new BigDecimal("").add(employees[0].getSalary()).add(employees[0].getBonus());
        BigDecimal sum = new BigDecimal("");
        String name = employees[0].getLastName();
        for(int i = 0; i < employees.length - 1; i++){
            sum = sum.add(employees[i].getSalary()).add(employees[i].getBonus());
            if(maxSum.compareTo(sum) == -1)
                maxSum = sum;
        }
        return name;
    }
}