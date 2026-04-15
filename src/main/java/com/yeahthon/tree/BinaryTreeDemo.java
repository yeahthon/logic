package com.yeahthon.tree;

import lombok.Getter;
import lombok.Setter;

// 二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建节点
        TreeHeroNode root = new TreeHeroNode(1, "宋江");
        TreeHeroNode node2 = new TreeHeroNode(2, "吴用");
        TreeHeroNode node3 = new TreeHeroNode(3, "卢俊义");
        TreeHeroNode node4 = new TreeHeroNode(4, "林冲");
        TreeHeroNode node5 = new TreeHeroNode(5, "关胜");
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree(root);
        // 连接节点
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        // 遍历测试
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

        // 查找测试
        TreeHeroNode result = binaryTree.searchPreOrder(5);
        System.out.println("找到[id为" + result.getId() + "，name为" + result.getName() + "]的元素！");
        TreeHeroNode result1 = binaryTree.searchInfixOrder(3);
        System.out.println("找到[id为" + result1.getId() + "，name为" + result1.getName() + "]的元素！");
        TreeHeroNode result2 = binaryTree.searchPostOrder(2);
        System.out.println("找到[id为" + result2.getId() + "，name为" + result2.getName() + "]的元素！");
    }
}

@Setter
// 定义二叉树
class BinaryTree {
    // 根节点
    private TreeHeroNode root;

    public BinaryTree(TreeHeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    // 前序遍历查找
    public TreeHeroNode searchPreOrder(int id) {
        if (root != null) {
            return root.searchPreorder(id);
        } else {
            return null;
        }
    }

    // 中序遍历查找
    public TreeHeroNode searchInfixOrder(int id) {
        if (root != null) {
            return root.searchInfixOrder(id);
        } else {
            return null;
        }
    }

    // 后续遍历查找
    public TreeHeroNode searchPostOrder(int id) {
        if (root != null) {
            return root.searchPostOrder(id);
        } else {
            return null;
        }
    }
}

@Getter
@Setter
class TreeHeroNode {
    private int id;
    private String name;
    private TreeHeroNode left;
    private TreeHeroNode right;

    public TreeHeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [id = " + id + ",name=" + name + "]";
    }

    // 前序遍历
    public void preOrder() {
        // 先输出该节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出该节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后续遍历
    public void postOrder() {
        // 递归向左子树后续遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出该节点
        System.out.println(this);
    }

    // 前序遍历查找
    public TreeHeroNode searchPreorder(int id) {
        // 1、比较当前节点
        if (this.id == id) {
            return this;
        }

        // 2、向左递归前序查找
        TreeHeroNode result = null;
        if (this.left != null) {
            result = this.left.searchPreorder(id);
        }
        if (result != null) {
            return result;
        }

        // 3、向右递归前序查找
        if (this.right != null) {
            result = this.right.searchPreorder(id);
        }
        return result;
    }

    // 中序遍历查找
    public TreeHeroNode searchInfixOrder(int id) {
        // 1、先向左中序遍历查找
        TreeHeroNode result = null;
        if (this.left != null) {
            result = this.left.searchInfixOrder(id);
        }
        if (result != null) {
            return result;
        }

        // 2、比较当前节点
        if (this.id == id) {
            return this;
        }

        // 3、向右中序遍历查找
        if (this.right != null) {
            result = this.right.searchInfixOrder(id);
        }
        return result;
    }

    // 后续遍历查找
    public TreeHeroNode searchPostOrder(int id) {
        // 1、先向左后续遍历查找
        TreeHeroNode result = null;
        if (this.left != null) {
            result = this.left.searchPostOrder(id);
        }
        if (result != null) {
            return this;
        }

        // 2、再向右后序遍历查找
        if (this.right != null) {
            result = this.right.searchPostOrder(id);
        }
        if (result != null) {
            return this;
        }

        // 3、最后与当前节点比较
        if (this.id == id) {
            return this;
        }

        return result;
    }
}