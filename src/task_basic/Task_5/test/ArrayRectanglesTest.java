package task_basic.Task_5.test;

import task_basic.Task_5.ArrayRectangles;
import task_basic.Task_5.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRectanglesTest {

    ArrayRectangles arrayOfRectangles = new ArrayRectangles(new Rectangle[]{
            new Rectangle(4, 4),
            new Rectangle(10, 4),
            new Rectangle(15, 6),
            new Rectangle(1, 3),
            new Rectangle(10, 10)});

    @Test
    void addRectangle() {
        assertFalse(arrayOfRectangles.addRectangle(new Rectangle(10, 3)));
        arrayOfRectangles = new ArrayRectangles(new Rectangle[]{
                new Rectangle(4, 4),
                null
        });
        assertTrue(arrayOfRectangles.addRectangle(new Rectangle(10, 3)));
    }

    @Test
    void numberMaxArea() {
        assertEquals(4, arrayOfRectangles.numberMaxArea());
    }

    @Test
    void numberMinPerimeter() {
        assertEquals(3, arrayOfRectangles.numberMinPerimeter());
    }

    @Test
    void numberSquare() {
        assertEquals(2, arrayOfRectangles.numberSquare());
    }
}