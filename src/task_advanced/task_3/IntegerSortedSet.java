package task_advanced.task_3;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class IntegerSortedSet implements Iterable<Integer>{

    private Node head; // 3
    private int size;

    public void add(int element) {
        if (head == null) {
            head = new Node(element);
            size++;
            return;
        }
        Node currentNode = head;
        while(currentNode != null){
            if(currentNode.getValue() == element)
                return;
            currentNode = currentNode.getNext();
        }
        addElementByOrdering(element);
    }

    private void addElementByOrdering(int valueToAdd){
        Node currentNode = head;
        Node prevNode = head;
        while(currentNode.getNext() != null){
            if(currentNode.getValue() > Math.abs(valueToAdd))
                break;
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        Node tempNode = new Node(valueToAdd);
        if(currentNode == head){
            if(head.getValue() > Math.abs(valueToAdd)){
                tempNode.setNext(currentNode);
                head = tempNode;
            } else{
                head.setNext(currentNode);
            }
            size++;
            return;
        }
        if(currentNode.getNext() == null){
            if(currentNode.getValue() > Math.abs(valueToAdd)){
                tempNode.setNext(currentNode);
                prevNode.setNext(tempNode);
            } else {
                currentNode.setNext(tempNode);
            }
            size++;
            return;
        }
        tempNode.setNext(currentNode);
        prevNode.setNext(tempNode);
        size++;
    }

    public int remove(int elementToRemove){
        Node currentNode = head;
        if(currentNode.getValue() == elementToRemove){
            head = currentNode.getNext();
            size--;
            return currentNode.getValue();
        }
        while(currentNode.getNext() != null){
            if(currentNode.getNext().getValue() == elementToRemove){
                Node removedNode = currentNode.getNext();
                currentNode.setNext(removedNode.getNext());
                size--;
                return removedNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        throw new IllegalArgumentException("Element has not been found");
    }

    public int search(int elementToSearch){
        Node currentNode = head;
        if(currentNode.getValue() == elementToSearch){
            return currentNode.getValue();
        }
        while(currentNode.getNext() != null){
            if(currentNode.getNext().getValue() == elementToSearch){
                return currentNode.getNext().getValue();
            }
            currentNode = currentNode.getNext();
        }
        throw new IllegalArgumentException("Element has not been found");
    }

    public void addOf(Collection<Integer> collectionToAdd){
        Iterator<Integer> iter = collectionToAdd.iterator();
        while(iter.hasNext()){
            this.add(iter.next());
        }
    }

    public Iterator<Integer> iterator(){
        return new IntegerSortedSetIterator();
    }

    private class IntegerSortedSetIterator implements Iterator<Integer>{

        int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return size - 1 == currentIndex;
        }

        @Override
        public Integer next() {
            Node currentNode = head;
            for(int i = 0; i < ++currentIndex; i++){
                currentNode = currentNode.getNext();
            }
            return currentNode.getValue();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = head;
        for(int i = 0; i < size - 1; i++){
            sb.append(currentNode.getValue()).append(", ");
            currentNode = currentNode.getNext();
        }
        sb.append(currentNode.getValue()).append("]");
        return sb.toString();
    }

    private class Node {
        private int value;
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
    }

    public static void main(String[] args) {
        IntegerSortedSet set = new IntegerSortedSet();
        set.add(5);
        set.add(2);
        set.add(7);
        set.add(1);
        set.add(-1);
        set.add(3);
        set.add(-1);
        set.add(9);
        set.add(-9);
        set.add(-9);
        set.add(24);
        set.add(-8);
        System.out.println("Set before removing: " + set);
        set.remove(7);
        set.remove(2);
        set.remove(-9);
        System.out.println("Set after removing: " + set);
        System.out.println("Element 5: " + set.search(5));
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(15);
        treeSet.add(12);
        treeSet.add(25);
        treeSet.add(17);
        treeSet.add(19);
        set.addOf(treeSet);
        System.out.println("Set with added TreeSet: " + set);
        System.out.println("---------------------------------------------");
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
