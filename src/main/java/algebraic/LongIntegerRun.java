package algebraic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** @author Jinho D. Choi */
public class LongIntegerRun {
    static public void main(String[] args) {
        System.out.println(Arrays.toString(args));

        LongInteger a = new LongInteger(args[0]);
        LongInteger b = new LongInteger(args[1]);
        System.out.println(a);
        System.out.println(b);

        List<LongInteger> list = new ArrayList<>();
        list.add(new LongInteger("78"));
        list.add(new LongInteger("-45"));
        list.add(new LongInteger("0"));
        list.add(new LongInteger("6"));
        list.add(new LongInteger("-0"));
        list.add(new LongInteger("-123"));

        list.sort(Comparator.naturalOrder());
        System.out.println(list);

        list.sort(Comparator.reverseOrder());
        System.out.println(list);
    }
}
