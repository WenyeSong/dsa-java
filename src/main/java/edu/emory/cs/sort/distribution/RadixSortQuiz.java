package edu.emory.cs.sort.distribution;

import java.util.Arrays;
import java.util.Deque;

public class RadixSortQuiz extends RadixSort {
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        // TODO: to be filled
        int maxBit = getMaxBit(array, beginIndex, endIndex);// find the max bit
        mySort(array, beginIndex, endIndex, maxBit);
    }

    public void mySort(Integer[] array, int beginIndex, int endIndex, int bit) {
        if (endIndex <= beginIndex || bit <= 0) {
            return;
        }
        int[] eachBucketNum = new int[buckets.size()];

        for (int i = beginIndex; i < endIndex; i++) {
            int digit = (array[i] / (int) Math.pow(10, bit - 1)) % 10; // digit on each bit
            eachBucketNum[digit]++; // how many number in this bucket of digit
        }

        sort(array, beginIndex, endIndex, key -> (key / (int) Math.pow(10, bit - 1)) % 10); // bucket sort

        int bucketNotNeedToSort = 0;
        for (int digit = 0; digit < buckets.size(); digit++) {

            if (eachBucketNum[digit] == 1)
                bucketNotNeedToSort++;
            else if(eachBucketNum[digit] != 1 && eachBucketNum[digit] != 0){
                mySort(array, bucketNotNeedToSort, eachBucketNum[digit], bit - 1);
            }
        }
    }

        public static void main (String[]args)
        {
            Integer arr[] = {802, 883, 789, 14, 66, 2, 66, 100, 543, 289, 1000, 345, 145690}; //最底下的先出来
            int n = arr.length;
            RadixSortQuiz test = new RadixSortQuiz();
            test.sort(arr, 0, arr.length);
            for (int i = 0; i < n; i++)
                System.out.print(arr[i] + " ");
        }
    }
