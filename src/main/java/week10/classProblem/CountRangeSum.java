package week10.classProblem;

import java.util.HashMap;
import java.util.TreeSet;

public class CountRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        TreeSet<Long> treeSet = new TreeSet<>();
        for (long sum : preSum) {
            treeSet.add(sum);
            treeSet.add(sum - lower);
            treeSet.add(sum - upper);
        }
        int[] treeArray = new int[treeSet.size() + 1];
        int idx = 1;
        HashMap<Long, Integer> hashMap = new HashMap<>();
        for (long num : treeSet) {
            hashMap.put(num, idx++);
        }

        int ans = 0;
        for (int i = 0; i < preSum.length; i++) {
            int left = hashMap.get(preSum[i] - upper) - 1;
            int right = hashMap.get(preSum[i] - lower);
            ans += get(treeArray, right) - get(treeArray, left);
            left = hashMap.get(preSum[i]);
            add(treeArray, left);
        }
        return ans;
    }

    private void add(int[] treeArray, int pos) {
        while (pos < treeArray.length) {
            treeArray[pos]++;
            pos += lowBit(pos);
        }
    }

    private int get(int[] treeArray, int pos) {
        int res = 0;
        while (pos > 0) {
            res += treeArray[pos];
            pos -= lowBit(pos);
        }
        return res;
    }

    private int lowBit(int x) {
        return x & (-x);
    }
}
