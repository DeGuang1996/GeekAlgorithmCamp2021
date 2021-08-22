package week09.homework;

import java.util.PriorityQueue;

public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((term1, term2) -> term2[0] - term1[0]);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
            if (i >= k - 1) {
                while (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= i - k) {
                    priorityQueue.poll();
                }
                res[i - k + 1] = priorityQueue.peek()[0];
            }
        }
        return res;
    }
}
