package com.ssafy.baekjoon.study.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774 {

    static class Edge implements Comparable<Edge> {

        int start;
        int end;
        double weight;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
            this.weight = getDistance(start, end);
        }

        private double getDistance(int start, int end){
            return Math.sqrt(
                    Math.pow(Math.abs(points[start][0] - points[end][0]), 2.0)
                            + Math.pow(Math.abs(points[start][1] - points[end][1]), 2.0)
            );
        }


        @Override
        public int compareTo(Edge other) {
            if (this.weight < other.weight){
                return -1;
            }
            else if (this.weight == other.weight){
                return 0;
            }
            else {
                return 1;
            }
        }
    }

    static int N, M;
    static int[] p;
    static int[] rank;
    static PriorityQueue<Edge> edges;
    static int[][] points;
    static int cnt;
    static int dup;
    static double min;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();
        points = new int[N][2];
        cnt = 0;
        dup = 0;
        min = 0D;

        // 좌표 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        // 가능한 모든 간선들을 추가
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                edges.offer(new Edge(i, j));
            }
        }

        makeSet();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            // cycle이 생기는 경우를 제외하기 위함
            if (!union(x, y)){
                dup++;
            }
        }

        // N - 1: 모든 정점을 이을 수 있는 간선의 최소 개수
        // M: 이미 연결된 통로의 개수
        // dup: cycle이 생기는 경우의 수
        // 모든 간선을 체크하지 않기 위함
        while (cnt < N - 1 - M + dup){
            Edge edge = edges.poll();
            if (union(edge.start, edge.end)){
                cnt++;
                min += edge.weight;
            }
        }
        System.out.println(String.format("%.2f", min));
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (rank[x] > rank[y]){
            p[y] = x;
        }
        else if (rank[x] == rank[y]){
            p[y] = x;
            rank[x]++;
        }
        else {
            p[x] = y;
        }
        return true;
    }

    static int find(int x){
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    static void makeSet(){
        p = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }
}