package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 최종 순위
public class Main_3665 {

    static int T;
    static int N, M;
    static int[] origin;
    static int[] inDegree;  // 진입차수
    static Set<Integer>[] graph;
    static Queue<Integer> q;

    static StringBuilder answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        answer = new StringBuilder();
        for (int t = 0; t < T; t++) {

            N = Integer.parseInt(br.readLine());
            origin = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 작년 순위를 입력받는다
            for (int i = 1; i < N + 1; i++) {
                origin[i] = Integer.parseInt(st.nextToken());
            }

            // 작년 순위를 기반으로 방향 그래프를 생성한다
            graph = new HashSet[N + 1];
            inDegree = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                graph[i] = new HashSet<>();
            }
            // 선후관계 변경이 있으므로, 가능한 모든 관계를 추가해준다.
            for (int i = 1; i < N + 1; i++) {
                int from = origin[i];
                for (int j = i + 1; j < N + 1; j++) {
                    graph[from].add(origin[j]);
                    inDegree[origin[j]]++;  // 해당 팀의 진입차수를 증가시켜준다.
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                // 선후관계를 변경해준다
                swap(from, to);
            }

            answer.append(bfs()).append("\n");
        }
        System.out.println(answer);
    }

    // 선후관계 변경
    static void swap(int from, int to){

        if (graph[from].contains(to)){
            graph[from].remove(to);
            graph[to].add(from);
            inDegree[from]++;
            inDegree[to]--;
        } else {
            graph[to].remove(from);
            graph[from].add(to);
            inDegree[from]--;
            inDegree[to]++;
        }
    }

    static String bfs(){
        q = new ArrayDeque<>();

        for (int i = 1; i < N + 1; i++) {
            // 진입차수가 0인 노드들 찾기 -> 시작지점이 될 수 있는 것들
            if (inDegree[i] == 0){
                q.offer(i);
            }
        }

        // 만약 시작지점이 여러 개라면 -> 정답을 특정할 수 없다.
        if (q.size() > 1){
            return "?";
        }

        StringBuilder sb = new StringBuilder();
        // while문이 아닌 for문을 사용하여 오류인 경우를 검출한다
        for (int i = 1; i < N + 1; i++) {

            // 모든 노드를 순회하지 못한 경우 -> 오류가 있다는 뜻
            if (q.isEmpty()){
                return "IMPOSSIBLE";
            }

            // queue의 노드를 꺼낸다
            int from = q.poll();
            sb.append(from).append(" ");

            // 진입차수를 재조정한다 (간선 제거)
            for (int to : graph[from]) {
                inDegree[to]--;
                // inDegree가 0인 노드들을 queue에 넣는다
                if (inDegree[to] == 0){
                    q.offer(to);
                }
            }
        }

        return sb.toString();
    }
}
