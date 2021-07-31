package week07;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSubarraySumCircular {

    public int maxSubarraySumCircular(int[] nums) {
        // int[] preSum = new int[nums.length + 1];
        // // 求前缀
        // for (int i = 0; i < nums.length; i++) {
        //     preSum[i + 1] = preSum[i] + nums[i];
        // }
        // // 求无环最大子段和
        // int ansNoCycle = Integer.MIN_VALUE;
        // int minSum = 0;
        // for (int i = 0; i < nums.length; i++) {
        //     ansNoCycle = Math.max(ansNoCycle, preSum[i + 1] - minSum);
        //     minSum = Math.min(minSum, preSum[i + 1]);
        // }
        //
        // // 求有环最小子段和
        // int minCycleSum = 0;
        // int pre = preSum[0];
        // for (int i = 1; i < nums.length - 1; i++) {
        //     minCycleSum = Math.min(minCycleSum, preSum[i] - pre);
        //     pre = Math.max(pre, preSum[i]);
        // }
        // return Math.max(ansNoCycle, preSum[nums.length] - minCycleSum);

        // int ansNoCycle = nums[0];
        // int preSum = nums[0];
        // int totalSum = nums[0];
        // for (int i = 1; i < nums.length; i++) {
        //     preSum = Math.max(0, preSum) + nums[i];
        //     ansNoCycle = Math.max(ansNoCycle, preSum);
        //     totalSum += nums[i];
        // }
        //
        // int minCycleSum = 0;
        // preSum = nums[0];
        // for (int i = 1; i < nums.length - 1; i++) {
        //     preSum = Math.min(0, preSum) + nums[i];
        //     minCycleSum = Math.min(minCycleSum, preSum);
        // }
        // return Math.max(ansNoCycle, totalSum - minCycleSum);

        int[] preSum = new int[nums.length * 2 + 1];
        for (int i = 0; i < nums.length * 2; i++) {
            preSum[i + 1] = nums[i % nums.length] + preSum[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = Integer.MIN_VALUE;

        for (int i = 1; i < preSum.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - nums.length) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                ans = Math.max(ans, preSum[i] - preSum[deque.peekFirst()]);
            }
            while (!deque.isEmpty() && preSum[deque.peekLast()] > preSum[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        return ans;
    }
}
