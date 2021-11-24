package com.company.Task_6.test;

import com.company.Task_6.Employee;
import com.company.Task_6.SalesPerson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static Employee emp;

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("Before EmployeeTest.class");
        emp = new Employee("Mike", BigDecimal.valueOf(5000)) {
            @Override
            public void setBonus(BigDecimal bonus) {
            }
        };
    }

    @org.junit.AfterClass
    public  static void afterClass() {
        System.out.println("After EmployeeTest.class");
    }

    @Test
    void getLastName() {
        assertEquals("Mike", emp.getLastName());
    }

    @Test
    void getSalary() {
        assertEquals(BigDecimal.valueOf(5000), emp.getSalary());
    }


    @Test
    void setSalary() {
        emp.setSalary(BigDecimal.valueOf(1000));
        assertEquals(0, emp.getSalary().compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void getBonus() {
        SalesPerson sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(200));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(3000), emp.getBonus());
    }

    @Test
    void toPay() {
        SalesPerson sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(200));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(8000, emp.getSalary().add(sp.getBonus()));
    }
}