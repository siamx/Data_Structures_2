package SortingAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

/*
 * Created by Ahmed on 3/20/2017.
 */

@SuppressWarnings("FieldCanBeLocal")
public class NSquared_Test {
    private final int TEST_CASES = 1000;
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final List<Integer> expected = new ArrayList<>();
    private final Random random = new Random();
    private final NSquared<Integer> nSquared = new NSquared<>();
    private List<Integer> actual = null;

    private void setUp() {
        for (int i = 0; i < TEST_CASES; i++) {
            expected.add(random.nextInt(MAX_VALUE));
        }
        actual = new ArrayList<>(expected);
        Collections.sort(expected);
    }

    @Test
    void testSelectionSort() {
        setUp();
        System.out.println("Data :" + actual);
        nSquared.selectionSort(actual);
        System.out.println("My Sort  :" + actual);
        System.out.println("Expected :" + expected);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testBubbleSort() {
        setUp();
        System.out.println("Data :" + actual);
        nSquared.bubbleSort(actual);
        System.out.println("My Sort  :" + actual);
        System.out.println("Expected :" + expected);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testInsertionSort() {
        setUp();
        System.out.println("Data :" + actual);
        nSquared.insertionSort(actual);
        System.out.println("My Sort  :" + actual);
        System.out.println("Expected :" + expected);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
