package week02;

import java.util.TreeSet;

public class MaxSumSubmatrix {

    int[][] preSum;

    public void numMatrix(int[][] matrix) {
        preSum = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        // numMatrix(matrix);
        // boolean seq = matrix.length > matrix[0].length;
        //
        // int res = Integer.MIN_VALUE;
        // for (int i = 0; i < (seq ? matrix.length : matrix[0].length); i++) {
        //     for (int j = i; j < (seq ? matrix.length : matrix[0].length); j++) {
        //         TreeSet<Integer> treeSet = new TreeSet<>();
        //         treeSet.add(0);
        //         for (int l = 0; l < (seq ? matrix[0].length : matrix.length); l++) {
        //             int cur = sumRegion(seq ? i : 0, seq ? 0 : i, seq ? j : l, seq ? l : j);
        //             Integer near = treeSet.ceiling(cur - k);
        //             if (near != null) {
        //                 res = Math.max(res, cur - near);
        //             }
        //             treeSet.add(cur);
        //         }
        //     }
        // }
        // return res;

        // numMatrix(matrix);
        // int m = matrix.length;
        //
        // int res = Integer.MIN_VALUE;
        // for (int i = 0; i < m; i++) {
        //     for (int j = i; j < m; j++) {
        //         TreeSet<Integer> treeSet = new TreeSet<>();
        //         treeSet.add(0);
        //         for (int l = 0; l < matrix[j].length; l++) {
        //             int cur = sumRegion(i, 0, j, l);
        //             Integer near = treeSet.ceiling(cur - k);
        //             if (near != null) {
        //                 res = Math.max(res, cur - near);
        //             }
        //             treeSet.add(cur);
        //         }
        //     }
        // }
        // return res;

        int res = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];

            for (int j = i; j < m; j++) {

                for (int l = 0; l < n; l++) {
                    sum[l] += matrix[j][l];
                }

                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0);

                int cur = 0;
                for (int val : sum) {
                    cur += val;
                    Integer near = treeSet.ceiling(cur - k);
                    if (near != null) {
                        res = Math.max(res, cur - near);
                    }
                    treeSet.add(cur);
                }
            }
        }
        return res;
    }
}
