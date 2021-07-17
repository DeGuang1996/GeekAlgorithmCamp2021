package week05;

public class ShipWithinDays {

    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int num : weights) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean isValid(int[] weights, int days, int capacity) {
        int needDays = 1;
        int cur = 0;
        for (int num : weights) {
            if (cur + num > capacity) {
                needDays++;
                if (needDays > days) {
                    return false;
                }
                cur = num;
            } else {
                cur += num;
            }
        }
        return true;
    }
}
