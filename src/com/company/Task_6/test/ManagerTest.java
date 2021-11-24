package com.company.Task_6.test;

import com.company.Task_6.Manager;
import com.company.Task_6.SalesPerson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private static Manager manager;

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("Before ManagerTest.class");
    }

    @org.junit.AfterClass
    public  static void afterClass() {
        System.out.println("After ManagerTest.class");
    }

    @Test
    void setBonus() {
        // 1
        manager = new Manager("Mike", BigDecimal.valueOf(5000), 200);
        manager.setBonus(BigDecimal.valueOf(1000));
        assertEquals(0, BigDecimal.valueOf(2000).compareTo(manager.getBonus()));

        // 2
        manager = new Manager("Mike", BigDecimal.valueOf(5000), 130);
        manager.setBonus(BigDecimal.valueOf(1000));
        assertEquals(0, BigDecimal.valueOf(1500).compareTo(manager.getBonus()));

        // 3
        manager = new Manager("Mike", BigDecimal.valueOf(5000), 90);
        manager.setBonus(BigDecimal.valueOf(1000));
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(manager.getBonus()));
    }
}