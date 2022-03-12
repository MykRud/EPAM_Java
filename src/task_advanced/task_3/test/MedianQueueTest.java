package task_advanced.task_3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task_advanced.task_3.MedianQueue;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MedianQueueTest {

    static MedianQueue queue;

    @BeforeEach
    void init(){
        queue = new MedianQueue();
    }

    @Test
    void shouldOfferElements() {
        queue.offer(5);
        queue.offer(2);
        queue.offer(24);
        queue.offer(10);
        queue.offer(50);
        queue.offer(7);
        assertEquals("[5, 2, 24, 10, 50, 7]", queue.toString());
    }

    @Test
    void shouldPollMedianElement() {
        queue.offer(5);
        queue.offer(2);
        queue.offer(10);
        assertEquals(5, queue.poll());
    }

    @Test
    void shouldPeekMedianElement() {
        queue.offer(5);
        queue.offer(2);
        queue.offer(10);
        assertEquals(5, queue.peek());
    }

    @Test
    void shouldReturnSortedArray() {
        queue.offer(5);
        queue.offer(2);
        queue.offer(10);
        assertEquals("[2, 5, 10]", queue.getSortedArray());
    }

    @Test
    void iterator() {
        queue.offer(5);
        queue.offer(6);
        queue.offer(1);
        Iterator<Integer> iterator = queue.iterator();
        String result = "";
        while(iterator.hasNext())
            result += iterator.next();
        assertEquals("561", result );
    }
}