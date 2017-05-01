package sortingAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Created by Ahmed on 3/21/2017.
 */
public class Sort_Compare {
    private final Random random = new Random();
    private final HeapSort<Integer> heapSort = new HeapSort<>();
    private final NSquared<Integer> nSquared = new NSquared<>();
    private final NLogN<Integer> nLogN = new NLogN<>();
    private int TEST_CASES = 50000;
    private int MAX_VALUE = Integer.MAX_VALUE;
    private List<Integer> actual = new ArrayList<>();
    private List<Integer> actualCopy = null;

    private Sort_Compare(int TEST_CASES, int MAX_VALUE) {
        this.TEST_CASES = TEST_CASES;
        this.MAX_VALUE = MAX_VALUE;
    }

    // MAIN !
    public static void main(String[] args) {
        for (int i = 4500; i < 6001; i += 500) {
            Sort_Compare sort_compare = new Sort_Compare(i, 0x7fffffff);
            sort_compare.runTest();
        }
    }

    private void setUp() {
        for (int i = 0; i < TEST_CASES; i++) {
            actual.add(random.nextInt(MAX_VALUE));
        }
    }

    private void runTest() {
        setUp();
        System.out.println("Testing Sorting Algorithms");
        System.out.println("Array Size: " + TEST_CASES + "   &   Max Value: " + MAX_VALUE);
        System.out.println();
        System.out.println(heapSortRunTime());
        System.out.println(quickSortRunTime());
        System.out.println(mergeSortRunTime());
        System.out.println(bubbleSortRunTime());
        System.out.println(selectionSortRunTime());
        System.out.println(insertionSortRunTime());
        System.out.println("===============================================\n");
    }

    private String mergeSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        nLogN.mergeSort(actualCopy, 0, actual.size() - 1);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Merge   Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String quickSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        nLogN.quickSort(actualCopy, 0, actual.size() - 1);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Quick   Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String bubbleSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.bubbleSort(actualCopy);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Bubble  Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }

    private String selectionSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.selectionSort(actualCopy);
        long endTime = getTime();
        long runTime = endTime - startTime;
        return "Selection Sort Run Time = " + String.valueOf(runTime) + " ms";
    }

    private String heapSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        heapSort.Sort(actualCopy);
        long runTime = getTime() - startTime;
        return "heap    Sort Run Time   = " + String.valueOf(runTime) + " ms";
    }


    private String insertionSortRunTime() {
        actualCopy = new ArrayList<>(actual);
        long startTime = getTime();
        nSquared.insertionSort(actualCopy);
        long runTime = getTime() - startTime;
        return "Insertion Sort Run Time = " + String.valueOf(runTime) + " ms";
    }

    private long getTime() {
        return System.currentTimeMillis();
    }
}
