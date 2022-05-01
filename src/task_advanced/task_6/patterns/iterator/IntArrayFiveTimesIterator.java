package task_advanced.task_6.patterns.iterator;

import task_advanced.task_6.patterns.IllegalFormatException;
import task_advanced.task_6.patterns.NotString;
import task_advanced.task_6.patterns.NotStringHandler;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayFiveTimesIterator implements Iterator<Integer> {
    @NotString
    private int[] array;
    @NotString
    private int size;
    private int currentPosition;
    private int currentSizeOfSourceArray = -1;
    private int counter;

    public IntArrayFiveTimesIterator(int[] array) {
        this.array = array;
        size = array.length * 5;
        try {
            NotStringHandler.checkIfItsNotString(array);
            NotStringHandler.checkIfItsNotString(size);
        } catch (IllegalFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        if(size > currentPosition)
            return true;
        else
            return false;
    }

    @Override
    public Integer next() {
        if(!hasNext())
            throw new NoSuchElementException();

        counter++;

        if(counter > 5)
            counter = 1;

        if(counter == 1)
            ++currentSizeOfSourceArray;

        currentPosition++;
        return array[currentSizeOfSourceArray];
    }
}
