package datastructures.sortingalgorithms;

public class BasicSorts {

    public void bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }


    public void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            // Make the sort based on the indexes of the minimum value;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex])
                    minIndex = j;
            }

            // Confirms if there is a smaller value, in another index, if it does, will changed it.
            if (i != minIndex) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }

    public void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
    }


}
