
package edu.emory.cs.sort.hybrid;
import java.util.Arrays;
import edu.emory.cs.sort.divide_conquer.MergeSort;
import java.lang.reflect.Array;

public class HybridSortHW<T extends Comparable<T>> implements HybridSort<T>  {
    @Override
    public T[] sort(T[][] input) {
        MergeSort<T> mg = new MergeSort<>();

        for (int i = 0; i < input.length; i++) {
            T[] curr = input[i];
            int index = 0;
            for (int j = 0; j < curr.length - 1; j++) {
                if (curr[j].compareTo(curr[j + 1]) <= 0) {
                    index++;
                } else {
                    break;
                }
            }

            if (index == curr.length - 1) { // all ascending
            } else {  //descending
                for (int j = curr.length - 2; j > 0; j--) {
                    if (curr[j].compareTo(curr[j + 1]) <= 0) {
                        index++;
                    } else {
                        break;
                    }
                }
                if (index == curr.length - 1) { //all descending, reverse
                    for(int k = 0; k < input[i].length / 2; k++) {
                        int indexChange = input[i].length - 1 - k;
                        swap(input, k, indexChange, i);
                    }
                } else {
                    //random, mostly descending, mostly ascending
                    mg.sort(input[i]);
                }
            }
        }
        return recursion(input);
    }


    public T[] recursion(T[][] input) {
        if (input.length == 1) {
            return input[0];
        }
        T[][] firstHalf = Arrays.copyOfRange(input, 0, input.length / 2);
        T[][] secondHalf = Arrays.copyOfRange(input, input.length / 2, input.length);
        return merge(recursion(firstHalf), recursion(secondHalf));
    }

    private T[] merge(T[] array1, T[] array2) {
        int index1 = 0, index2 = 0, i = 0;
        T[] result = (T[])Array.newInstance(array1[0].getClass(), array1.length + array2.length);
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1].compareTo(array2[index2]) < 0) {
                result[i] = array1[index1];
                index1++;
                i++;
            }
            else {
                result[i] = array2[index2];
                index2++;
                i++;
            }
        }
        while (index1 < array1.length){
            result[i] = array1[index1];
            i++;
            index1++;
        }while (index2 < array2.length) {
            result[i] =array2[index2];
            i++;
            index2++;
        }
        return result;
    }


    private void swap(T[][] array, int k, int indexChange, int i) {
        T t = array[i][k];
        array[i][k] = array[i][indexChange];
        array[i][indexChange] = t;
    }


}