package SortingAlgorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
 * Created by Ahmed on 3/17/2017.
 */
@SuppressWarnings("unused")
class NLogN<T extends Comparable<T>> {

    //QUICK_SORT
    private int partition(List<T> arr, int start, int end) {
        swap(arr, start + new Random().nextInt(end - start + 1), end);
        T pivot = arr.get(start);
        int i = start;
        for (int j = start + 1; j <= end; j++) {
            if (arr.get(j).compareTo(pivot) <= 0) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i, start);
        return i;
    }

    private void swap(List<T> arr, int i, int j) {
        T t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    void quickSort(List<T> arr, int start, int end) {
        if (start >= end) return;
        int pivotIndex = partition(arr, start, end);
        quickSort(arr, start, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, end);
    }
    //QUICK_SORT_END

    /*
    ======================================================================================================
    ======================================================================================================
    ======================================================================================================
    ======================================================================================================
    ======================================================================================================
     */

    //MERGE_SORT
    void mergeSort(List<T> arr, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) >> 1;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        mergeArrays(arr, start, mid, end);
    }

    private void mergeArrays(List<T> arr, int start, int mid, int end) {
        List<T> sorted = new LinkedList<>();
        int i = start, j = mid + 1;

        while (i <= mid && j <= end)
            if (arr.get(i).compareTo(arr.get(j)) < 0)
                sorted.add(arr.get(i++));
            else
                sorted.add(arr.get(j++));

        while (i <= mid)
            sorted.add(arr.get(i++));
        while (j <= end)
            sorted.add(arr.get(j++));

        i = 0;
        for (j = start; j <= end; j++)
            arr.set(j, sorted.get(i++));
    }
    //MERGE_SORT_END
}
