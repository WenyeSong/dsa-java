package edu.emory.cs.sort.distribution;

import java.util.Arrays;
import java.util.Deque;

public class RadixSortQuiz extends RadixSort {
    @Override
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);// find the max bit
        mySort(array, beginIndex, endIndex, maxBit);
    }

    public void mySort(Integer[] array, int indexNotNeedToSort, int endIndex, int bit) {
        if (endIndex <= indexNotNeedToSort || bit <= 0) {
            return;
        }
        int[] eachBucketNum = new int[buckets.size()]; // buckets.size() = 10

        for (int i = indexNotNeedToSort; i < endIndex; i++) {
            int digit = (array[i] / (int) Math.pow(10, bit - 1)) % 10; // digit on each bit
            eachBucketNum[digit]++; // how many number in this bucket of digit
        }

        sort(array, indexNotNeedToSort, endIndex, key -> (key / (int) Math.pow(10, bit - 1)) % 10); // bucket sort

        for (int digit = 0; digit < buckets.size(); digit++) {

            if (eachBucketNum[digit] == 1)
                indexNotNeedToSort++;
            else if(eachBucketNum[digit] != 1 && eachBucketNum[digit] != 0){
                mySort(array, indexNotNeedToSort, eachBucketNum[digit] + indexNotNeedToSort, bit - 1);
                indexNotNeedToSort += eachBucketNum[digit];

            }
        }
    }

        public static void main (String[]args)
        {
            Integer arr[] = {113,145,367,79866,66,23,66,8}; //move out numbers from the bottom
            int n = arr.length;
            RadixSortQuiz test = new RadixSortQuiz();
            test.sort(arr, 0, arr.length);
            for (int i = 0; i < n; i++)
                System.out.print(arr[i] + " ");
        }
    }
