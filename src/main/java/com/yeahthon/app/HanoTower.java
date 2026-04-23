package com.yeahthon.app;

// 分治算法：汉诺塔
public class HanoTower {
    public static void main(String[] args) {
        hanoTower(5, 'A', 'B', 'C');
    }

    /**
     *
     * @param num 汉诺塔盘子的个数
     * @param a 源柱
     * @param b 辅柱
     * @param c 目标柱
     */
    public static void hanoTower(int num, char a, char b, char c) {
        if (num == 1) {
            // 只有一个盘
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 有两个以上的盘，总视为两个盘，最下面的一个盘、除了最下面的上面所有盘
            // 1、先把最上面的所有盘 A->B，移动过程会使用到C
            hanoTower(num - 1, a, c, b);
            // 2、将最下面的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3、将B塔所有的盘从B->C，移动过程中使用到A塔
            hanoTower(num - 1, b, a, c);
        }
    }
}
