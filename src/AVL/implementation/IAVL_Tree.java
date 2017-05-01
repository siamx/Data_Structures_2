package AVL.implementation;

/*
 * Created by ahmed on 5/1/17.
 */
public interface IAVL_Tree<T extends Comparable<T>> {

    int getHeight();

    boolean remove(T key);

    boolean search(T key);

    INode<T> getSubTree();

    void insert(T key);

}