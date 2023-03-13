package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.AbstractBinaryNode;
import edu.emory.cs.tree.AbstractBinarySearchTree;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public abstract class AbstractBalancedBinarySearchTree<T extends Comparable<T>, N extends AbstractBinaryNode<T, N>> extends AbstractBinarySearchTree<T, N> {
    /**
     * Rotates the specific node to the left.
     * @param node the node to be rotated.
     */
    protected void rotateLeft(N node) {
        N child = node.getRightChild();
        node.setRightChild(child.getLeftChild());

        if (node.hasParent())
            node.getParent().replaceChild(node, child);
        else
            setRoot(child);

        child.setLeftChild(node);
    }

    /**
     * Rotates the specific node to the right.
     * @param node the node to be rotated.
     */
    protected void rotateRight(N node) {
        N child = node.getLeftChild();
        node.setLeftChild(child.getRightChild());

        if (node.hasParent())
            node.getParent().replaceChild(node, child);
        else
            setRoot(child);

        child.setRightChild(node);
    }

//	============================== Override ==============================

    @Override
    public N add(T key) {
        N node = super.add(key);
        balance(node);
        return node;
    }

    @Override
    public N remove(T key) {
        N node = findNode(root, key); //return N node

        if (node != null) {
            N lowest = node.hasBothChildren() ? removeHibbard(node) : removeSelf(node); // removeHibbard delete node
            if (lowest != null && lowest != node) balance(lowest);
        }

        return node;
    }

    /**
     * Preserves the balance of the specific node and its ancestors.
     * @param node the node to be balanced.
     */
    protected abstract void balance(N node);
}