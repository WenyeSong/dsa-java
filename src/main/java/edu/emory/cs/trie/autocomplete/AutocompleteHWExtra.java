package edu.emory.cs.trie.autocomplete;
import edu.emory.cs.trie.TrieNode;

import java.util.*;

public class AutocompleteHWExtra extends Autocomplete<List<AutocompleteHWExtra.Reply>> {

    public AutocompleteHWExtra(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            return getInitialLetters();
        } else {
            prefix = prefix.trim();
        }
        TrieNode<List<Reply>> nodeOfPrefix = find(prefix);

        PriorityQueue<Reply> queue = new PriorityQueue<>(new StringComparator());

        if (nodeOfPrefix != null) {
            dfs(nodeOfPrefix, prefix, queue);
        }

        List<String> candidates = new ArrayList<>();
        List<Reply> valueOfNode;

        if (nodeOfPrefix != null) {
            valueOfNode = nodeOfPrefix.getValue();
            if (valueOfNode != null) {
                for (Reply reply : valueOfNode) {
                    candidates.add(reply.word);
                }
            }
        }

        while (!queue.isEmpty() && candidates.size() < getMax()) {
            String candidate = queue.poll().word;
            if (!candidates.contains(candidate)) {
                candidates.add(candidate);
            }
        }
        return candidates;
    }

    private List<String> getInitialLetters() {
        Set<String> initialLetters = new HashSet<>();
        for (TrieNode<List<Reply>> child : getRoot().getChildrenMap().values()) {
            initialLetters.add(String.valueOf(child.getKey()));
            if (initialLetters.size() == getMax()) {
                break;
            }
        }
        List<String> initialLettersList = new ArrayList<>(initialLetters);
        Collections.sort(initialLettersList);
        return initialLettersList;
    }

    private void dfs(TrieNode<List<Reply>> node, String prefix, PriorityQueue<Reply> queue) {
        if (node.isEndState()) {
            queue.add(new Reply(0, prefix)); // replace 0 with actual frequency if needed
        }
        for (Character key : node.getChildrenMap().keySet()) {
            TrieNode<List<Reply>> child = node.getChild(key);
            dfs(child, prefix + key, queue);
        }
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        prefix = prefix.trim().toLowerCase();
        TrieNode<List<Reply>> nodeOfPrefix = find(prefix);
        if (candidate == null) {
            return;
        }

        if (nodeOfPrefix == null) {
            put(prefix, new ArrayList<>());
            nodeOfPrefix = find(prefix);
            nodeOfPrefix.setEndState(false);
        }
        List<Reply> valueOfPrefix = nodeOfPrefix.getValue();
        if (valueOfPrefix == null) {
            valueOfPrefix = new ArrayList<>();
            nodeOfPrefix.setValue(valueOfPrefix);
        }

        Reply candidateReply = null;
        for (Reply reply : valueOfPrefix) {
            if (reply.word.equals(candidate)) {
                candidateReply = reply;
                break;
            }
        }

        if (candidateReply != null) {
            candidateReply.updateTimestamp();
            candidateReply.freq++;
        } else {
            candidateReply = new Reply(1, candidate);
            candidateReply.updateTimestamp();
            valueOfPrefix.add(candidateReply);
        }

        nodeOfPrefix = find(candidate);
        List<Reply> Value = nodeOfPrefix == null ? null : nodeOfPrefix.getValue();
        put(candidate, Value);
    }

    private static class StringComparator implements Comparator<AutocompleteHWExtra.Reply> {
        public int compare(AutocompleteHWExtra.Reply o1, AutocompleteHWExtra.Reply o2) {
            if (o1.freq != o2.freq) {
                return o2.freq - o1.freq;
            } else {
                if (o1.timestamp != o2.timestamp) {
                    return (int) (o2.timestamp - o1.timestamp);
                } else {
                    if (o1.word.length() != o2.word.length()) {
                        return o1.word.length() - o2.word.length();
                    } else {
                        return o1.word.compareTo(o2.word);
                    }
                }
            }
        }
    }

    public class Reply {
        int freq;
        String word;
        long timestamp;

        Reply(int freq, String word) {
            this.freq = freq;
            this.word = word;
            this.timestamp = System.currentTimeMillis();
        }

        public void updateTimestamp() {
            this.timestamp = System.currentTimeMillis();
        }
    }
}

