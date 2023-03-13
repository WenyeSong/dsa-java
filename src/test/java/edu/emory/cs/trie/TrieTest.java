package edu.emory.cs.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class TrieTest {
    @Test
    public void testTrieNode() {
        TrieNode<Integer> n = new TrieNode<>(null, 'r');
        TrieNode<Integer> a  = n.addChild('a');
        TrieNode<Integer> b = n.removeChild('a');
        assertEquals(a, b);
    }

    @Test
    public void testTrie() {
        final String[] vocab = {"she", "shell", "sell", "selling", "shore", "woman", "women", "won"};
        Trie<Integer> trie = new Trie<>();
        int i, len = vocab.length;

        for (i = 0; i < len; i++) {
            Integer t = trie.put(vocab[i], i);
            assertNull(t);
        }

        for (i = 0; i < len; i++)
            assertEquals(i, trie.get(vocab[i]).intValue());

        assertNull(trie.get("wom"));
        assertNull(trie.get("wons"));

        assertFalse(trie.remove("wom"));
        assertEquals(5, trie.get("woman").intValue());

        assertFalse(trie.remove("wons"));
        assertEquals(7, trie.get("won").intValue());

        assertTrue(trie.remove("won"));
        assertEquals(5, trie.get("woman").intValue());

        trie.remove("selling");
        assertNull(trie.get("selling"));
        assertEquals(2, trie.get("sell").intValue());

        trie.remove("she");
        assertNull(trie.get("she"));
        assertEquals(1, trie.get("shell").intValue());
        assertEquals(4, trie.get("shore").intValue());
//        System.out.println("sell".compareTo("selling"));
    }
}