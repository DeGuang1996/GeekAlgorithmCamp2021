package week01;

import java.util.HashMap;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int pre = 0;
        int res = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (hashMap.containsKey(pre - k)) {
                res += hashMap.get(pre - k);
            }
            hashMap.put(pre, hashMap.getOrDefault(pre, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,3,4,5,6,7,-5};
        SubarraySum subarraySum = new SubarraySum();
        subarraySum.subarraySum(nums, 2);
    }
}
