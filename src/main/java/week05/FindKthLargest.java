package week05;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        // Arrays.sort(nums);
        // return nums[nums.length - k];
        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        // for (int num : nums) {
        //     priorityQueue.offer(num);
        //     while (priorityQueue.size() > k) {
        //         priorityQueue.poll();
        //     }
        // }
        // return priorityQueue.peek();

        return quickSort(nums, nums.length - k, 0, nums.length - 1);
    }

    private int quickSort(int[] nums, int k, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int pivot = partition(nums, l, r);
        if (pivot >= k) {
            return quickSort(nums, k, l, pivot);
        } else {
            return quickSort(nums, k, pivot + 1, r);
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = l + (new Random()).nextInt(r - l + 1);
        int pivotVal = nums[pivot];
        while (l <= r) {
            while (nums[l] < pivotVal) {
                l++;
            }
            while (nums[r] > pivotVal) {
                r--;
            }
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        FindKthLargest findKthLargest = new FindKthLargest();
        findKthLargest.findKthLargest(nums, 2);
    }
}
