package edu.emory.cs.algebraic;

import java.util.Arrays;

public class LongIntegerQuiz extends LongInteger {
    public LongIntegerQuiz(LongInteger n) { super(n); }

    public LongIntegerQuiz(String n) { super(n); }

    @Override
    public void addDifferentSign(LongInteger n) {
        int m = Math.max(digits.length, n.digits.length);
        byte[] larger = new byte[m];
        byte[] smaller = new byte[m];

        if (compareAbs(n) < 0) {
            System.arraycopy(digits, 0, smaller, 0, digits.length);
            System.arraycopy(n.digits, 0, larger, 0, n.digits.length);
        } else {
            System.arraycopy(n.digits, 0, smaller, 0, n.digits.length);
            System.arraycopy(digits, 0, larger, 0, digits.length);
        }

        byte[] result = new byte[m];

        System.arraycopy(larger, 0, result, 0, larger.length);

        for (int i = 0; i < larger.length; i++) {
            result[i] -= smaller[i];
            if (result[i] < 0) {
                result[i] += 10;
                result[i + 1] -= 1;
            }
        }
        if (compareAbs(n) < 0) {
            sign = n.sign;
        }
        int index = m - 1;
        for (; index >= 0; index--) {
            if (result[index] != 0)
                break;
        }
        if (index < 0) {
            digits = Arrays.copyOf(result, 1);
        } else {
            digits = Arrays.copyOf(result, index + 1);
        }
    }
}




