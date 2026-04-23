package com.yeahthon.huffmancode;

import java.util.*;

// TODO 未完
// 赫夫曼编码、解码
public class HuffmanTreeCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
//        System.out.println("原始的字节数组的长度为：" + content.length());
//        // 将字节数组封装为List<Node>
//        List<Node> nodeList = byteToNode(bytes);
//        System.out.println("List<Node>的集合为：" + nodeList);
//        // 生成Huffman树
//        Node huffmanRoot = huffmanTree(nodeList);
//        System.out.println("遍历Huffman树:");
//        // 遍历
//        HuffmanTreeCode.preOrder(huffmanRoot);
//        // 生成Huffman编码
//        Map<Byte, String> huffmanCodes = getCodes(huffmanRoot);
//        System.out.println("Huffman编码为：");
//        System.out.println(huffmanCodes);

        byte[] zip = huffmanZip(bytes);
        System.out.println(Arrays.toString(zip));
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodeList = byteToNode(bytes);
        Node huffmanTreeRoot = huffmanTree(nodeList);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        return zip(bytes, huffmanCodes);
    }

    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @param huffmanCode 赫夫曼编码表
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        // 1、利用huffmanCodes将bytes转成huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    // Huffman表，用于存放字符（字节码）以及对应的路径（code）
    // Map<Byte,String>:{32:01,97:100,100:11000,...}
    static Map<Byte, String> huffmanCode = new HashMap<>();
    // 拼接的路径，初始字符串
    static StringBuilder originString = new StringBuilder();

    // 重载getCodes方法，让其从根节点的左右子节点开始遍历
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        // 处理左子树
        getCodes(root.left, "0", originString);
        // 处理右子树
        getCodes(root.right, "1", originString);

        return huffmanCode;
    }

    /**
     * func：将传入的Huffman树的所有叶子节点的路径code进行编码，并拼接到字符串中
     * @param node Huffman树中正在遍历的某个节点
     * @param code 左子节点为0，右子节点为1
     * @param preString 拼接的路径字符串
     */
    private static void getCodes(Node node, String code, StringBuilder preString) {
        // 存在递归操作，用另一个字符串接收拼接后的结果
        StringBuilder resultString = new StringBuilder(preString);
        // 在调用该方法时，会先从根节点开始遍历，遍历时将路径code进行拼接
        resultString.append(code);
        if (node != null) {
            if (node.data == null) {
                // 当前为非叶子节点
                // 向左递归
                getCodes(node.left, "0", resultString);
                // 向右递归
                getCodes(node.right, "1", resultString);
            } else {
                // 当前为叶子节点
                // 将该叶子节点的data、code存入到HuffmanCode
                huffmanCode.put(node.data, resultString.toString());
            }
        }
    }

    /**
     * func：根据传入的Node集合，生成一个huffman树
     * @param nodeList List<Node>
     * @return Huffman树的根节点Node
     */
    private static Node huffmanTree(List<Node> nodeList) {
        while (nodeList.size() > 1) {
            // 排序
            Collections.sort(nodeList);
            // 取出第一科最小的二叉树
            Node leftNode = nodeList.get(0);
            // 取出第二棵最小的二叉树
            Node rightNode = nodeList.get(1);
            // 生成父节点，没有data，只存在weight
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 剔除已经处理的节点
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            // 将新的二叉树放入集合
            nodeList.add(parent);
        }

        // 最后只剩一个节点，就是Huffman树的根节点
        return nodeList.get(0);
    }

    /**
     * func：将字节数组封装为Node集合
     * @param bytes 字节数组
     * @return [Node[97,3],Node[100,2],...]
     */
    private static List<Node> byteToNode(byte[] bytes) {
        ArrayList<Node> nodeList = new ArrayList<>();

        // 先遍历字节数组，统计每个字符（字节）出现的次数，暂用Map集合封装
        HashMap<Byte, Integer> byteCountMap = new HashMap<>();
        for (byte element : bytes) {
            byteCountMap.merge(element, 1, Integer::sum);
        }

        // 再将Map封装到List中
        for (Map.Entry<Byte, Integer> entry : byteCountMap.entrySet()) {
            nodeList.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodeList;
    }

    // 前序遍历方法
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空！");
        }
    }
}

// 节点
class Node implements Comparable<Node> {
    Byte data;  // 存储为字节形式的字符
    int weight;  // 每个字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    // 该遍历行为属于对象行为，应该从对象出发，而不是类调用
    public void preOrder() {
        // 1、先输出当前节点
        System.out.println(this);
        // 2、向左递归
        if (this.left != null) {
            this.left.preOrder();
        }
        // 3、向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node:[data=" + data + ",weight=" + weight + "]";
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}