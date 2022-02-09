package task_basic.Task_7.test;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;

class DepositTest extends BasisForTesting {

    @Test
    void getAmountOfEachMonth() {
        assertArrayEquals(scale(getBaseDepositExample()), scale(getBaseDeposit().getAmountOfEachMonth()));
    }

    @Test
    void getIncomeOfEachMonth() {
        BigDecimal[] incomeOfEachMonth = getSpecialDeposit().getIncomeOfEachMonth();
        for(int i = 0; i < getSpecialDepositExample().length; i++){
            assertEquals(getSpecialDepositExample()[i].subtract(getSpecialDeposit().Amount()).setScale(2, RoundingMode.HALF_EVEN),
                    incomeOfEachMonth[i].setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    @Test
    void income() {
        assertEquals(BigDecimal.valueOf(102.50).setScale(2, RoundingMode.HALF_EVEN),
                getBaseDeposit().income(2).setScale(2, RoundingMode.HALF_EVEN));
        assertThrows(IndexOutOfBoundsException.class, () -> getBaseDeposit().income(10));
    }
}