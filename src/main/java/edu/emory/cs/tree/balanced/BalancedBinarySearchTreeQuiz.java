package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.BinaryNode;


public class BalancedBinarySearchTreeQuiz<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, BinaryNode<T>> {
    @Override
    public BinaryNode<T> createNode(T key) {
        return new BinaryNode<>(key);
    }

    @Override
    protected void balance(BinaryNode<T> node) {
        // TODO: to be filled
        if (node.hasParent() && node.getParent().hasParent() && node.getParent().getParent().hasBothChildren()) {
            BinaryNode<T> parent = node.getParent();
            BinaryNode<T> uncle = node.getUncle();
            BinaryNode<T> grandParent = node.getGrandParent();

             if ((!parent.hasBothChildren()) && grandParent.isRightChild(parent) && !uncle.hasBothChildren()) {
           // whether the node is only child // whether node's parent is the right child of node's grandparent

                    if (((uncle.hasLeftChild()) && !uncle.hasRightChild()) || (!uncle.hasLeftChild() && uncle.hasRightChild())) {// Check if node's uncle has only one child
                        //rotation
                        if (parent.isLeftChild(node)) {
                            if (uncle.hasLeftChild()) {
                                rotateRight(parent);
                                rotateLeft(grandParent);
                                rotateRight(grandParent);
                            } else if (uncle.hasRightChild()) {
                                rotateRight(parent);
                                rotateLeft(uncle);
                                rotateLeft(grandParent);
                                rotateRight(grandParent);
                            }
                        } else {
                            if (uncle.hasLeftChild()) {
                                rotateLeft(grandParent);
                                rotateRight(grandParent);
                            } else if (uncle.hasRightChild()) {
                                rotateLeft(uncle);
                                rotateLeft(grandParent);
                                rotateRight(grandParent);
                            }
                        }
                    }
                }
            }
        }

    }






