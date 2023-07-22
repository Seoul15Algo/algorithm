package baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 1939 중량제한
public class Main_1939 {

    static int N, M;
    static List<Edge>[] graph;
    static int FROM, TO;
    static int max, min;

    static class Edge{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            max = Math.max(max, weight);
            min = Math.min(min, weight);
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        FROM = Integer.parseInt(st.nextToken());
        TO = Integer.parseInt(st.nextToken());

        // 이분탐색
        int start = min;
        int end = max;

        while (start <= end) {
            int mid = (start + end) / 2;

            // mid값이 최대 -> 더 큰 값이 있을수도?
            if (bfs(mid)){
                start = mid + 1;
                continue;
            }
            // mid값이 최대가 아님 -> 더 작은 값에서 찾아봐야 한다
            end = mid - 1;
        }
        System.out.println(start - 1);
    }

    static boolean bfs(int max){

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(FROM);
        visited[FROM] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == TO) {
                return true;
            }
            for (Edge next : graph[cur]) {
                // 최댓값보다 작은 간선은 체크하면 안됨
                if (next.weight < max) {
                    continue;
                }
                if (visited[next.to]){
                    continue;
                }
                q.offer(next.to);
                visited[next.to] = true;
            }
        }

        return false;
    }
}
