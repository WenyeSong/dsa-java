package edu.emory.cs.trie.autocomplete;

import edu.emory.cs.trie.TrieNode;
import java.lang.*;
import java.util.*;

public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }
    @Override
    public List<String> getCandidates(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            return getInitialLetters();
        } else {
            prefix = prefix.trim();
        }
        TrieNode<List<String>> nodeOfPrefix = find(prefix);

        Queue<String> queue = new PriorityQueue<>(Comparator.comparing(String::length).thenComparing(String::compareTo));

        if (nodeOfPrefix != null) {
            dfs(nodeOfPrefix, prefix, queue); // put node of prefix into the sorted queue
        }

        List<String> candidates = new ArrayList<>();
        List<String> valueOfNode;

        if (nodeOfPrefix != null){  // check whether prefix has node
            valueOfNode = nodeOfPrefix.getValue();
            if(valueOfNode  != null) { //check whether prefix has value
                candidates.addAll(valueOfNode);
            }
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
            if(initialLetters.size() == getMax()){break;}
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
        prefix = prefix.trim().toLowerCase();
        TrieNode<List<String>> nodeOfPrefix = find(prefix);
        if (candidate == null) {
            return;
        }

        if (nodeOfPrefix == null) {
            put(prefix, new ArrayList<>());
            nodeOfPrefix = find(prefix);
            nodeOfPrefix.setEndState(false);
        }

        List<String> valueOfPrefix = nodeOfPrefix.getValue();
        if (valueOfPrefix == null) {
            valueOfPrefix = new ArrayList<>();
            nodeOfPrefix.setValue(valueOfPrefix);
        }
        valueOfPrefix.remove(candidate);
        valueOfPrefix.add(0, candidate);
        nodeOfPrefix = find(candidate);
        List<String> Value = nodeOfPrefix == null ? null : nodeOfPrefix.getValue();
        put(candidate, Value);
    }
}
