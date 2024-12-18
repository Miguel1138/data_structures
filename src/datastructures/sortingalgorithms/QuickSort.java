package datastructures.sortingalgorithms;

public class QuickSort {

    private int pivot(int[] arr, int pivot, int endIndex) {
        int swapIndex = pivot;
        for (int i = pivot + 1; i <= endIndex; i++) {
            if (arr[i] < arr[pivot]) {
                swapIndex++;
                swap(arr, i, swapIndex);
            }
        }
        swap(arr, pivot, swapIndex);

        return swapIndex;
    }

    public void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private void quickSortHelper(int[] arr, int left, int right) {
        // Base Case
        if (left > right) return;

        int pivot = pivot(arr, left, right);
        quickSortHelper(arr, left, pivot - 1);
        quickSortHelper(arr, pivot + 1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
