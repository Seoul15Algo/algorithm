package com.ssafy.baekjoon.study.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14621 {

    static class Edge implements Comparable<Edge> {

        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge other) {

            return Integer.compare(this.weight, other.weight);
        }
    }

    static int N, M;
    static int[] p;
    static int[] rank;
    static PriorityQueue<Edge> edges;
    static char[] group;
    static int[] points;
    static int cnt;
    static int min;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();
        group = new char[N + 1];
        cnt = 0;
        min = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            group[i] = st.nextToken().charAt(0);    // 남초인지 여초인지
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());   // start
            int v = Integer.parseInt(st.nextToken());   // end
            int d = Integer.parseInt(st.nextToken());   // weight
            edges.offer(new Edge(u, v, d));
        }

        makeSet();

        while (!edges.isEmpty()){
            if (cnt == N - 1){
                break;
            }
            Edge edge = edges.poll();
            // 남초 - 남초 / 여초 - 여초 학교는 연결되지 않도록
            if (group[edge.start] == group[edge.end]){
                continue;
            }
            if (union(edge.start, edge.end)){
                cnt++;
                min += edge.weight;
            }
        }

        System.out.println((cnt < N - 1 ? -1 : min));
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if (x == y) return false; // 이미 MST 에 포함된 경우(Union 필요 X)
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
        p = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i < N; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }
}