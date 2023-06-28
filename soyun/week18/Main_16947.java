package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16947 {

    static int N;
    static List<Integer>[] graph;
    static int[] parents;
    static boolean[] visited;
    static boolean[] isCycle;
    static Queue<int[]> q;
    static int[] distance;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        q = new ArrayDeque<>();
        distance = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        findCircularLine(0, 1);
        getDistance();

        for (int i = 1; i <= N; i++) {
            result.append(distance[i]).append(" ");
        }

        System.out.println(result);
    }

    // 순환선 찾기 (DFS)
    static void findCircularLine(int prev, int cur){

        // 방문했던 곳 또 방문 -> 싸이클이 생겼기 때문
        if (visited[cur]){
            if (!isCycle[cur]){
                parents[cur] = prev;
                findCycle(cur); // 싸이클을 찾는다
            }
            return;
        }

        visited[cur] = true;
        parents[cur] = prev;    // 현재 노드에 이전에 방문한 노드를 기록한다

        // 현재 노드와 간선으로 연결된 노드들 탐색
        for (int next : graph[cur]) {
            // 이전에 방문한 노드 중복 방문하지 않도록
            if (next == prev){
                continue;
            }
            findCircularLine(cur, next);
        }
    }

    // 싸이클을 기록한다
    static void findCycle(int cur){

        if (isCycle[cur]){
            q.offer(new int[]{cur, 0}); // 순환선의 한 노드를 기점으로 BFS를 돌리기 위함
            return;
        }

        isCycle[cur] = true;    // 싸이클임을 기록
        findCycle(parents[cur]);    // 이전에 방문한 노드를 찾아간다
    }

    // 역과 순환선 사이의 거리 구하기 (BFS)
    static void getDistance(){
        visited = new boolean[N + 1];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int next : graph[cur[0]]) {
                if (visited[next]){
                    continue;
                }
                visited[next] = true;

                // 순환선일 경우
                if (isCycle[next]){
                    q.offer(new int[]{next, 0});    // 거리는 무조건 0
                }
                // 아닐 경우
                else {
                    distance[next] = cur[1] + 1;    // 순환선과의 거리를 기록
                    q.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
    }
}
