package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 원 이동하기 2 - 답봤어요
public class Main_22948 {

    static class Node {
        int idx;
        int total;
        String route;

        public Node(int idx, int total, String route) {
            this.idx = idx;
            this.total = total;
            this.route = route;
        }
    }

    static class Point implements Comparable<Point> {

        int k;
        int x;

        public Point(int k, int x) {
            this.k = k;
            this.x = x;
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.x, other.x);
        }
    }

    static final int MAX_X = 1_000_000;

    static int N, A, B;
    static PriorityQueue<Point> pq;
    static ArrayList<Integer>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        graph = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        pq.offer(new Point(0, -1 * MAX_X));
        pq.offer(new Point(0, MAX_X));
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            pq.offer(new Point(k, x - r));
            pq.offer(new Point(k, x + r));
        }
        makeGraph(-1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        search();
    }

    // 원의 왼쪽, 오른쪽 좌표를 기준으로 포함관계 생성됨
    static void makeGraph(int parent){
        Point now = pq.poll();
        if (parent != -1) {
            graph[parent].add(now.k);
            graph[now.k].add(parent);
        }
        while (now.k != pq.peek().k) {
            makeGraph(now.k);
        }
        pq.poll();
    }

    // bfs
    static void search() {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new ArrayDeque<>();
        visited[A] = true;
        q.offer(new Node(A, 1, String.valueOf(A)));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.idx == B) {
                System.out.println(cur.total);
                System.out.println(cur.route);
                return;
            }
            for (int next : graph[cur.idx]) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                q.offer(new Node(next, cur.total + 1, cur.route + " " + next));
            }
        }
    }
}