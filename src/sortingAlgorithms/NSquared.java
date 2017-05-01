package sortingAlgorithms;

import java.util.List;

/*
 * Created by Ahmed on 3/17/2017.
 */

@SuppressWarnings("unused")
class NSquared<T extends Comparable<T>> {

    void selectionSort(List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            int min = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j).compareTo(data.get(min)) < 0) {
                    min = j;
                }
            }
            swap(data, i, min);
        }
    }

    void bubbleSort(List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 1; j < data.size() - i; j++) {
                if (data.get(j).compareTo(data.get(j - 1)) < 0) {
                    swap(data, j, j - 1);
                }
            }
        }
    }


    void insertionSort(List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            int hole = i;
            while (hole > 0 && data.get(hole).compareTo(data.get(hole - 1)) < 0) {
                swap(data, hole, --hole);
            }
        }
    }


    private void swap(List<T> data, int i, int j) {
        T t = data.get(i);
        data.set(i, data.get(j));
        data.set(j, t);
    }
}
