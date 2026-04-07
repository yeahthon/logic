package com.yeahthon.app;

/**
 * func：使用递归求解迷宫
 */
public class MazeByRecursion {
    public static void main(String[] args) {
        // 初始化迷宫
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;

        // 显示初始地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 使用递归寻找路径
        setWay(map, 1, 1);

        // 输出新地图
        System.out.println("查找到的路径为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param map 表示地图
     * @param i 出发位置
     * @param j 出发位置
     * 约定：
     *      0：表示该点没有走过
     *      1：表示墙
     *      2：表示路可以走
     *      3：该点已经走过但不通
     * 策略：
     *    下 -> 右 -> 上 -> 左
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 表名已经找到通路
            return true;
        } else {
            if (map[i][j] == 0) {
                // 当前点还没有走过
                // 按照“下 -> 右 -> 上 -> 左”的策略

                // 先假设该点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return false;
                } else {
                    // 该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j]！=0，可能是1，2，3
                return false;
            }
        }
    }
}
