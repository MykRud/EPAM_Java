package task_basic.Task_6.test;

import task_basic.Task_6.SalesPerson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalesPersonTest {

    private static SalesPerson sp;

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("Before SalesPersonTest.class");
    }

    @org.junit.AfterClass
    public  static void afterClass() {
        System.out.println("After SalesPersonTest.class");
    }

    @Test
    void setBonus() {
        // 1
        sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(210));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(3000), sp.getBonus());

        // 2
        sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(130));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(2000), sp.getBonus());

        // 3
        sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(90));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), sp.getBonus());
    }
}