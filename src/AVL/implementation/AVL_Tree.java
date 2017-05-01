package AVL.implementation;

/*
 * Created by ahmed on 5/1/17.
 */
public class AVL_Tree<T extends Comparable<T>> implements IAVL_Tree<T> {
    private static final int ALLOWED_IMBALANCE = 1;
    private INode<T> root;

    public AVL_Tree() {
        root = null;
    }

    /**
     * @return the getHeight of root, or -1, if null.
     */
    @Override
    public int getHeight() {
        return root == null ? -1 : root.getHeight();
    }

    @Override
    public boolean remove(T key) {
        boolean doesExist = search(key);
        if (doesExist) root = remove(key, root);
        return doesExist;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private INode<T> remove(T x, INode<T> t) {
        if (t == null) return null;

        int compareResult = x.compareTo(t.getValue());

        if (compareResult < 0) t.setLeftChild(remove(x, t.getLeftChild()));
        else if (compareResult > 0) t.setRightChild(remove(x, t.getRightChild()));
        else if (t.getLeftChild() != null && t.getRightChild() != null) {
            t.setValue(getMin(t.getRightChild()).getValue());
            t.setRightChild(remove(t.getValue(), t.getRightChild()));
        } else
            t = (t.getLeftChild() != null) ? t.getLeftChild() : t.getRightChild();

        return balance(t);
    }

    @Override
    public boolean search(T x) {
        return search(x, root);
    }

    private boolean search(T x, INode<T> t) {
        if (t == null) return false;
        if (t.getValue().compareTo(x) > 0)
            return search(x, t.getLeftChild());
        else // if compare result is 0 then return true,else search rightChild
            return t.getValue().compareTo(x) == 0 || search(x, t.getRightChild());
    }

    @Override
    public INode<T> getSubTree() {
        return null;
    }

    @Override
    public void insert(T x) {
        root = insert(x, root);
    }

    private INode<T> insert(T x, INode<T> t) {
        if (t == null) {
            return new Node<>(x, null, null);
        }

        int compareResult = t.getValue().compareTo(x);

        if (compareResult > 0) t.setLeftChild(insert(x, t.getLeftChild()));
        else if (compareResult < 0) t.setRightChild(insert(x, t.getRightChild()));
        else return t;// already exists
        updateHeight(t);
        return balance(t);
    }


    /**
     * @param t INode
     * @return balanced tree t
     */
    private INode<T> balance(INode<T> t) {
        if (t == null) return null;

        if (getHeight(t.getLeftChild()) - getHeight(t.getRightChild()) > ALLOWED_IMBALANCE)
            if (getHeight(t.getLeftChild().getLeftChild()) >= getHeight(t.getLeftChild().getRightChild()))
                t = rotateLeft(t);
            else
                t = doubleRotateLeft(t);

        else if (getHeight(t.getRightChild()) - getHeight(t.getLeftChild()) > ALLOWED_IMBALANCE)
            if (getHeight(t.getRightChild().getRightChild()) >= getHeight(t.getRightChild().getLeftChild()))
                t = rotateRight(t);
            else
                t = doubleRotateRight(t);

        t.setHeight(Math.max(getHeight(t.getLeftChild()), getHeight(t.getRightChild())) + 1);
        return t;
    }

    /**
     * Rotate binary tree node with left child.
     * For implementation trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private INode<T> rotateLeft(INode<T> t) {
        INode<T> left = t.getLeftChild();
        t.setLeftChild(left.getRightChild());
        left.setRightChild(t);
        t.setHeight(Math.max(t.getLeftChild().getHeight(), t.getRightChild().getHeight()) + 1);
        left.setHeight(Math.max(left.getLeftChild().getHeight(), t.getHeight()) + 1);
        return left;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node t with new left child.
     * For implementation trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private INode<T> doubleRotateLeft(INode<T> t) {
        t.setLeftChild(rotateRight(t.getLeftChild()));
        return rotateLeft(t);
    }

    /**
     * Rotate binary tree node with right child.
     * For implementation trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private INode<T> rotateRight(INode<T> t) {
        INode<T> right = t.getRightChild();
        t.setRightChild(right.getLeftChild());
        right.setLeftChild(t);
        t.setHeight(Math.max(getHeight(t.getRightChild()), getHeight(t.getLeftChild())) + 1);
        right.setHeight(Math.max(getHeight(right.getRightChild()), getHeight(t)) + 1);
        return right;
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node t with new right child.
     * For implementation trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private INode<T> doubleRotateRight(INode<T> t) {
        t.setRightChild(rotateLeft(t.getRightChild()));
        return rotateRight(t);
    }

    private int getHeight(INode<T> t) {
        return t == null ? -1 : t.getHeight();
    }

    private void updateHeight(INode<T> node) {
        int leftHeight = getHeight(node.getLeftChild());
        int rightHeight = getHeight(node.getRightChild());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private INode<T> getMin(INode<T> t) {
        INode<T> min = t;
        while (min.getLeftChild() != null) min = min.getLeftChild();
        return min;
    }
}
