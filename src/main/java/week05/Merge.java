package week05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Merge {

    public int[][] merge(int[][] intervals) {
        // Arrays.sort(intervals, (term1, term2) -> term1[0] - term2[0]);
        // ArrayList<int[]> ans = new ArrayList<>();
        // int[] pre = {-1, -1};
        // for (int[] interval : intervals) {
        //     if (interval[0] > pre[1]) {
        //         ans.add(new int[] {pre[0], pre[1]});
        //         pre = interval;
        //     } else {
        //         if (interval[1] > pre[1]) {
        //             pre[1] = interval[1];
        //         }
        //     }
        // }
        // ans.add(new int[] {pre[0], pre[1]});
        // int[][] res = new int[ans.size() - 1][2];
        // for (int i = 1; i < ans.size(); i++) {
        //     res[i - 1] = ans.get(i);
        // }
        // return res;

        ArrayList<int[]> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1});
            events.add(new int[]{interval[1] + 1, -1});
        }
        events.sort((term1, term2) -> {
            if (term1[0] == term2[0]) {
                return term1[1] - term2[1];
            }
            return term1[0] - term2[0];
        });
        int count = 0;
        int left = 0;
        ArrayList<int[]> ans = new ArrayList<>();
        for (int[] event : events) {
            if (count == 0) {
                left = event[0];
            }
            count += event[1];
            if (count == 0) {
                ans.add(new int[]{left, event[0] - 1});
            }
        }
        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,4},{0,0}};
        Merge merge = new Merge();
        merge.merge(intervals);
    }
}
