package datastructures.sortingalgorithms;

import java.util.Arrays;
import java.util.LinkedList;

public class MergeSort {

    private int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, index = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j])
                result[index++] = arr1[i++];
            else
                result[index++] = arr2[j++];
        }

        while (i < arr1.length) result[index++] = arr1[i++];
        while (j < arr2.length) result[index++] = arr2[j++];

        return result;
    }

    public int[] mergeSort(int[] arr) {
        // Base Case:
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

}
