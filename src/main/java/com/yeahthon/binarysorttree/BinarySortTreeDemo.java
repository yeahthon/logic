package com.yeahthon.binarysorttree;

import lombok.Getter;

// 二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : array) {
            binarySortTree.add(new Node(j));
        }

        System.out.println("中序遍历！");
        binarySortTree.infixOrder();
        binarySortTree.deleteNode(12);
        System.out.println("删除节点后遍历");
        binarySortTree.infixOrder();
    }
}

@Getter
class BinarySortTree {
    private Node root;

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParentNode(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParentNode(value);
        }
    }

    // 获取当前节点二叉树中的最小节点的值
    public int deleteRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        deleteNode(target.value);
        return target.value;
    }

    // 删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            // 需要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 需要删除节点的父节点
            Node parent = searchParentNode(value);

            // 当前节点对应的二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 要删除节点为叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 直接删除该叶子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                // 删除存在两个子树的节点
                targetNode.value = deleteRightTreeMin(targetNode.right);
            } else {
                // 删除只有一个子树的节点
                if (targetNode.left != null) {
                    // 删除节点存在左子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    // 删除的节点存在右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    // 按照顺序添加元素
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
            System.out.println("二叉树为空！");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            // 删除的是当前节点
            return this;
        } else if (value < this.value) {
            // 删除的节点小于当前节点，向左递归
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(value);
            }
        } else {
            // 删除的节点大于、等于当前节点，向右递归
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }

    // 查找要删除节点的父节点
    public Node searchParentNode(int value) {
        // 当前节点是要删除节点的父节点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 删除的节点小于当前节点的值，向左递归
            if (this.value > value && this.left != null) {
                return this.left.searchParentNode(value);
            } else if (this.value < value && this.right != null) {
                // 删除的节点小于当前节点的值，向右递归
                return this.right.searchParentNode(value);
            } else {
                // 没有找到父节点
                return null;
            }
        }
    }

    // 按照顺序添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            // 向左递归插入节点
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 向右递归插入节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 向左递归
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 当前节点
        System.out.println(this);
        // 向右递归
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node[value=" + value + "]";
    }
}