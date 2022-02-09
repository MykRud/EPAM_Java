package task_basic.Task_6;

import java.math.BigDecimal;

public abstract class Employee {

    private final String lastName;
    private BigDecimal salary;
    private BigDecimal bonus = new BigDecimal("0");

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    protected void setBasicBonus(BigDecimal bonus){
        this.bonus = bonus;
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
