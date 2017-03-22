package SortingAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * Created by Ahmed on 3/21/2017.
 */
public class Sort_Compare {
    private final List<Integer> expected = new ArrayList<>();
    private final Random random = new Random();
    private final NSquared<Integer> nSquared = new NSquared<>();
    private final NLogN<Integer> nLogN = new NLogN<>();
    private int TEST_CASES = 50000;
    private int MAX_VALUE = Integer.MAX_VALUE;
    private List<Integer> actual = null;
    private List<Integer> tmpActual = null;

    private Sort_Compare(int TEST_CASES, int MAX_VALUE) {
        this.TEST_CASES = TEST_CASES;
        this.MAX_VALUE = MAX_VALUE;
    }

    // MAIN !
    public static void main(String[] args) {
        new Sort_Compare(30000, 1000).runTest();
    }

    private void setUp() {
        for (int i = 0; i < TEST_CASES; i++) {
            expected.add(random.nextInt(MAX_VALUE));
        }
        actual = new ArrayList<>(expected);
        Collections.sort(expected);
    }

    private void runTest() {
        setUp();
        System.out.println("Testing Sorting Algorithms");
        System.out.println("Array Size: " + TEST_CASES + "   &   Max Integer Value: " + MAX_VALUE);
        System.out.println();
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
        return "Merge   Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String quickSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nLogN.quickSort(tmpActual, 0, actual.size() - 1);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Quick   Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String bubbleSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.bubbleSort(tmpActual);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Bubble  Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String selectionSortRunTime() {
        tmpActual = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.selectionSort(tmpActual);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Selection Sort Run Time = " + String.valueOf(runTime) + " ms";
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
