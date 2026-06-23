package com.yeahthon.app;

// 动态规划（背包问题）
public class DynamicProgramming {
    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 4, 3};
        // 物品的价值
        int[] value = {1500, 3000, 2000};
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = value.length;

        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // path记录放入商品的情况
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行、第一列均为0，不做处理
        for (int i = 0; i < value.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        // 动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    // 当准备加入新增的商品容量大于当前背包容量时，就直接使用上一个单元格的装入策略
                    // v[i - 1][j]：即为上一个单元格的装入的最大值
                    v[i][j] = v[i - 1][j];
                } else {
                    // 当准备加入的新增商品的容量小于等于当前背包容量时
                    // v[i - 1][j - w[i - 1]]：装入i- 1商品，到剩余空间j - w[i - 1]的最大值
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
                        // 将当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        // 行的最大标
        int i = path.length - 1;
        // 列的最大标
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            // 从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
