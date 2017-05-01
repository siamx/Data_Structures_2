package heap;

/*
 * Created by Ahmed on 3/17/2017.
 */
@SuppressWarnings("unused")

public interface IHeap<T> {
    void insert(T object);

    int size();

    boolean isEmpty();

    T peek();

    T poll();
}
