package task_basic.MVC.test;

import task_basic.MVC.Control;
import task_basic.MVC.Diapason;
import task_basic.MVC.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControlTest {

    final Control control = new Control();

    @Test
    public void checkForDiapasonBoolean() {
        control.setModel();
        control.setExamplePlayer();
        control.getModel().setRandomNumber(76);
        assertFalse(control.checkForDiapason(50));
        assertFalse(control.checkForDiapason(80));
        assertTrue(control.checkForDiapason(76));
    }

    @Test
    public void checkForDiapason() {
        control.setModel();
        control.getModel().setRandomNumber(76);
        control.checkForDiapason(50);
        assertEquals(50, control.getModel().getDiapason(Diapason.FIRST));
        control.checkForDiapason(90);
        assertEquals(90, control.getModel().getDiapason(Diapason.SECOND));
    }

    @Test
    public void checkForReg(){
        Player Mike = new Player("Mike");
        assertTrue(control.register(Mike));
        assertFalse(control.register(Mike));
        Player David = new Player("David");
        assertTrue(control.register(David));
    }

    @Test
    public void testJoin(){
        Player Mike = new Player("Mike");
        control.register(Mike);
        control.setModel();
        control.getModel().setRandomNumber(76);
        assertFalse(control.checkForDiapason(50));
        Player David = new Player("David");
        assertFalse(control.start(David));
    }

    @Test
    public void calculateStatistic(){
        control.setExamplePlayer();
        control.setModel();
        control.getModel().setRandomNumber(76);
        control.checkForDiapason(50);
        control.checkForDiapason(60);
        control.checkForDiapason(76);
        int[] arr = new int[3];
        arr[0] = control.getCurrentPlayer().getShoots()[0];
        arr[1] = control.getCurrentPlayer().getShoots()[1];
        arr[2] = control.getCurrentPlayer().getShoots()[2];
        assertArrayEquals(new int[]{50, 60, 76}, arr);
    }
}