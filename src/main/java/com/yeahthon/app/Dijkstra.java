package com.yeahthon.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 迪杰斯特拉算法：最短路径
public class Dijkstra {
    public static void main(String[] args) {
        int n = 6; // 节点数
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构建图（有向图）
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(2, 5));
        graph.get(1).add(new Edge(3, 10));
        graph.get(2).add(new Edge(4, 3));
        graph.get(4).add(new Edge(3, 4));
        graph.get(3).add(new Edge(5, 11));

        int source = 0;

        dijkstra(graph, source);
    }

    // 边结构
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 节点（用于优先队列）
    static class Node implements Comparable<Node> {
        int id;
        int dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist; // 小顶堆
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] visited = new boolean[n];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 初始化
        dist[source] = 0;
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.id;

            // 已处理过就跳过（避免重复计算）
            if (visited[u]) continue;
            visited[u] = true;

            // 遍历邻接边
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                // 松弛操作
                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        // 输出结果
        System.out.println("最短路径结果：");
        for (int i = 0; i < n; i++) {
            System.out.println("源点到 " + i + " 的距离为: " + dist[i]);
        }
    }
}
