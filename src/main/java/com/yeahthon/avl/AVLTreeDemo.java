package com.yeahthon.avl;

import lombok.Getter;

// 平衡二叉树
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] array = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int j : array) {
            avlTree.add(new Node(j));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("根节点为：" + avlTree.getRoot());
    }
}

// AVLTree
@Getter
class AVLTree {
    private Node root;

    // 添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
}

// 二叉树节点
class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node[value=" + value + "]";
    }

    // 中序遍历
    public void infixOrder() {
        // 先递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 当前节点
        System.out.println(this);
        // 再递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    // 返回当前节点为根节点的树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(),
                this.right == null ? 0 : this.right.height()) + 1;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        } else {
            return this.left.height();
        }
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    // 左旋转方法
    private void leftRotate() {
        // 创建新节点
        Node newNode = new Node(this.value);
        // 将新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        // 将新节点的右子树设置为当前节点右子树的左子树
        newNode.right = this.right.left;
        // 将当前节点的值替换为右子节点的值
        this.value = this.right.value;
        // 将当前节点的右子树设置为当前节点的右子树的右子树
        this.right = this.right.right;
        // 将当前节点的左子树设置成新节点
        this.left = newNode;
    }

    // 右旋转方法
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    // 添加新节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入节点的值和当前树的根节点值的关系
        if (node.value < this.value) {
            if (this.left == null) {
                // 如果当前节点的左子树节点为null
                this.left = node;
            } else {
                // 向左递归添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 添加完节点后，如果（右子树高度-左子树的高度）> 1则进行左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                // 先对右子节点进行右旋转
                this.right.rightRotate();
                // 再对当前节点进行左旋转
                this.leftRotate();
            } else {
                // 直接进行左旋转
                this.leftRotate();
            }
            return;
        }

        // 添加完节点后，如果（左子树高度-右子树的高度）> 1则进行左旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
                this.rightRotate();
            } else {
                this.rightRotate();
            }
        }
    }
}