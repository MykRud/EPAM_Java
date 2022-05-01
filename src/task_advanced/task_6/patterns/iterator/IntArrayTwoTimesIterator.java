package task_advanced.task_6.patterns.iterator;

import task_advanced.task_6.patterns.IllegalFormatException;
import task_advanced.task_6.patterns.NotString;
import task_advanced.task_6.patterns.NotStringHandler;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayTwoTimesIterator implements Iterator<Integer> {
    @NotString
    private int[] array;
    @NotString
    private int size;
    private int currentPosition;
    private int currentSizeOfSourceArray;

    public IntArrayTwoTimesIterator(int[] array) {
        this.array = array;
        size = array.length * 2;
        try {
            NotStringHandler.checkIfItsNotString(array);
            NotStringHandler.checkIfItsNotString(size);
        } catch (IllegalFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        if(size > currentPosition )
            return true;
        else
            return false;
    }

    @Override
    public Integer next() {
        if(!hasNext())
            throw new NoSuchElementException();
        if(currentPosition % 2 != 0)
            currentSizeOfSourceArray--;
        currentPosition++;
        return array[currentSizeOfSourceArray++];
    }

}
