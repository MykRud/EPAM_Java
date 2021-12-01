package com.company.MVC.test;

import com.company.MVC.Control;
import com.company.MVC.Diapason;
import com.company.MVC.Player;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ControlTest {

    final Control control = new Control();

    @Test
    public void checkForDiapasonBoolean() {
        control.getModel().setRandomNumber(76);
        assertFalse(control.checkForDiapason(50));
        assertFalse(control.checkForDiapason(80));
        assertTrue(control.checkForDiapason(76));
    }

    @Test
    public void checkForDiapason() {
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
        Player David = new Player("David");
        control.setExamplePlayer();
        control.register(David);
        control.setModel();
        control.getModel().setRandomNumber(76);
        control.checkForDiapason(50);
        control.checkForDiapason(60);
        control.checkForDiapason(76);
        int[] arr = new int[3];
        arr[0] = control.getPlayer().getShoots()[0];
        arr[1] = control.getPlayer().getShoots()[1];
        arr[2] = control.getPlayer().getShoots()[2];
        assertArrayEquals(new int[]{50, 60, 76}, arr);
    }
}