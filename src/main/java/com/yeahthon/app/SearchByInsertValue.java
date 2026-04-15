package com.yeahthon.app;

// 插值查找法
public class SearchByInsertValue {
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 6, 9, 12, 34};
        int resultValue = insertValueSearch(array, 0, 6, 6);
        System.out.println(resultValue);
    }

    public static int insertValueSearch(int[] array,
                                        int left,
                                        int right,
                                        int searchValue) {
        // searchValue < array[0]、searchValue > array[searchValue]条件必须，防止数组越界
        if (left > right || searchValue < array[0] || searchValue > array[searchValue]) {
            return -1;
        }

        int mid = left + (right - left) * (searchValue - array[left]) / (array[right] - array[left]);
        int midValue = array[mid];
        if (searchValue > midValue) {
            // 向右递归
            return insertValueSearch(array, mid + 1, right, searchValue);
        } else if (searchValue < midValue) {
            // 向左递归
            return insertValueSearch(array, left, mid - 1, searchValue);
        } else {
            return mid;
        }
    }
}
