package task_advanced.task_1.secondTask;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl <T> implements Array <T> {
    private Object[] innerArray;
    private int size;
    private final Object[] primaryArray = new Object[10];

    public ArrayImpl(){
        innerArray = primaryArray;
    }

    public ArrayImpl(int size){
        innerArray = new Object[size];
    }

    @Override
    public void add(T element) {
        if(size+1 >= innerArray.length) // Decomposition
            extendInnerArray();
        innerArray[size++] = element;
    }

    @Override
    public void set(int index, T element) {
        if(size >= innerArray.length)
            extendInnerArray();

        Object[] newArray = new Object[innerArray.length];
        int i = 0;
        int j = 0;
        while(i != size+1){
            if(i == index){
                newArray[i++] = element;
                continue;
            }
            newArray[i++] = innerArray[j++];
        }
        innerArray = newArray;
        size++;
    }

    @Override
    public T get(int index) {
        if(index > size)
            return null;
        return (T)innerArray[index];
    }

    @Override
    public int indexOf(T element) {
        for(int i = 0; i < size; i++){
            if(innerArray[i].equals(element))
                return i;
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if(index < 0 || index > size)
            throw new NoSuchElementException();

        Object[] newArray = new Object[innerArray.length];
        int i = 0;
        int j = 0;
        while(j != size+1){
            if(j == index){
                j++;
                continue;
            }
            newArray[i++] = innerArray[j++];
        }
        innerArray = newArray;
        size--;
    }

    @Override
    public void clear() {
        innerArray = primaryArray;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void extendInnerArray(){
        Object[] newArray = new Object[innerArray.length * 2];
        for(int i = 0; i < innerArray.length; i++) {
            newArray[i] = innerArray[i];
        }
        innerArray = newArray;
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {

        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return currentPosition + 1 != size;
        }

        @Override
        public T next() {
            if(currentPosition == size)
                throw new NoSuchElementException();
            return (T)innerArray[++currentPosition];
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(currentPosition--);
        }
    }

    @Override
    public String toString() {
        StringBuilder returningString = new StringBuilder("[");
        for(int i = 0; i < size-1; i++)
            returningString.append(innerArray[i]).append(" ");
        return returningString.append(innerArray[size - 1]).append("]").toString();
    }

    public static void main(String[] args) {
        ArrayImpl<String> array = new ArrayImpl<String>();

        array.add("firstWord");
        array.add("secondWord");
        array.add("thirdWord");

        System.out.println("Array after adding: " + array + ", Size: " + array.size());

        array.set(2, "newString");

        System.out.println("Array after set(): " + array + ", Size: " + array.size());

        System.out.println("Element with index 0: " + array.get(0));

        System.out.println("The index of the element \"secondWord\": " + array.indexOf("second_Word"));

        array.remove(2);
        System.out.println("Array after removing: " + array + ", Size: " + array.size());

        for (Object obj : array) {
            System.out.println(obj.toString());
        }

        array.add("zero");
        array.add("Smile");
        array.add("zero");
        System.out.println("Array before removing with iterator: " + array);

        Iterator<String> iterator = array.iterator();
        while(iterator.hasNext()) {
            String toRemove = iterator.next();
            if (toRemove.equals("zero"))
                iterator.remove();
        }
        System.out.println("Array after removing with iterator: " + array);

    }
}
