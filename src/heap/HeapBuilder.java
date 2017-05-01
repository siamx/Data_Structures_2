package heap;

import java.util.List;

/*
 * Created by Ahmed on 3/17/2017.
 */
@SuppressWarnings("unused")
public class HeapBuilder<T extends Comparable<T>> {

    public void buildMaxHeap(List<T> heapArr) {
        for (int i = heapArr.size() / 2; i >= 0; i--) {
            maxHeapify(heapArr, i);
        }
    }

    private void maxHeapify(List<T> heapArr, int i) {
        int l = getLeft(i);
        int r = getRight(i);
        int max = i;

        if (l < heapArr.size() && heapArr.get(l).compareTo(heapArr.get(max)) > 0)
            max = l;
        if (r < heapArr.size() && heapArr.get(r).compareTo(heapArr.get(max)) > 0)
            max = r;

        if (max != i) {
            swap(heapArr, i, max);
            maxHeapify(heapArr, max);
        }
    }

    private void swap(List<T> data, int i, int j) {
        T t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }

    private int getLeft(int node) {
        return (node << 1) + 1;
    }

    private int getRight(int node) {
        return (node << 1) + 2;
    }
}
