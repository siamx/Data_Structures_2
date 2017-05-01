package sortingAlgorithms;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/*
 * Created by Ahmed on 3/17/2017.
 */

@SuppressWarnings("FieldCanBeLocal")
class HeapSort_Test {
    private final int TEST_SIZE = 10000;
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final List<Integer> expected = new ArrayList<>();
    private final Random random = new Random();
    private List<Integer> actual = null;

    private void setUp() {
        for (int i = 0; i < TEST_SIZE; i++) {
            Integer num = random.nextInt(MAX_VALUE);
            expected.add(num);
        }
        actual = new ArrayList<>(expected);
        expected.sort(Collections.reverseOrder());
    }

    @Test
    void testHeapSort() {
        setUp();
        // Before Sort
        System.out.println(" Unsorted data : " + actual);

        //Sort
        HeapSort<Integer> heapsort = new HeapSort<>();
        heapsort.Sort(actual);

        //After Sort
        System.out.println("My sorted data : " + actual);
        System.out.println(" Expected data : " + expected);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}