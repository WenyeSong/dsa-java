package edu.emory.cs.tree;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class BinaryNode<T extends Comparable<T>> extends AbstractBinaryNode<T, BinaryNode<T>> {
    public BinaryNode(T key) {
        super(key);
    }
}