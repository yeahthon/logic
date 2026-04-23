package com.yeahthon.huffman;

import java.util.ArrayList;
import java.util.Collections;

// 赫夫曼树
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        Node root = huffmanTree(array);
        preOrder(root);
    }

    public static Node huffmanTree(int[] array) {
        // 将数组元素封装为节点并排序存储
        ArrayList<Node> list = new ArrayList<>();
        for (int element : array) {
            list.add(new Node(element));
        }

        while (list.size() > 1) {
            // 排序
            Collections.sort(list);

            // 取出根节点权值最小的节点
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            // 构建新二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 提出处理过的二叉树
            list.remove(leftNode);
            list.remove(rightNode);
            // 将parent加入
            list.add(parentNode);
        }

        return list.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树！");
        }
    }
}

class Node implements Comparable<Node> {
    // 权值
    int value;
    // 左子节点
    Node left;
    // 右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
        // 输出当前节点
        System.out.println(this);
        // 递归遍历左子节点
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归遍历右子节点
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node[value=" + value + "]";
    }
}