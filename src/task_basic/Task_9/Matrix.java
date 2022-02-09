package task_basic.Task_9;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Matrix {
    static Logger logger;
    static {
        logger = Logger.getLogger(Matrix.class.getName());
    }
    private double[][] matrixArray;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) throws MatrixException {
        if(rows <= 0 || columns <= 0) {
            logger.log(Level.WARNING, Errors.BAD_SIZE_ERROR.toString());
            throw new MatrixException(Errors.BAD_SIZE_ERROR.toString());
        }
        this.rows = rows;
        this.columns = columns;
        matrixArray = new double[rows][columns];
        for (double[] doubles : matrixArray)
            Arrays.fill(doubles, 0);
    }

    public Matrix(int rows, int columns, double[][] matrixArray) throws MatrixException {
        this(rows, columns);
        if(matrixArray.length != rows){
            logger.log(Level.WARNING, Errors.ROWS_DO_NOT_MATCH.toString());
        throw new MatrixException(Errors.ROWS_DO_NOT_MATCH.toString()); }
        for(double[] arr : matrixArray)
            if(arr.length != columns){
                logger.log(Level.WARNING, Errors.COLUMNS_DO_NOT_MATCH.toString());
                throw new MatrixException(Errors.COLUMNS_DO_NOT_MATCH.toString()); }
        this.matrixArray = matrixArray;
    }

    public void addElement(double elem, int indexRow, int indexColumn) throws MatrixException {
        checkForLength(indexRow, 'r');
        checkForLength(indexColumn, 'c');
        matrixArray[indexRow][indexColumn] = elem;
    }

    public void addMatrix(Matrix matrixToAdd) throws MatrixException {
        checkForLength(matrixToAdd);
        double[][] mtx = matrixToAdd.getMatrix();
        for(int i = 0; i < matrixArray.length; i++)
            for(int j = 0; j < matrixArray[i].length; j++)
                matrixArray[i][j] += mtx[i][j];
    }

    public void subtractMatrix(Matrix matrixToSubtract) throws MatrixException {
        checkForLength(matrixToSubtract);
        for(int i = 0; i < matrixArray.length; i++)
            for(int j = 0; j < matrixArray[i].length; j++)
                matrixArray[i][j] -= matrixToSubtract.getMatrix()[i][j];
    }

    public void multiplyMatrix(Matrix matrixToMultiply) throws MatrixException {
        checkForLength(matrixToMultiply);
        for(int i = 0; i < matrixArray.length; i++)
            for(int j = 0; j < matrixArray[i].length; j++)
                matrixArray[i][j] *= matrixToMultiply.getMatrix()[i][j];
    }

    private void checkForLength(Matrix matrixToAdd) throws MatrixException {
        if (matrixToAdd.getAmountOfColumns() != this.getAmountOfColumns() || matrixToAdd.getAmountOfRows() != this.getAmountOfRows()){
            logger.log(Level.WARNING, Errors.BAD_MATRIX_LENGTH_ERROR.toString());
            throw new MatrixException(Errors.BAD_MATRIX_LENGTH_ERROR.toString()); }
    }


    private void checkForLength(int length, char r) throws MatrixException {
        if(r == 'r' && (length < 0 || length > this.rows)){
            logger.log(Level.WARNING, Errors.BAD_ROW_ERROR.toString());
            throw new MatrixException(Errors.BAD_ROW_ERROR.toString()); }
        else if(r == 'c' && (length < 0 || length > this.columns)) {
            logger.log(Level.WARNING, Errors.BAD_COLUMN_ERROR.toString());
            throw new MatrixException(Errors.BAD_COLUMN_ERROR.toString()); }
    }

    public double[] getRow(int row) throws MatrixException {
        checkForLength(row, 'r');
        return matrixArray[row];
    }

    public double getElement(int row, int column) throws MatrixException {
        checkForLength(row, 'r');
        checkForLength(column, 'c');
        return matrixArray[row][column];
    }

    public int getAmountOfRows(){
        return this.rows;
    }

    public int getAmountOfColumns(){
        return this.columns;
    }

    public double[][] getMatrix() {
        return matrixArray;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(double[] row : matrixArray)
            str.append(Arrays.toString(row)).append('\n');
        return str.toString();
    }

}
