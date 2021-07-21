package week05;

public class CountRangeSum {

    private int ans = 0;
    private int lower;
    private int upper;

    // 归并排序解法
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        this.lower = lower;
        this.upper = upper;
        mergeSort(preSum, 0, preSum.length - 1);
        return ans;
    }

    private void mergeSort(long[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        calculate(nums, left, mid, right);
        merge(nums, left, mid, right);
    }

    private void calculate(long[] nums, int left, int mid, int right) {
        int i = left;
        int begin = mid + 1;
        int end = mid + 1;
        while (i <= mid) {
            while (begin <= right && nums[begin] - nums[i] < lower) {
                begin++;
            }
            while (end <= right && nums[end] - nums[i] <= upper) {
                end++;
            }
            ans += end - begin;
            i++;
        }
    }

    private void merge(long[] nums, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
        int i = left, j = mid + 1;
        for (int k = 0; k < temp.length; k++) {
            if (j > right || (i <= mid && nums[i] <= nums[j])) {
                temp[k] = nums[i++];
            } else {
                temp[k] = nums[j++];
            }
        }
        for (int k = 0; k < temp.length; k++) {
            nums[k + left] = temp[k];
        }
    }

    public static void main(String[] args) {
        CountRangeSum countRangeSum = new CountRangeSum();
        int[] nums = {-2, 5, -1};
        countRangeSum.countRangeSum(nums, -2, 2);
    }

    // 树状数组解法
    // public int countRangeSum(int[] nums, int lower, int upper) {
    //     long[] preSum = new long[nums.length + 1];
    //     for (int i = 0; i < nums.length; i++) {
    //         preSum[i + 1] = preSum[i] + nums[i];
    //     }
    //
    //     TreeSet<Long> treeSet = new TreeSet<>();
    //     for (long sum : preSum) {
    //         treeSet.add(sum);
    //         treeSet.add(sum - lower);
    //         treeSet.add(sum - upper);
    //     }
    //     int[] treeArray = new int[treeSet.size() + 1];
    //     int idx = 1;
    //     HashMap<Long, Integer> hashMap = new HashMap<>();
    //     for (long num : treeSet) {
    //         hashMap.put(num, idx++);
    //     }
    //
    //     int ans = 0;
    //     for (int i = 0; i < preSum.length; i++) {
    //         int left = hashMap.get(preSum[i] - upper) - 1;
    //         int right = hashMap.get(preSum[i] - lower);
    //         ans += get(treeArray, right) - get(treeArray, left);
    //         left = hashMap.get(preSum[i]);
    //         add(treeArray, left);
    //     }
    //     return ans;
    // }

    // private void add(int[] treeArray, int pos) {
    //     while (pos < treeArray.length) {
    //         treeArray[pos]++;
    //         pos += lowBit(pos);
    //     }
    // }
    //
    // private int get(int[] treeArray, int pos) {
    //     int res = 0;
    //     while (pos > 0) {
    //         res += treeArray[pos];
    //         pos -= lowBit(pos);
    //     }
    //     return res;
    // }
    //
    // private int lowBit(int x) {
    //     return x & (-x);
    // }
}
