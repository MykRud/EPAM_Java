package task_basic.Task_7.test;

import task_basic.Task_7.BaseDeposit;
import task_basic.Task_7.LongDeposit;
import task_basic.Task_7.SpecialDeposit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasisForTesting {

    private final BaseDeposit bd = new BaseDeposit(BigDecimal.valueOf(1000), 5);
    private final SpecialDeposit sd = new SpecialDeposit(BigDecimal.valueOf(5000), 7);
    private final LongDeposit ld = new LongDeposit(BigDecimal.valueOf(7000), 12);

    public BaseDeposit getBaseDeposit() {
        return bd;
    }

    public SpecialDeposit getSpecialDeposit() {
        return sd;
    }

    public LongDeposit getLongDeposit() {
        return ld;
    }

    protected BigDecimal[] getBaseDepositExample(){
        return new BigDecimal[]{BigDecimal.valueOf(1050.00),
                BigDecimal.valueOf(1102.50),
                BigDecimal.valueOf(1157.63),
                BigDecimal.valueOf(1215.51),
                BigDecimal.valueOf(1276.28)};
    }

    protected BigDecimal[] getSpecialDepositExample(){
        return new BigDecimal[]{
                BigDecimal.valueOf(5050.00),
                BigDecimal.valueOf(5151.00),
                BigDecimal.valueOf(5305.53),
                BigDecimal.valueOf(5517.75),
                BigDecimal.valueOf(5793.64),
                BigDecimal.valueOf(6141.26),
                BigDecimal.valueOf(6571.15)};
    }

    protected BigDecimal[] getLongDepositExample(){
        return new BigDecimal[]{
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(7000.00),
                BigDecimal.valueOf(8050.00),
                BigDecimal.valueOf(9257.50),
                BigDecimal.valueOf(10646.12),
                BigDecimal.valueOf(12243.04),
                BigDecimal.valueOf(14079.50),
                BigDecimal.valueOf(16191.43)};
    }

    protected BigDecimal[] scale(BigDecimal[] arrayOfBigDecimals){
        for(int i = 0; i < arrayOfBigDecimals.length; i++)
            arrayOfBigDecimals[i] = arrayOfBigDecimals[i].setScale(2, RoundingMode.HALF_EVEN);
        return arrayOfBigDecimals;
    }
}
