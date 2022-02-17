package task_advanced.task_2.businessLogic.collections.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl<T> implements Queue<T> {

    private Object[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public QueueImpl() {
        queue = new Object[DEFAULT_CAPACITY];
        //  this.type = ((ParameterizedType) getClass()
        //        .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private void grow() {
        Object[] newQueue = new Object[queue.length * 2];
        for (int i = 0; i < size; i++)
            newQueue[i] = queue[i];
        queue = newQueue;
    }

    @Override
    public void enqueue(T element) {
        if (queue.length == size) {
            grow();
        }
        queue[size++] = element;
    }

    @Override
    public T dequeue() {
        T temp = (T) queue[0];
        Object[] newQueue = new Object[queue.length];
        for (int i = 1; i < size; i++) {
            newQueue[i - 1] = queue[i];
        }
        queue = newQueue;

        size--;
        return temp;
    }

    @Override
    public T top() {
        return (T) queue[0];
    }

    @Override
    public void clear() {
        queue = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] asList() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++)
            array[i] = queue[i];
        return (T[]) array;
    }

    @Override
    public void setList(T[] array) {
        queue = array;
        size = array.length;
    }

    @Override
    public String toString() {
        StringBuilder returningString = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++)
            returningString.append(queue[i]).append(" ");
        return returningString.append(queue[size - 1]).append("]").toString();
    }

    @Override
    public IteratorImpl iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {

        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition != size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (T) queue[currentPosition++];
        }

        @Override
        public void remove() {
            /*Object[] newQueue = new Object[queue.length];
            int i = 0;
            int j = 0;
            while (i != size) {
                if (queue[i] == queue[currentPosition]) {
                    j--;
                    continue;
                }
                newQueue[j++] = queue[i++];
            }
            queue = newQueue;
            size--;
            currentPosition--;*/

            Object[] newArray = new Object[queue.length];
            int i = 0;
            int j = 0;
            while(j != size + 1){
                if(j == currentPosition - 1){
                    j++;
                    continue;
                }
                newArray[i++] = queue[j++];
            }
            queue = newArray;
            size--;
        }
    }

    public static void main(String[] args) {
        QueueImpl<String> queue = new QueueImpl<>();
        queue.enqueue("String1");
        queue.enqueue("String2");
        queue.enqueue("String3");
        queue.enqueue("String4");
        queue.enqueue("String5");
        queue.enqueue("String6");

        System.out.println(queue);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        System.out.println(queue);

        for (String str : queue)
            System.out.println(str);

        queue.enqueue("nonZero");
        queue.enqueue("zero");
        queue.enqueue("nonZero1");

        System.out.println("Queue before removing with iterator: " + queue);


        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String toRemove = iterator.next();
            if (toRemove.equals("zero"))
                iterator.remove();
        }
        System.out.println("Queue after removing with iterator: " + queue);
    }
}
