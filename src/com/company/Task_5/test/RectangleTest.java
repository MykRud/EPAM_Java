package com.company.Task_5.test;

import com.company.Task_5.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    Rectangle rectangle = new Rectangle(7, 6);

    @Test
    void area() {
        assertEquals(42, rectangle.area());
    }

    @Test
    void perimeter() {
        assertEquals(26, rectangle.perimeter());
    }

    @Test
    void isSquare() {
        assertFalse(rectangle.isSquare());
        rectangle = new Rectangle(6, 6);
        assertTrue(rectangle.isSquare());
    }

    @Test
    void replaceSides() {
        rectangle.replaceSides();
        assertEquals(6, rectangle.getSideA());
        assertEquals(7, rectangle.getSideB());
    }
}