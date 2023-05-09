package com.ssafy.algo230307_Sort.soyun.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int num;
    int x;
    int y;
    int z;

    Point(int num, int x, int y, int z) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int weight;

    Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

}

public class Main_2887 {

    static int N;
    static Point[] points;
    static int[] p;
    static int[] rank;
    static PriorityQueue<Edge> edgeList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            points[i] = new Point(i, x, y, z);
        }

        edgeList = new PriorityQueue<>();

        // x, y, z 각각에 대해서 정렬하고 각 행성의 번호와 비용을 edgeList에 추가.

        // Comparator.comparingInt() 메소드를 사용하면 더 가독성 좋은 코드 작성 가능
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(points[i].x - points[i + 1].x);

            edgeList.offer(new Edge(points[i].num, points[i + 1].num, weight));
        }

        // Comparator.comparing() 메소드를 사용할 수도 있음
        // comparingInt()와의 차이
        // comparingInt()는 Integer.compare() 값을 반환 (Integer 비교 시 사용)
        // comparing()은 compareTo() 값을 반환 (객체 비교 시 사용)
        Arrays.sort(points, Comparator.comparing(p -> p.y));
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(points[i].y - points[i + 1].y);

            edgeList.offer(new Edge(points[i].num, points[i + 1].num, weight));
        }

        // 기존의 람다식을 이용한 정렬
        Arrays.sort(points, (p1, p2) -> p1.z - p2.z);
        for (int i = 0; i < N - 1; i++) {
            int weight = Math.abs(points[i].z - points[i + 1].z);

            edgeList.offer(new Edge(points[i].num, points[i + 1].num, weight));
        }

        makeSet();

        int result = 0;
        int cnt = 0;
        // Kruskal 알고리즘
        while (cnt < N - 1){
            Edge edge = edgeList.poll();

            // 사이클이 발생하는 간선은 제외
            if (union(edge.start, edge.end)){
                result += edge.weight;
                cnt++;
            }
        }

        System.out.println(result);
    }

    static void makeSet(){
        p = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }

    static int find(int x) {
        if (x == p[x]) {
            return x;
        }
        return p[x] = find(p[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y){
            return false;
        }

        if (rank[x] > rank[y]){
            p[y] = p[x];
        }
        else if (rank[x] == rank[y]){
            rank[x]++;
            p[y] = p[x];
        }
        else {
            p[x] = p[y];
        }
        return true;
    }
}