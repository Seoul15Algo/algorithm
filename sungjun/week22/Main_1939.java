package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1939 {
    static int N, M, from, to, maxWeight;
    static boolean[] visited;
    static ArrayList<Bridge>[] bridges;
    static class Bridge {
        int to;
        int weight;

        public Bridge(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maxWeight = 0;

        bridges = new ArrayList[N+1];   // 인접리스트

        for (int i = 1; i < N+1; i++) {
            bridges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            // 양방향 추가
            bridges[A].add(new Bridge(B, C));
            bridges[B].add(new Bridge(A, C));

            // 최대 무게 갱신
            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());

        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = maxWeight;
        
        // 이진탐색
        while(left <= right) {
            visited = new boolean[N+1];
            int mid = (left + right) / 2;
            boolean possible = bfs(mid);    // 선택한 무게를 옮길 수 있는지

            if(possible) {      // 옮길 수 있다면
                left = mid+1;   // 최소값 갱신
                continue;
            }

            right = mid-1;      // 옮길 수 없다면 최대값 갱신
        }

        System.out.println(right);
    }

    // 선택한 무게를 옮길 수 있는지 체크
    private static boolean bfs(int weight) {
        Queue<Integer> q = new LinkedList<>();
        visited[from] = true;
        q.add(from);

        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur == to) return true;  // 도착지 도달

            for (int i = 0; i < bridges[cur].size(); i++) {
                Bridge next = bridges[cur].get(i);

                if(visited[next.to]) continue;
                if(next.weight >= weight) {
                    q.add(next.to);
                    visited[next.to] = true;
                }
            }
        }
        
        return false;   // 짐이 너무 무거움
    }
}
