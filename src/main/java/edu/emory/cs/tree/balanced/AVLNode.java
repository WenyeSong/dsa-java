package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.AbstractBinaryNode;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AVLNode<T extends Comparable<T>> extends AbstractBinaryNode<T, AVLNode<T>> {
    /** The height of this node. */
    private int height;

    public AVLNode(T key) {
        super(key);
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setLeftChild(AVLNode<T> node) {
        super.setLeftChild(node);
        resetHeights();
    }

    @Override
    public void setRightChild(AVLNode<T> node) {
        super.setRightChild(node);
        resetHeights();
    }

    /** Resets the heights of this node and its ancestor, recursively. */
    public void resetHeights() {
        resetHeightsAux(this);
    }

    private void resetHeightsAux(AVLNode<T> node) {
        if (node != null) {
            int lh = node.hasLeftChild() ? node.getLeftChild().getHeight() : 0;
            int rh = node.hasRightChild() ? node.getRightChild().getHeight() : 0;
            int height = Math.max(lh, rh) + 1;

            if (height != node.getHeight()) {
                node.setHeight(height);
                resetHeightsAux(node.getParent());  // recursively update parent height
            }
        }
    }

    /** @return height(left-subtree) - height(right-subtree). */
    public int getBalanceFactor() {
        if (hasBothChildren())
            return left_child.getHeight() - right_child.getHeight();
        else if (hasLeftChild())
            return left_child.getHeight();
        else if (hasRightChild())
            return -right_child.getHeight();
        else
            return 0;
    }

    public String toString() {
        return key + ":" + height + " -> (" + left_child + ", " + right_child + ")";
    }
}