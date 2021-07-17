package week05;

public class MinDays {

    public int minDays(int[] bloomDay, int m, int k) {
        int left = 1, right = 1;
        for (int bloom : bloomDay) {
            right = Math.max(right, bloom + 1);
        }
        int max = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == max ? -1 : left;
    }

    private boolean isValid(int[] bloomDay, int m, int k, int curDay) {
        int consecutive = 0;
        int bouquet = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (curDay >= bloomDay[i]) {
                consecutive++;
                if (consecutive >= k) {
                    bouquet++;
                    consecutive = 0;
                }
            } else {
                consecutive = 0;
            }
        }
        return bouquet >= m;
    }
}
