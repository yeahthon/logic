package com.yeahthon.tree;

// TODO 没有理解
// 使用数组转顺序存储二叉树，并遍历
public class BinaryTreeByArrayDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        BinaryTreeByArray binaryTreeByArray = new BinaryTreeByArray(array);
        binaryTreeByArray.preOrder(0);
    }
}

class BinaryTreeByArray {
    // 使用数组来存储二叉树
    private final int[] array;

    public BinaryTreeByArray(int[] array) {
        this.array = array;
    }

    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空，不能按照二叉树前序遍历！");
        }

        // 1、输出当前节点
        System.out.println(array[index]);
        // 2、向左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(index * 2 + 1);
        }
        // 3、向右递归遍历
        if ((index * 2 + 2) < array.length) {
            preOrder(index * 2 + 2);
        }
    }
}