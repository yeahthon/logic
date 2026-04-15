package com.yeahthon.app;

// func：二分查找（只查找单个元素）
public class SearchByBinary {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 7, 11, 23};
        int result = binarySearch(arr, 0, 7, 11);
        System.out.println(result);
    }

    public static int binarySearch(int[] array, int left, int right, int searchElement) {
        // 当 left > right 时，说明递归整个数组后并没有找到相应的元素
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (searchElement > midValue) {
            // 向右递归
            return binarySearch(array, mid + 1, right, searchElement);
        } else if (searchElement < midValue) {
            // 向左递归
            return binarySearch(array, left, mid - 1, searchElement);
        } else {
            return mid;
        }
    }
}
