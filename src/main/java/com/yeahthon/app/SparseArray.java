package com.yeahthon.app;

/**
 * desc：稀疏数组
 * func：
 *      1、使用二维数组模拟棋盘
 *      2、使用稀疏数组存储二维数组数据
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] originChess = new int[11][11];
        originChess[1][2] = 1;
        originChess[2][3] = 2;
        originChess[4][9] = 2;

        System.out.println("原始的二维数组：");
        for (int[] row : originChess) {
            for (int element : row) {
                System.out.printf("%d\t", element);
            }
            System.out.println();
        }
        System.out.println();

        // 将二维数组转为稀疏数组
        // 1、遍历原始二维数组，获取元素的个数
        int elementSum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (originChess[i][j] != 0) {
                    elementSum++;
                }
            }
        }
        System.out.println("二维数组中共有元素个数：" + elementSum);
        System.out.println();

        // 2、创建稀疏数组
        // 稀疏数组的第一行分别存储：行信息、列信息、元素个数
        // 稀疏数组的其他行分别存储：行信息、列信息、元素值
        int[][] sparseArray = new int[elementSum + 1][3];
        // 初始化第一行
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = elementSum;
        // 2.1、遍历二维数组，将元素存储到稀疏数组
        // count用于记录已经存储的元素个数
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (originChess[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = originChess[i][j];
                    count++;
                }
            }
        }

        // 3、遍历稀疏数
        System.out.println("稀疏数组：");
        for (int i = 0; i < count; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();

        // 4、将稀疏数组恢复到二维数组
        int[][] currentArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            currentArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        // 打印恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] ints : currentArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }
}
