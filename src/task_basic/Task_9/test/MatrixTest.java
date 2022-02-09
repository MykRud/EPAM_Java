package task_basic.Task_9.test;

import task_basic.Task_9.Matrix;
import task_basic.Task_9.MatrixException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    static Matrix matrix1;
    static Matrix matrix2;
    static double[][] arr = {{1, 2, 3, 4, 5}, {4, 5, 6, 7, 8}, {7, 8, 9, 10, 11},
            {10, 11, 12, 13, 14}, {13, 14, 15, 16, 17}};

    @BeforeClass
    public static void setup() throws MatrixException {
        matrix1 = new Matrix(5, 6);
        matrix2 = new Matrix(5, 5, arr);
    }

    @Test
    public void testForBadInitialisation(){
        assertThrows(MatrixException.class, () -> new Matrix(5, 4, arr));
    }


    @Test
    public void addElement() {
        assertThrows(MatrixException.class, () -> matrix1.addElement(5.0, -1, -2));
    }

    @Test
    public void addMatrix() {
        assertThrows(MatrixException.class, () -> matrix1.addMatrix(matrix2));
    }

    @Test
    public void subtractMatrix() {
        assertThrows(MatrixException.class, () -> matrix1.subtractMatrix(matrix2));
    }

    @Test
    public void multiplyMatrix() {
        assertThrows(MatrixException.class, () -> matrix1.multiplyMatrix(matrix2));
    }

    @Test
    public void getRow() {
        assertThrows(MatrixException.class, () -> matrix1.getRow(8));
    }

    @Test
    public void getElement() {
        assertThrows(MatrixException.class, () -> matrix1.getElement(8, 99));
    }
}