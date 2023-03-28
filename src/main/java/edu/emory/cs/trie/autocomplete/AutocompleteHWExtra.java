package edu.emory.cs.trie.autocomplete;
import edu.emory.cs.trie.TrieNode;

import java.util.*;
import java.util.List;
import java.util.Map;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AutocompleteHWExtra extends Autocomplete<List<AutocompleteHWExtra.Reply>> {
    public AutocompleteHWExtra(String dict_file, int max) {
        super(dict_file, max);
        generateList(this.getRoot());
    }

    @Override
    public List<String> getCandidates(String prefix) {
        // TODO: to be updated
        TrieNode<List<Reply>> root = this.getRoot();
        root = findLastLetter(root, prefix);

        List<Reply> values = root.getValue();
        List<String> ans = new ArrayList<>();
        Collections.sort(values, new StringCompartor());

        for (Reply item : values) {
            String temp = prefix.substring(0, prefix.length() - 1) + item.word;
            ans.add(temp);
        }
        return ans.subList(0,getMax());
    }

    public void generateList(TrieNode<List<Reply>> root) {
        Map<Character, TrieNode<List<Reply>>> childrenMap = root.getChildrenMap();
        List<Reply> valueList = new ArrayList<>();
        for (Character key : childrenMap.keySet()) {
            TrieNode<List<Reply>> child = root.getChild(key);
            generateList(child);
            for (Reply s : child.getValue()) {
                valueList.add(new Reply(0, root.getKey() + s.word));
            }
        }
        if (childrenMap.isEmpty() || root.isEndState()){
            valueList.add(new Reply(0, String.valueOf(root.getKey())));
        }
        Collections.sort(valueList, new StringCompartor());
        root.setValue(valueList);
    }

    private static class StringCompartor implements Comparator<Reply> {
        public int compare(Reply o1, Reply o2) {
            if (o1.freq != o2.freq) {
                return o2.freq - o1.freq;
            } else {
                if (o1.word.length() != o2.word.length()) {
                    return o1.word.length() - o2.word.length();
                }  else {
                    return o1.word.compareTo(o2.word);
                }
            }
        }
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        // TODO: to be updated
        TrieNode<List<Reply>> root = this.getRoot();
        root = findLastLetter(root, prefix);
        List<Reply> values = root.getValue();
        String temp = candidate.substring(1);
        for (Reply r : values) {
            if (r.word.equals(temp)) {
                r.freq++;
            }
        }
    }
    public TrieNode<List<Reply>> findLastLetter(TrieNode<List<Reply>> root, String prefix) {
        Map<Character, TrieNode<List<Reply>>> childrenMap;
        for (int i = 0; i < prefix.length(); i++) {
            childrenMap = root.getChildrenMap();
            root = childrenMap.get(prefix.charAt(i));
        }
        return root;
    }
    public class Reply{
        int freq;
        String word;
        Reply(int freq, String word) {
            this.freq = freq;
            this.word = word;
        }
    }
/*    public static void main(String[] args) {
        final String dict_file = "src/main/resources/dict.txt";
        final int max = 15;

        Autocomplete<?> ac = new AutocompleteHWExtra(dict_file, max);

        System.out.println(ac.getCandidates("ph"));
        ac.pickCandidate("ph", "pho");
        ac.pickCandidate("ph", "pho");
        ac.pickCandidate("ph", "pho");
        ac.pickCandidate("ph", "pho");
        ac.pickCandidate("ph", "pho");
        System.out.println(ac.getCandidates("ph"));

        ac.pickCandidate("ph", "phi");
        ac.pickCandidate("ph", "phi");
        System.out.println(ac.getCandidates("ph"));

        ac.pickCandidate("ph", "phr");
        System.out.println(ac.getCandidates("ph"));

    }*/
}