package task_basic.Task_6;

import java.math.BigDecimal;

public class Manager extends Employee{
    private int quantity;

    public Manager(String lastName, BigDecimal salary, int clientAmount ) {
        super(lastName, salary);
        this.quantity = clientAmount;
    }

    public void setBonus(BigDecimal bonus){
        if(quantity > 100 && quantity <= 150)
            bonus = bonus.add(BigDecimal.valueOf(500));
        else if(quantity > 150)
            bonus = bonus.add(BigDecimal.valueOf(1000));
        setBasicBonus(bonus);
    }

}
