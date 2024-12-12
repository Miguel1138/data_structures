package datastructures.heap;

import java.util.ArrayList;
import java.util.List;

// Adding and removing with this structure is O(log n) in priority queues
public class mHeap {

    private List<Integer> heap;

    public mHeap() {
        this.heap = new ArrayList<Integer>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;
        while (current > 0 && heap.get(current) > heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Integer remove() {
        if (heap.size() == 0) {
            return null;
        }

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        // Vai pegar o valor mais alto, e removê-lo pelo último item da heap.
        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));

        // Método que irá "descer" o valor até seu local correto conforme a estrutura heap
        sinkDown(0);

        return maxValue;
    }

    private void sinkDown(int index) {
        int maxIndex = index;
        while (true) {
            int leftChild = leftChild(maxIndex);
            int rightChild = rightChild(maxIndex);
            if (leftChild < heap.size()
                    && heap.get(leftChild) > heap.get(maxIndex)) {
                maxIndex = leftChild;
            }
            if (rightChild < heap.size()
                    && heap.get(rightChild) > heap.get(maxIndex)) {
                maxIndex = rightChild;
            }
            if (maxIndex != index) {
                swap(index, maxIndex);
            } else {
                return;
            }
        }
    }

    /* LEETCODE EXERCISE
        Kth Smallest Element in an Array
       */

    public int findKthSmallest(int[] nums, int k) {
        mHeap maxHeap = new mHeap();
        for (int num : nums) {
            maxHeap.insert(num);
        }
        // Ensures that the heap always has k elements in it.
        while (maxHeap.getHeap().size() > k) {
            maxHeap.remove();
        }
        // Return the smallest  k element.
        return maxHeap.remove();
    }

    public List<Integer> streamMax(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        mHeap maxHeap = new mHeap();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            maxHeap.insert(num);
            list.add(maxHeap.getHeap().get(0));
        }

        return list;
    }

}
