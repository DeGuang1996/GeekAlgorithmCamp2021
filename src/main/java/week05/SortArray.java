package week05;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class SortArray {

    public int[] sortArray(int[] nums) {
        // Java API 自带排序
        // Arrays.sort(nums);
        // return nums;

        // 优先队列排序
        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // for (int num : nums) {
        //     priorityQueue.offer(num);
        // }
        // int idx = 0;
        // while (!priorityQueue.isEmpty()) {
        //     nums[idx++] = priorityQueue.poll();
        // }
        // return nums;

        // mergeSort(nums, 0, nums.length - 1);
        // return nums;

        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // 归并排序
    public void mergeSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        mergeSort(arr, begin, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    public void merge(int[] arr, int begin, int mid, int end) {
        int[] temp = new int[end - begin + 1];
        int i = begin, j = mid + 1;
        for (int k = 0; k < temp.length; k++) {
            if (j > end || (i <= mid && arr[i] <= arr[j])) {
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
        }
        System.arraycopy(temp, 0, arr, begin, temp.length);
    }

    // 快速排序
    public void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = partition(arr, begin, end);
        if (begin < mid) {
            quickSort(arr, begin, mid - 1);
        }
        if (end > mid) {
            quickSort(arr, mid + 1, end);
        }
    }

    public void quickSort2(int[] arr, int begin, int end) {
        if (begin == end) {
            return;
        }
        int mid = partition(arr, begin, end);
        if (begin < mid) {
            quickSort(arr, begin, mid);
        }
        if (end > mid) {
            quickSort(arr, mid + 1, end);
        }
    }

    // 论快排的几种写法
    public int partition(int[] arr, int begin, int end) {
        // int pivot = arr[begin];
        // int idx = begin;
        // while (begin < end) {
        //     while (begin < end && arr[end] >= pivot) {
        //         end--;
        //     }
        //     while (begin < end && arr[begin] <= pivot) {
        //         begin++;
        //     }
        //     if (begin < end) {
        //         arr[begin] = arr[begin] ^ arr[end];
        //         arr[end] = arr[end] ^ arr[begin];
        //         arr[begin] = arr[begin] ^ arr[end];
        //     }
        // }
        // if (idx != begin) {
        //     arr[begin] = arr[begin] ^ arr[idx];
        //     arr[idx] = arr[idx] ^ arr[begin];
        //     arr[begin] = arr[begin] ^ arr[idx];
        // }
        //
        // return begin;

        // int mark = begin;
        // int pivot = arr[begin];
        // for (int i = begin; i <= end; i++) {
        //     if (arr[i] < pivot) {
        //         mark++;
        //         int temp = arr[i];
        //         arr[i] = arr[mark];
        //         arr[mark] = temp;
        //     }
        // }
        // int temp = arr[begin];
        // arr[begin] = arr[mark];
        // arr[mark] = temp;
        // return mark;

        int pivot = arr[begin + new Random().nextInt(end - begin + 1)];
        while (begin <= end) {
            while (arr[begin] < pivot) {
                begin++;
            }
            while (arr[end] > pivot) {
                end--;
            }
            if (begin <= end) {
                int temp = arr[begin];
                arr[begin] = arr[end];
                arr[end] = temp;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,3,1};
        SortArray sortArray = new SortArray();
        sortArray.sortArray(nums);
    }
}
