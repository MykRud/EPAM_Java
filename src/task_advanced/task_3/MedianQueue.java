package task_advanced.task_3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MedianQueue implements Iterable<Integer> {

    private Node head;
    private int size;

    public void offer(int elementToPut) {
        if (head == null) {
            head = new Node(elementToPut);
            size++;
            return;
        }
        Node currentNode = head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(new Node(elementToPut));
        size++;
    }

    public int poll() {
        int[] arrayOfNumbers = getArray();
        int temp = 0;
        if (size % 2 == 0)
            temp = 1;
        int median = arrayOfNumbers[arrayOfNumbers.length / 2 - temp];
        Node currentNode = head;
        Node prevNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getValue() == median) {
                break;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (currentNode == head)
            head = currentNode.getNext();
        else
            prevNode.setNext(currentNode.getNext());
        size--;
        return median;
    }

    public int peek() {
        int[] arrayOfNumbers = getArray();
        int temp = 0;
        if (size % 2 == 0)
            temp = 1;
        return arrayOfNumbers[arrayOfNumbers.length / 2 - temp];
    }

    private int[] getArray() {
        int[] arrayOfNumbers = new int[size];
        Node currentNode = head;
        int i = 0;
        while (currentNode != null) {
            arrayOfNumbers[i++] = currentNode.getValue();
            currentNode = currentNode.getNext();
        }
        quicksort(arrayOfNumbers, 0, size - 1);
        return arrayOfNumbers;
    }

    private void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int newIndex = partOfSort(array, low, high);

            quicksort(array, low, newIndex - 1);
            quicksort(array, newIndex + 1, high);
        }
    }

    private int partOfSort(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return (i + 1);
    }

    public String getSortedArray() {
        return Arrays.toString(getArray());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = head;
        for (int i = 0; i < size - 1; i++) {
            sb.append(currentNode.getValue()).append(", ");
            currentNode = currentNode.getNext();
        }
        sb.append(currentNode.getValue()).append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MedianQueueIterator();
    }

    private class MedianQueueIterator implements Iterator<Integer> {

        int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return currentPosition != size - 1;
        }

        @Override
        public Integer next() {
            Node currentNode = head;
            currentPosition++;
            for (int i = 0; i < currentPosition; i++) {
                if (currentNode == null)
                    throw new NoSuchElementException("Element has not been found");
                currentNode = currentNode.getNext();
            }
            return currentNode.getValue();
        }
    }

    private static class Node {

        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static void main(String[] args) {
        MedianQueue queue = new MedianQueue();
        queue.offer(5);
        queue.offer(2);
        queue.offer(24);
        queue.offer(10);
        queue.offer(50);
        queue.offer(7);

        System.out.println("Before: " + queue);
        System.out.println("Sorted: " + queue.getSortedArray());

        System.out.println(queue.poll());

        System.out.println("After: " + queue);

        System.out.println("The element in the top: " + queue.peek());

        Iterator<Integer> iterator = queue.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());
    }
}
