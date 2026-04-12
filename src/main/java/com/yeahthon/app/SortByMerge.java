package com.yeahthon.app;

import java.util.Arrays;

/**
 * func：归并排序
 */
public class SortByMerge {
    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 7, 1, 5};
        mergeSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] array, int left, int right) {
        // 遍历完跳出
        if (left >= right) {
            return;
        }
        // 递归拆分数组
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        // 合并
        merge(array, left, mid, right);
    }

    // 合并两个有序数组
    public static void merge(int[] array, int left, int mid, int right) {
        // 临时数组
        int[] temp = new int[right - left + 1];
        // 左数组指针
        int i = left;
        // 右数组指针
        int j = mid + 1;
        // 临时数组指针
        int k = 0;

        // 依次比较两个数组种的元素，并放入到临时数组中
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // 如果某个数组先遍历完，将另外一个数组的剩余部分直接放入到临时数组
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // 将临时数组再拷贝到原数组
        for (int t = 0; t < temp.length; t++) {
            array[left + t] = temp[t];
        }
    }
}
