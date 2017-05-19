package avl.implementation;

/*
 * Created by ahmed on 5/1/17.
 */
public interface INode<T extends Comparable<T>> {

    T getValue();

    void setValue(T value);

    int getHeight();

    void setHeight(int height);

    INode<T> getLeftChild();

    void setLeftChild(INode<T> node);

    INode<T> getRightChild();

    void setRightChild(INode<T> node);

}
