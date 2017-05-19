package avl.implementation;

/*
 * Created by ahmed on 5/1/17.
 */
public class Node<T extends Comparable<T>> implements INode<T> {
    private T element;
    private INode<T> left;
    private INode<T> right;
    private int height;

    Node(T element, Node<T> left, Node<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public T getValue() {
        return element;
    }

    @Override
    public void setValue(T value) {
        this.element = value;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public INode<T> getLeftChild() {
        return left;
    }

    @Override
    public void setLeftChild(INode<T> node) {
        this.left = node;
    }

    @Override
    public INode<T> getRightChild() {
        return right;
    }

    @Override
    public void setRightChild(INode<T> node) {
        this.right = node;
    }
}
