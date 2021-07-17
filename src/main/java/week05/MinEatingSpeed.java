package week05;

public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 0, right = piles[0];
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        left = Math.max(right / h, 1);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isValid(int[] piles, int h, int speed) {
        int cur = 0;
        for (int pile : piles) {
            cur += (pile + speed - 1) / speed;
            if (cur > h) {
                return false;
            }
        }
        return true;
    }
}
