package week02;

import java.util.HashMap;
import java.util.TreeSet;

public class NumSubmatrixSumTarget {

    // int[][] preSum;
    //
    // public void numMatrix(int[][] matrix) {
    //     preSum = new int[matrix.length + 1][matrix[0].length + 1];
    //     for (int i = 0; i < matrix.length; i++) {
    //         for (int j = 0; j < matrix[i].length; j++) {
    //             preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
    //         }
    //     }
    // }
    //
    // public int sumRegion(int row1, int col1, int row2, int col2) {
    //     return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
    // }
    // //
    // // public int numSubmatrixSumTarget(int[][] matrix, int target) {
    // //     numMatrix(matrix);
    // //     int res = 0;
    // //     for (int i = 0; i < matrix.length; i++) {
    // //         for (int j = 0; j < matrix[i].length; j++) {
    // //             for (int k = 0; k <= i; k++) {
    // //                 for (int l = 0; l <= j; l++) {
    // //                     if (sumRegion(k, l, i, j) == target) {
    // //                         res++;
    // //                     }
    // //                 }
    // //             }
    // //         }
    // //     }
    // //     return res;
    // // }
    //
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // numMatrix(matrix);
        // int res = 0;
        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = i; j < matrix.length; j++) {
        //
        //         HashMap<Integer, Integer> hashMap = new HashMap<>();
        //         hashMap.put(0, 1);
        //         for (int k = 0; k < matrix[j].length; k++) {
        //             int cur = sumRegion(i, 0, j, k);
        //             if (hashMap.containsKey(cur - target)) {
        //                 res += hashMap.get(cur - target);
        //             }
        //             hashMap.put(cur, hashMap.getOrDefault(cur, 0) + 1);
        //         }
        //     }
        // }
        // return res;

        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] sum = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {

                for (int k = 0; k < matrix[j].length; k++) {
                    sum[k] += matrix[j][k];
                }

                HashMap<Integer, Integer> hashMap = new HashMap<>();
                hashMap.put(0, 1);

                int cur = 0;
                for (int k = 0; k < matrix[j].length; k++) {
                    cur += sum[k];
                    if (hashMap.containsKey(cur - target)) {
                        res += hashMap.get(cur - target);
                    }
                    hashMap.put(cur, hashMap.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NumSubmatrixSumTarget numSubmatrixSumTarget = new NumSubmatrixSumTarget();
        // int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        int[][] matrix = {{1,-1},{-1,1}};
        numSubmatrixSumTarget.numSubmatrixSumTarget(matrix, 0);
    }
}
