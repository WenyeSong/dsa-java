package edu.emory.cs.trie.autocomplete;
import edu.emory.cs.trie.TrieNode;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
        generateList(this.getRoot());
    }

    @Override
    public List<String> getCandidates(String prefix) {
        // TODO: to be updated
            TrieNode<List<String>> root = this.getRoot();
            Map<Character, TrieNode<List<String>>> childrenMap;
            for (int i = 0; i < prefix.length(); i++) {
                childrenMap = root.getChildrenMap();
                root = childrenMap.get(prefix.charAt(i));
            } // find the last letter of prefix

            List<String> values = root.getValue();
            List<String> ans = new ArrayList<>();

            for (String item : values) {
                ans.add(prefix.substring(0, prefix.length() - 1) + item);
            }
            System.out.println(ans);

            return ans;
        }

    public void generateList(TrieNode<List<String>> root) {
        Map<Character, TrieNode<List<String>>> childrenMap = root.getChildrenMap();
        List<String> valueList = new ArrayList<>();
        for (Character key : childrenMap.keySet()) {
            TrieNode<List<String>> child = root.getChild(key);
            generateList(child);
            for (String s : child.getValue()) {
                valueList.add(root.getKey() + s);
            }
        }
        if (childrenMap.isEmpty() || root.isEndState()){
            valueList.add(String.valueOf(root.getKey()));
        }
        Collections.sort(valueList, new StringCompartor());
        root.setValue(valueList);
    }

    private static class StringCompartor implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }  else {
                return o1.compareTo(o2);
            }
        }
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        // TODO: to be updated
        TrieNode<List<String>> root = this.getRoot();
        Map<Character, TrieNode<List<String>>> childrenMap;
        for (int i = 0; i < prefix.length(); i++) {
            childrenMap = root.getChildrenMap();
            root = childrenMap.get(prefix.charAt(i));
        }
        List<String> values = root.getValue();
        String temp = candidate.substring(1);
        values.remove(temp);
        values.add(0, temp);
    }


}
