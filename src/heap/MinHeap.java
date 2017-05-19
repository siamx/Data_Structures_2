package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 5/19/17.
 */

public class MinHeap<T extends Comparable<T>> implements IHeap<T> {
    private final List<T> heapArr;

    public MinHeap() {
        this.heapArr = new ArrayList<>();
    }

    public MinHeap(List<T> heapArr) {
        this.heapArr = heapArr;
    }

    private void minHeapify(int i) {
        int l = getLeft(i);
        int r = getRight(i);
        int min = i;

        if (l < heapArr.size() && heapArr.get(l).compareTo(heapArr.get(min)) < 0)
            min = l;
        if (r < heapArr.size() && heapArr.get(r).compareTo(heapArr.get(min)) < 0)
            min = r;

        if (min != i) {
            swap(i, min);
            minHeapify(min);
        }
    }

    private void swap(int i, int j) {
        T t = heapArr.get(i);
        heapArr.set(i, heapArr.get(j));
        heapArr.set(j, t);
    }


    @Override
    public void insert(T object) {
        heapArr.add(object);
        int child = getLastIndex();
        int parent = getParent(child);
        while (parent >= 0 && heapArr.get(parent).compareTo(heapArr.get(child)) > 0) {
            swap(child, parent);
            child = parent;
            parent = getParent(child);
        }
    }

    @Override
    public T poll() {
        if (isEmpty())
            throw new RuntimeException("heap is Empty !");
        T root = heapArr.get(0);
        swap(0, getLastIndex());
        heapArr.remove(getLastIndex());
        minHeapify(0);
        return root;
    }

    @Override
    public int size() {
        return heapArr.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T peek() {
        return size() == 0 ? null : heapArr.get(0);
    }

    private int getLastIndex() {
        return size() > 0 ? size() - 1 : 0;
    }

    private int getParent(int node) {
        return (node - 1) >> 1;
    }

    private int getLeft(int node) {
        return (node << 1) + 1;
    }

    private int getRight(int node) {
        return (node << 1) + 2;
    }
}
