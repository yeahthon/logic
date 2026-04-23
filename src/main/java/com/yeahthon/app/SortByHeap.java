package com.yeahthon.app;

import java.util.Arrays;

// 堆排序
public class SortByHeap {
    public static void main(String[] args) {
        int[] array = {4, 6, 8, 5, 9};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    // 堆排序主方法
    public static void heapSort(int[] array) {
        int n = array.length;

        // 1、构建大顶堆，从最后一个非子叶点开始
        // 最后一个非子叶点的索引为i=n/2-1
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, n);
        }
        // 2、逐个将堆顶元素移到数组末尾
        for (int j = n - 1; j > 0; j--) {
            // 交换堆顶和当前末尾
            swap(array, 0, j);
            // 重新调整堆
            adjustHeap(array, 0, j);
        }
    }

    /**
     * func：将索引i对应的非叶子节点的树调整为大顶堆
     * @param array 待排序的数组
     * @param i 当前非叶子节点的索引
     * @param length 表示对多少个元素继续进行调整
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 保存当前节点的值
        int temp = array[i];

        // 从当前节点的左子节点开始遍历
        // k = 2 * i + 1 ：表示左子节点的索引值
        // k = 2 * k + 1 ：表示当前左子节点的左子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 判断当前节点的左右子节点的大小
            if (k + 1 < length && array[k] < array[k + 1]) {
                // k指向右子节点
                k++;
            }

            // 判断当前节点和子节点的大小
            if (array[k] > temp) {
                // 子节点上移
                array[i] = array[k];
                // i指向子节点，继续向下比较
                i = k;
            } else {
                break;
            }

            // 放回最终位置
            array[i] = temp;
        }
    }

    // 交换方法
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
