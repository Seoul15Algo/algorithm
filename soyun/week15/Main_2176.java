package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2176 {

    static final int INF = Integer.MAX_VALUE;
    static final int FROM = 1;
    static final int TO = 2;

    static int N, M;
    static List<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] distance;
    static int[] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, weight));
            graph[b].add(new Node(a, weight));
        }

        dijkstra(); // TO(2번 노드)를 기준으로 모든 정점에 대한 최단 거리 계산

        System.out.println(dp[FROM]);
    }

    static void dijkstra(){

        pq = new PriorityQueue<>();
        distance = new int[N + 1];
        Arrays.fill(distance, INF);

        distance[TO] = 0; // 시작정점은 0으로
        pq.offer(new Node(TO, 0));    // 시작정점 -> 시작정점으로 가는 비용은 0

        dp = new int[N + 1];
        dp[TO] = 1;  // dp 초기화

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();   // 현재 최소비용을 갖는 정점

            // 해당 정점의 비용이 distance 배열에 저장된 값보다 크다면 고려할 필요없음
            // 이 코드가 있어야 중복 방문을 막을 수 있음
            if (distance[minNode.to] < minNode.weight) {
                continue;
            }

            // 해당 정점의 인접한 정점들을 탐색
            for (Node node : graph[minNode.to]) {

                // 기존 인접 정점으로의 distance 값과 현재 선택된 정점에서 인접 정점으로 가는 비용을 비교 (최단거리 저장)
                if (distance[node.to] > minNode.weight + node.weight) {
                    distance[node.to] = minNode.weight + node.weight;
                    pq.offer(new Node(node.to, distance[node.to]));
                }

                // distance(S, T)보다 distance(X, T)가 짧을 경우 합리적인 경로임!!
                if (distance[minNode.to] > distance[node.to]) {
                    dp[minNode.to] = dp[minNode.to] + dp[node.to];
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
