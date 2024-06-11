package week01;

public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = m - 1, idx2 = n - 1, total = m + n - 1;
        while (idx1 >= 0 && idx2 >= 0) {
            if (nums1[idx1] < nums2[idx2]) {
                nums1[total--] = nums2[idx2--];
            } else {
                nums1[total--] = nums1[idx1--];
            }
        }
        while (idx1 >= 0) {
            nums1[total--] = nums1[idx1--];
        }
        while (idx2 >= 0) {
            nums1[total--] = nums2[idx2--];
        }
    }
}
