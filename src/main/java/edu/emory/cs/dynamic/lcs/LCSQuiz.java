package edu.emory.cs.dynamic.lcs;
import java.util.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class LCSQuiz extends LCSDynamic {
    /**
     * @param a the first string.
     * @param b the second string.
     * @return a set of all longest common sequences between the two strings.
     */
    public Set<String> solveAll(String a, String b) {
            // TODO: to be filled
            Set<String> results = new HashSet<>();
            String LCSSample = solve(a, b);
            int largestLength = LCSSample.length();
//        System.out.println(res+len);
//        solveAll(a.toCharArray(), b.toCharArray(), len, "", results);
//        System.out.println(results);
            List<String> lcs = lcs(a, b);
            System.out.println(lcs);
            for (String tmp : lcs) {
                if (tmp.length() == largestLength) {
                    results.add(tmp);
                }
            }
            return results;
        }
        public static List<String> lcs (String s1, String s2){
            int len1 = s1.length(), len2 = s2.length();
            if (len1 == 0 || len2 == 0) {
                return Collections.singletonList(""); // return an empty list
            }
            if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
                List<String> lcsList = lcs(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1));
                List<String> result = new ArrayList<>();
                for (String s : lcsList) {
                    result.add(s + s1.charAt(len1 - 1));
                }
                return result;
            } else {
                List<String> lcs1 = lcs(s1, s2.substring(0, len2 - 1));
                List<String> lcs2 = lcs(s1.substring(0, len1 - 1), s2);
                if (lcs1.isEmpty() && lcs2.isEmpty()) {
                    return Collections.emptyList();
                } else if (lcs1.isEmpty()) {
                    return lcs2;
                } else if (lcs2.isEmpty()) {
                    return lcs1;
                } else {
                    List<String> result = new ArrayList<>(lcs1);
                    result.addAll(lcs2);
                    return result;
                }
            }
        }
        public static void main (String[]args){
            String a = "ACGTCGTGT";
            String b = "CTAGTGGAG";
            LCSQuiz d = new LCSQuiz();
            System.out.println(d.solveAll(a, b));
        }
    }

