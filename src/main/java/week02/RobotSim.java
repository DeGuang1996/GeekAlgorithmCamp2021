package week02;

import java.util.HashSet;
import java.util.Set;

public class RobotSim {

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> set = new HashSet<>();
        for (int[] nums : obstacles) {
            set.add(getHash(nums));
        }
        int x = 0, y = 0;
        int res = 0;
        int[] xDirection = {0, 1, 0, -1};
        int[] yDirection = {1, 0, -1, 0};
        int direction = 0;
        for (int command : commands) {
            if (command == -2) {
                direction = (direction + 3) % 4;
            } else if (command == -1) {
                direction = (direction + 1) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    if (set.contains(getHash(new int[]{x + xDirection[direction], y + yDirection[direction]}))) {
                        break;
                    }
                    x = x + xDirection[direction];
                    y = y + yDirection[direction];
                    if (x * x + y * y > res) {
                        res = x * x + y * y;
                    }
                }
            }
        }
        return res;
    }

    private Long getHash(int[] nums) {
        return (nums[0] + 30000) * 60000L  + nums[1] + 30000L;
    }
}
