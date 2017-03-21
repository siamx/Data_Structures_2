package SortingAlgorithms;

/*
 * Created by Ahmed on 3/17/2017.
 */

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
class HeapSort<T extends Comparable<T>> {
    private List<T> heapArray;

    public void Sort(List<T> arrayHeap) {
        this.heapArray = arrayHeap;
        buildMaxHeap();
        List<T> sorted = new ArrayList<>();
        while (heapArray.size() > 0) {
            sorted.add(poll());
        }
        for (T aSorted : sorted)
            heapArray.add(aSorted);
    }

    private void buildMaxHeap() {
        for (int i = heapArray.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    //Max Heap-ify
    private void heapify(int i) {
        int l = getLeft(i);
        int r = getRight(i);
        int max = i;

        if (l < heapArray.size() && heapArray.get(l).compareTo(heapArray.get(max)) > 0)
            max = l;
        if (r < heapArray.size() && heapArray.get(r).compareTo(heapArray.get(max)) > 0)
            max = r;

        if (max != i) {
            swap(i, max);
            heapify(max);
        }
    }

    private void swap(int i, int j) {
        T t = heapArray.get(i);
        heapArray.set(i, heapArray.get(j));
        heapArray.set(j, t);
    }

    public T poll() {
        if (heapArray.size() == 0)
            throw new RuntimeException("Heap is Empty !");
        T root = heapArray.get(0);
        swap(0, heapArray.size() - 1);
        heapArray.remove(heapArray.size() - 1);
        heapify(0);
        return root;
    }

    private int getLeft(int node) {
        return (node << 1) + 1;
    }

    private int getRight(int node) {
        return (node << 1) + 2;
    }
}
