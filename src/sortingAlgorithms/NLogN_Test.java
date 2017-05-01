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
class NLogN_Test {
    private final int TEST_SIZE = 10000;
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final List<Integer> expected = new ArrayList<>();
    private final NLogN<Integer> nLogN = new NLogN<>();
    private final Random random = new Random();
    private List<Integer> actual = null;

    private void setUp(int MAX) {
        for (int i = 0; i < TEST_SIZE; i++) {
            Integer num = random.nextInt(MAX);
            expected.add(num);
        }
        actual = new ArrayList<>(expected);
        Collections.sort(expected);
    }

    @Test
    void testQuickSort() {
        setUp(MAX_VALUE);
        System.out.println(actual);
        nLogN.quickSort(actual, 0, actual.size() - 1);
        System.out.println(actual);
        System.out.println(expected);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void testMergeSort() {
        setUp(MAX_VALUE);
        System.out.println(actual);
        nLogN.mergeSort(actual, 0, actual.size() - 1);
        System.out.println(actual);
        System.out.println(expected);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}