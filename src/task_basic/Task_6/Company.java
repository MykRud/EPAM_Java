package task_basic.Task_6;

import java.math.BigDecimal;

public class Company {
    private final Employee[] employees;

    public Company(Employee[] employees) {
        this.employees = employees;
    }

    public Employee[] getArray() {
        return employees;
    }

    public void giveEverbodyBonus(BigDecimal companyBonus){
        for(Employee emp : employees)
            emp.setBonus(companyBonus);
    }

    public BigDecimal totalToPay(){
        BigDecimal sum = new BigDecimal("0");
        for(Employee emp : employees) {
            sum = sum.add(emp.getSalary()).add(emp.getBonus());
        }
        return sum;
    }

    public String nameMaxSalary(){
        BigDecimal maxSum = new BigDecimal("0").add(employees[0].getSalary()).add(employees[0].getBonus());
        BigDecimal sum = new BigDecimal("0");
        String name = employees[0].getLastName();
        int a = 0;
        for(int i = 1; i < employees.length; i++){
            sum = sum.add(employees[i].getSalary()).add(employees[i].getBonus());
            if(maxSum.compareTo(sum) == -1) {
                maxSum = sum;
                a++;
            }
        }
        name = employees[a].getLastName();
        return name;
    }
}