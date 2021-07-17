package week05;

import java.util.Arrays;

public class MinimumEffort {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (term1, term2) -> term2[1] - term2[0] + term1[0] - term1[1]);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < tasks.length; i++) {
            ans = Math.max(ans, sum + tasks[i][1]);
            sum+= tasks[i][0];
        }
        return ans;
    }
}
