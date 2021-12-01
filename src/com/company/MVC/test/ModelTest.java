package com.company.MVC.test;

import com.company.MVC.Control;
import com.company.MVC.Diapason;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    Control control = new Control();

    @Test
    public void setNumber() {
        control.getModel().setRandomNumber(78);
        control.checkForDiapason(50);
        assertEquals(50, control.getModel().getEnteredValue());
    }

    @Test
    public void getNumbers(){
        control.getModel().setRandomNumber(78);
        control.checkForDiapason(50);
        assertEquals(50, control.getModel().getNumbers()[0]);
    }

    @Test
    public void getSize() {
        control.checkForDiapason(50);
        assertEquals(1, control.getModel().getSize());
    }

    @Test
    public void setDiapason() {
        control.getModel().setRandomNumber(78);
        control.checkForDiapason(50);
        assertEquals(50, control.getModel().getDiapason(Diapason.FIRST));
        control.checkForDiapason(90);
        assertEquals(90, control.getModel().getDiapason(Diapason.SECOND));
    }

    @Test
    public void doubleArray(){
        control.getModel().setRandomNumber(78);
        for(int i = 0; i < 11; i++)
            control.checkForDiapason(50);
        assertEquals(20, control.getModel().getNumbers().length);
    }
}