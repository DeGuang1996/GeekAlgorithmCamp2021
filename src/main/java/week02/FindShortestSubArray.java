package week02;

import java.util.ArrayList;
import java.util.HashMap;

public class FindShortestSubArray {

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                ArrayList<Integer> list = hashMap.get(nums[i]);
                list.set(1, i);
                list.set(2, list.get(2) + 1);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(i);
                list.add(1);
                hashMap.put(nums[i], list);
            }
        }
        int maxCount = 0;
        int res = 0;
        for (int key : hashMap.keySet()) {
            ArrayList<Integer> list = hashMap.get(key);
            if (list.get(2) > maxCount || (list.get(2) == maxCount && list.get(1) - list.get(0) + 1 < res)) {
                maxCount = list.get(2);
                res = list.get(1) - list.get(0) + 1;
            }
        }
        return res;
    }
}
