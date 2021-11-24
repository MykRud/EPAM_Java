package com.company.Task_6.test;

import com.company.Task_6.Company;
import com.company.Task_6.Employee;
import com.company.Task_6.Manager;
import com.company.Task_6.SalesPerson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {
    private static Employee[] emps = {new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(90)),
                                      new SalesPerson("Adam", BigDecimal.valueOf(7000), BigDecimal.valueOf(90))};

    private static Company company = new Company(emps);

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("Before CompanyTest.class");
    }

    @org.junit.AfterClass
    public  static void afterClass() {
        System.out.println("After CompanyTest.class");
    }

    @Test
    void giveEverbodyBonus() {
        company.giveEverbodyBonus(BigDecimal.valueOf(1000));
        Employee[] em = company.getArray();
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(em[0].getBonus()));
    }

    @Test
    void totalToPay() {
        company = new Company(emps);
        assertEquals(0, BigDecimal.valueOf(12000).compareTo(company.totalToPay()));
    }

    @Test
    void nameMaxSalary() {
        assertEquals("Adam", company.nameMaxSalary());
    }
}