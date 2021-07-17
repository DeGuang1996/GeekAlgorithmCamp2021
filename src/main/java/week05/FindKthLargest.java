package week05;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        // Arrays.sort(nums);
        // return nums[nums.length - k];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int num : nums) {
            priorityQueue.offer(num);
            while (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        FindKthLargest findKthLargest = new FindKthLargest();
        findKthLargest.findKthLargest(nums, 2);
    }
}
