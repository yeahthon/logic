package com.yeahthon.app;

// 二分查找（非递归）
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 6, 8, 9};
        int targetIndex = binarySearch(array, 6);
        System.out.println(targetIndex);
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                // 向左查找
                right = mid - 1;
            } else {
                // 向右查找
                left = left + 1;
            }
        }

        return -1;
    }
}
