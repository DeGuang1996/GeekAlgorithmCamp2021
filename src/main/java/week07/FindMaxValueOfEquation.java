package week07;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindMaxValueOfEquation {

    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < points.length; i++) {
            // poll不合适条件，队头合法
            while (!deque.isEmpty() && points[deque.peekFirst()][0] < points[i][0] - k) {
                deque.pollFirst();
            }
            // 取队头为最优解
            if (!deque.isEmpty()) {
                ans = Math.max(ans, points[i][0] + points[i][1] + points[deque.peekFirst()][1] - points[deque.peekFirst()][0]);
            }
            // 维护单调性
            while (!deque.isEmpty() && points[deque.peekLast()][1] - points[deque.peekLast()][0] <= points[i][1] - points[i][0]) {
                deque.pollLast();
            }
            // 添加下标元素
            deque.offerLast(i);
        }
        return ans;
    }
}
