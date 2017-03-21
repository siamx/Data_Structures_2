package SortingAlgorithms;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * Created by Ahmed on 3/21/2017.
 */
@SuppressWarnings("FieldCanBeLocal")
public class Sort_Compare {
    private final int TEST_CASES = 50000;
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final List<Integer> expected = new ArrayList<>();
    private List<Integer> actual = null;
    private List<Integer> tmpActual = null;
    private final Random random = new Random();
    private final NSquared<Integer> nSquared = new NSquared<>();
    private final NLogN<Integer> nLogN = new NLogN<>();

    private void setUp() {
        for (int i = 0; i < TEST_CASES; i++) {
            expected.add(random.nextInt(MAX_VALUE));
        }
        actual = new ArrayList<>(expected);
        Collections.sort(expected);
    }

    @Test
    void test_all() {
        setUp();
        System.out.println(quickSortRunTime());
        System.out.println(mergeSortRunTime());
        System.out.println(bubbleSortRunTime());
        System.out.println(selectionSortRunTime());
    }

    private String mergeSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nLogN.mergeSort(tmpActual, 0, actual.size() - 1);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Merge   Sort Run Time   = " + String.valueOf(runTime);
    }

    private String quickSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nLogN.quickSort(tmpActual, 0, actual.size() - 1);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Quick   Sort Run Time   = " + String.valueOf(runTime);
    }

    private String bubbleSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.bubbleSort(tmpActual);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Bubble  Sort Run Time   = " + String.valueOf(runTime);
    }

    private String selectionSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.selectionSort(tmpActual);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Selection Sort Run Time = " + String.valueOf(runTime);
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
