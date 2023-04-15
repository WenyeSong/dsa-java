
package edu.emory.cs.trie.autocomplete;

import edu.emory.cs.trie.TrieNode;
import java.lang.*;
import java.util.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }
    public TrieNode<List<String>> getNode(String prefix) {

        return find(prefix);

    }
    @Override
    public List<String> getCandidates(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            return getInitialLetters();
        } else {
            prefix = prefix.trim();
        }
        TrieNode<List<String>> node = getNode(prefix);

        Queue<String> queue = new PriorityQueue<>(Comparator.comparing(String::length).thenComparing(String::compareTo));
        if (node != null) {
            dfs(node, prefix, queue);
        }

        List<String> candidates = new ArrayList<>();
        TrieNode<List<String>> temp = find(prefix);
        List<String> memory;
        if (temp!=null && (memory = temp.getValue()) != null) {
            candidates.addAll(memory);
        }

        while (!queue.isEmpty() && candidates.size() < getMax()) {
            String candidate = queue.poll();
            if (!candidates.contains(candidate)) {
                candidates.add(candidate);
            }
        }
        return candidates;
    }

    private List<String> getInitialLetters() {
        Set<String> initialLetters = new HashSet<>();
        for (TrieNode<List<String>> child : getRoot().getChildrenMap().values()) {
            initialLetters.add(String.valueOf(child.getKey()));
        }
        List<String> initialLettersList = new ArrayList<>(initialLetters);
        Collections.sort(initialLettersList);
        return initialLettersList;
    }


    private void dfs(TrieNode<List<String>> node, String prefix, Queue<String> queue) {
        if (node.isEndState()) {
            queue.add(prefix);
        }
        for (Character key : node.getChildrenMap().keySet()) {
            TrieNode<List<String>> child = node.getChild(key);
            dfs(child, prefix + key, queue);
        }
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        if (candidate == null) return;
        prefix = prefix.trim().toLowerCase();
        TrieNode<List<String>> node = find(prefix);
        if (node == null) {
            put(prefix, new ArrayList<>());
            node = find(prefix);
            node.setEndState(false);
        }
        List<String> memory = node.getValue();
        if (memory == null) {
            memory = new ArrayList<>();
            node.setValue(memory);
        }
        memory.remove(candidate);
        memory.add(0, candidate);
        node = find(candidate);
        List<String> curValue = node == null ? null : node.getValue();
        put(candidate, curValue);
    }
}