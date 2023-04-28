package edu.emory.cs.dynamic.hanoi;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class HanoiRecursive extends Hanoi {
    public int count;
    @Override
    public List<String> solve(int n, char source, char intermediate, char destination) {
        List<String> list = new ArrayList<>();
        solve(list, n, source, intermediate, destination);
        return list;
    }

    private void solve(List<String> list, int n, char source, char intermediate, char destination) {
        this.count += 1;
        if (n == 0) return;

        //Move all plates from 'source' to 'intermediate' via 'destination' as medium
        solve(list, n - 1, source, destination, intermediate);

        //Record the step
        list.add(getKey(n, source, destination));

        //Move all plates from 'intermediate' to 'destination' via 'source' as medium
        solve(list, n - 1, intermediate, source, destination);
    }
    public static void main(String[] args) {
        final char source = 'S';
        final char intermediate = 'I';
        final char destination = 'D';


        for (int k = 1; k < 11; k++) {
            HanoiRecursive recursive = new HanoiRecursive();
            recursive.solve(k, source, intermediate, destination);
            System.out.println(k + "\t" + recursive.count);
        }
//            System.out.println(result);
//    assertEquals(recursive.solve(k, source, intermediate, destination), dynamic.solve(k, source, intermediate, destination));

    }
    }