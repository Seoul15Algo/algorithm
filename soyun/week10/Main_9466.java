package com.ssafy.algo230405_Random2.soyun.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466 {

    static int n;
    static int[] students;
    static boolean[] visited;
    static boolean[] visitedCycle;
    static int total;
    
    /*
        각각의 팀원들은 어차피 누군가를 선택해야 하므로
        경로를 따라가다보면 싸이클이 만들어지는 구간이 생길 수 밖에 없음!
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            visitedCycle = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            total = 0;
            for (int i = 1; i <= n; i++) {
                find(i);
            }
            sb.append(n - total).append("\n");
        }
        System.out.println(sb);
    }

    static boolean find(int x){

        // 방문했던 곳에 또 방문하는 경우
        // 1. 밟아왔던 경로에 싸이클이 존재함을 발견 혹은
        // 2. 싸이클이 존재하는 경로에 진입했음을 의미
        if (visited[x]){
            // 아직 계산하지 않은 싸이클이라면 -> 1에 해당
            if (!visitedCycle[x]){
                checkCycle(x, 0);
            }
            // 2에 해당한다면 이미 계산된 싸이클이므로 중복계산하지 않음

            return true;    // 싸이클은 어느 노드를 택하든 경로를 따라가다 보면 무조건 발견하게 되므로 항상 true 반환
        }

        visited[x] = true;
        visitedCycle[x] = find(students[x]); // 싸이클이 생기는 경로의 노드에 대하여 visitedCycle true 처리해줌
        return true;    // 이전 경로의 노드들에게도 true 반환
    }

    /*
        싸이클이 생기는 구간: 노드를 따라가다 보면 다시 싸이클의 처음으로 돌아가게 됨
        -> 이를 이용해 몇 개의 노드가 싸이클에 해당하는지 계산
     */
    static void checkCycle(int x, int count){

        if (visitedCycle[x]){
            total = total + count;
            return;
        }

        visitedCycle[x] = true;
        checkCycle(students[x], count + 1);
    }
}