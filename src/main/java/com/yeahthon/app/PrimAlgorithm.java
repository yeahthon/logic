package com.yeahthon.app;

import java.util.Arrays;

// 普利姆算法：修路问题
public class PrimAlgorithm {
    // 图中最大的点数，用于代表两点之间不连接、不修路
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // 村庄之间修路成本（INF表示不可直接修路）
        int[][] graph = {
                {0, 5, 3, INF, INF},
                {5, 0, 2, 6, INF},
                {3, 2, 0, 7, 4},
                {INF, 6, 7, 0, 2},
                {INF, INF, 4, 2, 0}
        };

        prim(graph, 5);
    }

    public static void prim(int[][] graph, int n) {
        // 标记节点是否已经加入生成树
        boolean[] visited = new boolean[n];
        // 表示i到当前生成树的最小代价
        int[] lowCost = new int[n];
        // 初始化：从0号村庄开始
        Arrays.fill(lowCost, INF);
        lowCost[0] = 0;

        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            int min = INF;
            int u = -1;

            // 找到当前未加入节点中，连接成本最低的点
            for (int j = 0; j < n; j++) {
                if (!visited[j] && lowCost[j] < min) {
                    min = lowCost[j];
                    u = j;
                }
            }

            // 加入生成树
            visited[u] = true;
            totalCost += (min == INF ? 0 : min);

            System.out.println("加入村庄：" + u + "，当前修路成本：" + min);

            // 更新其他店到生成树的最小距离
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] < lowCost[v]) {
                    lowCost[v] = graph[u][v];
                }
            }
        }
        System.out.println("最小修路总成本：" + totalCost);
    }
}
