package edu.emory.cs.tree;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractBinarySearchTree<T extends Comparable<T>, N extends AbstractBinaryNode<T, N>> {
    protected N root;

    public AbstractBinarySearchTree() {
        setRoot(null);
    }

    /** @return a new node with the specific key. */
    abstract public N createNode(T key);

    /** @return {@code true} if the specific node is the root of this tree. */
    public boolean isRoot(N node) {
        return root == node;
    }

    /** @return the root of this tree. */
    public N getRoot() {
        return root;
    }

    /** Sets the root of this tree to the specific node. */
    public void setRoot(N node) {
        if (node != null) node.setParent(null);
        root = node;
    }

    /** @return the node with the specific key if exists; otherwise, {@code null}. */
    public N get(T key) {
        return findNode(root, key);
    }

    /** @return the node with the specific key if exists; otherwise, {@code null}. */
    protected N findNode(N node, T key) {
        if (node == null) return null;
        int diff = key.compareTo(node.getKey());

        if (diff < 0)
            return findNode(node.getLeftChild(), key);
        else if (diff > 0)
            return findNode(node.getRightChild(), key);
        else
            return node;
    }

    /** @return the node with the minimum key under the subtree of {@code node}. */
    protected N findMinNode(N node) {
        return node.hasLeftChild() ? findMinNode(node.getLeftChild()) : node;
    }

    /** @return the node with the maximum key under the subtree of {@code node}. */
    protected N findMaxNode(N node) {
        return node.hasRightChild() ? findMaxNode(node.getRightChild()) : node;
    }

//	============================== Add ==============================

    /**
     * @return the new node that is added to this tree.
     * If this tree already contains the key, nothing is added.
     */
    public N add(T key) {
        N node;

        if (root == null)
            setRoot(node = createNode(key));
        else
            node = addAux(root, key);

        return node;
    }

    private N addAux(N node, T key) {
        int diff = key.compareTo(node.getKey());
        N child, newNode = null;

        if (diff < 0) {
            if ((child = node.getLeftChild()) == null)
                node.setLeftChild(newNode = createNode(key));
            else
                newNode = addAux(child, key);
        }
        else if (diff > 0) {
            if ((child = node.getRightChild()) == null)
                node.setRightChild(newNode = createNode(key));
            else
                newNode = addAux(child, key);
        }

        return newNode;
    }

//	============================== Remove ==============================

    /** @return the removed node with the specific key if exists; otherwise, {@code null}. */
    public N remove(T key) {
        N node = findNode(root, key);

        if (node != null) {
            if (node.hasBothChildren()) removeHibbard(node);
            else removeSelf(node);
        }

        return node;
    }

    private void replaceChild(N oldNode, N newNode) {
        if (isRoot(oldNode))
            setRoot(newNode);
        else
            oldNode.getParent().replaceChild(oldNode, newNode);
    }

    /** @return the lowest node whose subtree has been updatede. */
    protected N removeHibbard(N node) {
        N successor = node.getRightChild();
        N min = findMinNode(successor); //一直往下找最小的
        N parent = min.getParent(); //最小的parent

        min.setLeftChild(node.getLeftChild()); //把最小的leftchild设置成node的leftchild

        if (min != successor) { //如果node不是最小的母亲，node的右孩子不是最小值，
            parent.setLeftChild(min.getRightChild()); //最小的rightchild变成最小的母亲的左孩子
            min.setRightChild(successor);//node的rightchild变成最小的rightchild
        }

        replaceChild(node, min); //把最小和node换位
        return parent;
    }

    /** @return the lowest node whose subtree has been updatede. */
    protected N removeSelf(N node) {
        N parent = node.getParent();
        N child = null;

        if (node.hasLeftChild()) child = node.getLeftChild();
        else if (node.hasRightChild()) child = node.getRightChild();
        replaceChild(node, child);

        return parent;
    }

//	============================== Min/Max ==============================

    /** @return {@code true} if the specific key exists; otherwise, {@code false}. */
    public boolean contains(T key) {
        return get(key) != null;
    }

    /** @return the minimum key in this tree if exists; otherwise, {@code null}. */
    public T min() {
        return (root != null) ? findMinNode(root).getKey() : null;
    }

    /** @return the maximum key in this tree if exists; otherwise, {@code null}. */
    public T max() {
        return (root != null) ? findMaxNode(root).getKey() : null;
    }

    public String toString() {
        return (root != null) ? root.toString() : "null";
    }
}