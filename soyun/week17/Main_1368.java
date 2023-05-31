package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368 {

    static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        Edge(int vertex, int cost){
            this.vertex = vertex;
            this.weight = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int N;
    static List<Edge>[] graph;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        pq = new PriorityQueue<>();
        // 우물을 파는 경우 -> 시작 노드 역할, pq에 넣어서 가장 작은 시작 노드를 꺼낼 것임
        for (int i = 0; i < N; i++) {
            int weight = Integer.parseInt(br.readLine());
            pq.offer(new Edge(i, weight));
        }

        for (int from = 0; from < N; from++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int to = 0; to < N; to++) {
                int weight = Integer.parseInt(st.nextToken());
                graph[from].add(new Edge(to, weight));
                graph[to].add(new Edge(from, weight));
            }
        }

        System.out.println(prim());
    }

    // prim 알고리즘
    static int prim() {
        boolean[] visited = new boolean[N];

        int total = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int vertex = edge.vertex;
            int weight = edge.weight;

            // 중복 방문 방지
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            total += weight;

            // 해당 노드와 연결된 간선들을 pq에 넣음
            for (Edge e : graph[vertex]) {
                if (!visited[e.vertex]){
                    pq.offer(e);
                }
            }
        }

        return total;
    }
}
