package edu.emory.cs.trie;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class TrieQuiz extends Trie<Integer> {

    /**
     * PRE: this trie contains all country names as keys and their unique IDs as values
     * (e.g., this.get("United States") -> 0, this.get("South Korea") -> 1).
     * @param input the input string in plain text
     *              (e.g., "I was born in South Korea and raised in the United States").
     * @return the list of entities (e.g., [Entity(14, 25, 1), Entity(44, 57, 0)]).
     */
    List<Entity> getEntities(String input) {
        // TODO: to be updated
        Map<String,Integer> countryMap = new HashMap<>();
        this.makeCountryMap("", this.getRoot(), countryMap);

        Set<String> keySet = countryMap.keySet();
        List<Entity> answer = new ArrayList<>();

        int beginIndex;
        int endIndex;

        for(String key : keySet ){
            if(input.contains(key)){
                beginIndex = input.indexOf(key);
                endIndex = beginIndex + key.length();
                answer.add(new Entity(beginIndex, endIndex, countryMap.get(key)));
            }
        }
        return answer;
    }

    public void makeCountryMap(String country, TrieNode<Integer> root, Map<String,Integer> ans) {
        if (root.getValue() != null) {
            country = country + root.getKey();
            ans.put(country, root.getValue());
            return;
        }
        Map<Character, TrieNode<Integer>> children = root.getChildrenMap();
        if (root.getKey() != 0) {
            country = country + root.getKey();
        }
        Set<Character> keySet = children.keySet();
        for (Character one : keySet) {
            TrieNode<Integer> child = children.get(one);
            makeCountryMap(country, child, ans);
        }
    }

  /*  public static void main(String[] args) {
        final List<String> L = List.of("United States", "South Korea", "China", "Chile", "Canada");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++) {
            trie.put(L.get(i), i);
        }
        Map<String,Integer> ans = new HashMap<>();
            trie.makeCountryMap("", trie.getRoot(), ans);
            System.out.println(ans);

    }*/

}