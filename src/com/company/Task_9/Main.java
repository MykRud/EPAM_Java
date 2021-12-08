package com.company.Task_9;

public class Main {
    public static void main(String[] args) throws MatrixException {
        Matrix matrix1 = new Matrix(5, 6);
        double[][] arr = {{1, 2, 3, 4, 5, 6}, {4, 5, 6, 7, 8, 9}, {7, 8, 9, 10, 11, 12},
                          {10, 11, 12, 13, 14, 15}, {13, 14, 15, 16, 17, 18}};
        Matrix matrix2 = new Matrix(5, 6, arr);
        toStr(matrix1);
        matrix1.addElement(5.0, 0, 1);
        matrix1.addMatrix(matrix2);
        toStr(matrix1);
        matrix1.subtractMatrix(matrix2);
        toStr(matrix1);
        matrix1.addMatrix(matrix2);
        matrix1.multiplyMatrix(matrix2);
        toStr(matrix1);
    }

    private static void toStr(Matrix matrix) {
        System.out.println(matrix.toString());
        System.out.println('\n');
    }
}
