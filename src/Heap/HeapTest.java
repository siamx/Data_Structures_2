package Heap;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Created by Ahmed on 3/17/2017.
 */

class HeapTest {

    @Test
    void testBuildMaxHeap() {
        HeapBuilder<Integer> heapBuilder = new HeapBuilder<>();
        Integer[] arr = {2, 3, 7, 10, 0, -9, 4, 50, 49, 1, 8};
        List<Integer> heapArray = new ArrayList<>(Arrays.asList(arr));
        heapBuilder.buildMaxHeap(heapArray);
        System.out.println("Built Heap : " + heapArray);
    }

    @Test
    void testMaxHeap() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        IHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();

        for (int i = 0; i < random.nextInt(10000); i++) {
            int randomNum = random.nextInt(Integer.MAX_VALUE);
            queue.add(randomNum);
            maxHeap.insert(randomNum);
        }

        queue.poll();
        maxHeap.poll();

        while (!queue.isEmpty() && !maxHeap.isEmpty()) {
            assertEquals(queue.size(), maxHeap.size());
            assertEquals(queue.peek(), maxHeap.peek());
            assertEquals(queue.poll(), maxHeap.poll());
            assertEquals(queue.size(), maxHeap.size());
            assertEquals(queue.isEmpty(), maxHeap.isEmpty());
        }
        assertEquals(queue.isEmpty(), maxHeap.isEmpty());
    }
}