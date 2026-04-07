package com.yeahthon.app;

/**
 * func：递归解决八皇后问题
 */
public class QueueByRecursion {
    // 皇后个数
    int max = 8;
    // 一维数组表示皇后放置位置
    int[] array = new int[max];
    // 解法的个数
    static int count = 0;
    // 判断的次数
    static int judgeCount = 0;

    public static void main(String[] args) {
        QueueByRecursion queueByRecursion = new QueueByRecursion();
        queueByRecursion.check(0);
        System.out.println("一共有" + count + "种解法！");
        System.out.println("一共判断了" + judgeCount + "次！");
    }

    // 放置第N个皇后
    private void check(int n) {
        if (n == max) {
            // 即放置最后一个皇后
            print();
            return;
        }

        // 一次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            // 想把当前这个皇后n放到该行的第一列
            array[n] = i;
            // 判断是否冲突
            if (judge(n)) {
                // 如果不冲突
                // 接着放第n+1个皇后，开始递归
                check(n + 1);
            }
            // 如果冲入，则继续执行array[n]=1;
        }
    }

    // 校验第N个皇后摆放位置是否合适
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }

    // 显示最后皇后的摆放位置
    private void print() {
        count++;
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
