package task_basic.Task_6.test;

import task_basic.Task_6.Employee;
import task_basic.Task_6.SalesPerson;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static Employee emp = new Employee("Mike", BigDecimal.valueOf(5000)) {
        @Override
        public void setBonus(BigDecimal bonus) {
        }
    };

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("Before EmployeeTest.class");

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
    void setSalary() {
        emp.setSalary(BigDecimal.valueOf(1000));
        assertEquals(0, emp.getSalary().compareTo(BigDecimal.valueOf(1000)));
    }

    @Test
    void getSalary() {
        emp.setSalary(BigDecimal.valueOf(10000));
        assertEquals(0, BigDecimal.valueOf(10000).compareTo(emp.getSalary()));
    }

    @Test
    void getBonus() {
        SalesPerson sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(200));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(0, BigDecimal.valueOf(3000).compareTo(sp.getBonus()));
    }

    @Test
    void toPay() {
        SalesPerson sp = new SalesPerson("Mike", BigDecimal.valueOf(5000), BigDecimal.valueOf(200));
        sp.setBonus(BigDecimal.valueOf(1000));
        assertEquals(0, BigDecimal.valueOf(8000).compareTo(emp.getSalary().add(sp.getBonus())));

    }
}