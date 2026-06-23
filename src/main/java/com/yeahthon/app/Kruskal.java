package com.yeahthon.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 克鲁斯卡尔算法：公交车
public class Kruskal {
    public static void main(String[] args) {
        // 假设有 5 个公交站：0~4
        int n = 5;

        List<Edge> edges = new ArrayList<>();

        // 公交站之间的“修路成本”
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(1, 4, 7));

        int result = kruskal(n, edges);

        System.out.println("\n最小总建设成本 = " + result);
    }

    // 边结构：两个公交站 + 距离
    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    // 并查集（用于判断是否成环）
    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 查找根节点（路径压缩）
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // 合并两个集合
        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // 已经在一个集合，会形成环
            }

            parent[rootX] = rootY;
            return true;
        }
    }

    public static int kruskal(int n, List<Edge> edges) {

        // 1. 按成本排序
        edges.sort(Comparator.comparingInt(e -> e.cost));

        UnionFind uf = new UnionFind(n);

        int totalCost = 0;
        int count = 0;

        // 2. 遍历所有边
        for (Edge edge : edges) {

            // 如果不形成环，就加入
            if (uf.union(edge.from, edge.to)) {
                totalCost += edge.cost;
                count++;

                System.out.println("连接公交站 " + edge.from +
                        " -> " + edge.to + "，成本：" + edge.cost);

                // MST 只需要 n-1 条边
                if (count == n - 1) {
                    break;
                }
            }
        }

        return totalCost;
    }
}
