package com.yeahthon.app;

import java.util.ArrayList;
import java.util.List;

// 二分查找（查找多个元素）
public class SearchByBinaryMul {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 6, 6, 9, 11, 12};
        List<Integer> resultList = binarySearchMul(array, 0, 8, 6);
        System.out.println(resultList);
    }

    public static List<Integer> binarySearchMul(int[] array, int left, int right, int searchElement) {
        // 当 left > right 时，说明递归整个数组后并没有找到相应的元素
        ArrayList<Integer> resultList = new ArrayList<>();
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (searchElement > midValue) {
            // 向右递归
            return binarySearchMul(array, mid + 1, right, searchElement);
        } else if (searchElement < midValue) {
            // 向左递归
            return binarySearchMul(array, left, mid - 1, searchElement);
        } else {
            // 查找到元素后不要立刻返回
            // 向左扫描，将所有满足的元素下标放入集合中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || array[temp] != searchElement) {
                    break;
                }

                resultList.add(temp);
                temp -= 1;
            }
            resultList.add(mid);

            // 向右扫描，将所有满足的元素下标放入集合中
            temp = mid + 1;
            while (true) {
                if (temp > array.length - 1 || array[temp] != searchElement) {
                    break;
                }

                resultList.add(temp);
                temp += 1;
            }
            return resultList;
        }
    }
}
