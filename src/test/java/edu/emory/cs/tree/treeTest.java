package edu.emory.cs.tree;

import edu.emory.cs.tree.balanced.BalancedBinarySearchTreeQuiz;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class treeTest {

    @Test
    public void testQuiz1() {

        BalancedBinarySearchTreeQuiz<Integer> tree = new BalancedBinarySearchTreeQuiz<>();
        tree.add(3);
        tree.add(2);
        tree.add(5);
        tree.add(1);
        tree.add(4);

        int root = tree.getRoot().getKey();
        int left = tree.getRoot().getLeftChild().getKey();
        int right = tree.getRoot().getRightChild().getKey();
        int leftleft = tree.getRoot().getLeftChild().getLeftChild().getKey();
        int leftright = tree.getRoot().getLeftChild().getRightChild().getKey();


        assertEquals(4, root);
        assertEquals(2, left);
        assertEquals(5, right);
        assertEquals(1, leftleft);
        assertEquals(3, leftright);


    }

    @Test
    public void testQuiz2() {

        BalancedBinarySearchTreeQuiz<Integer> tree = new BalancedBinarySearchTreeQuiz<>();
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(2);
        tree.add(5);

        int root = tree.getRoot().getKey();
        int left = tree.getRoot().getLeftChild().getKey();
        int right = tree.getRoot().getRightChild().getKey();
        int leftleft = tree.getRoot().getLeftChild().getLeftChild().getKey();
        int leftright = tree.getRoot().getLeftChild().getRightChild().getKey();

        assertEquals(4, root);
        assertEquals(2, left);
        assertEquals(5, right);
        assertEquals(1, leftleft);
        assertEquals(3, leftright);

    }

    @Test
    public void testQuiz3() {

        BalancedBinarySearchTreeQuiz<Integer> tree = new BalancedBinarySearchTreeQuiz<>();
        tree.add(3);
        tree.add(1);
        tree.add(5);
        tree.add(2);
        tree.add(4);

        int root = tree.getRoot().getKey();
        int left = tree.getRoot().getLeftChild().getKey();
        int right = tree.getRoot().getRightChild().getKey();
        int leftleft = tree.getRoot().getLeftChild().getLeftChild().getKey();
        int leftright = tree.getRoot().getLeftChild().getRightChild().getKey();

        assertEquals(4, root);
        assertEquals(2, left);
        assertEquals(5, right);
        assertEquals(1, leftleft);
        assertEquals(3, leftright);

    }

    @Test
    public void testQuiz4() {

        BalancedBinarySearchTreeQuiz<Integer> tree = new BalancedBinarySearchTreeQuiz<>();
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.add(5);

        int root = tree.getRoot().getKey();
        int left = tree.getRoot().getLeftChild().getKey();
        int right = tree.getRoot().getRightChild().getKey();
        int leftleft = tree.getRoot().getLeftChild().getLeftChild().getKey();
        int leftright = tree.getRoot().getLeftChild().getRightChild().getKey();

        assertEquals(4, root);
        assertEquals(2, left);
        assertEquals(5, right);
        assertEquals(1, leftleft);
        assertEquals(3, leftright);

    }
}