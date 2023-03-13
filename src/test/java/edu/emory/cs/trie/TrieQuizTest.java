package edu.emory.cs.trie;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


/** @author Jinho D. Choi */
public class TrieQuizTest {

    @Test
    public void testGetEntities1() {
        final List<String> L = List.of("United States", "South Korea");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);

        String input = "I was born in South Korea and raised in the United States";
        List<Entity> entities = List.of(new Entity(44, 57, 0), new Entity(14, 25, 1));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet()); // trie.getEntities, trie 就是getEntities里的this
        assertEquals(expected, actual);
    }
    @Test
    public void testGetEntities2() {
        final List<String> L = List.of("China", "United States", "Korea", "Japan", "Italy");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);

        String input = "Learning in United States but born in China, wanting to live in Japan";
        List<Entity> entities = List.of(new Entity(12, 25, 1), new Entity(38, 43, 0), new Entity(64, 69, 3));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet()); // trie.getEntities, trie 就是getEntities里的this
        assertEquals(expected, actual);
    }
}

