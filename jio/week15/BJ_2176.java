package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2176 {
    static int N, M, pathCnt, startToEnd;
    static int START = 1, END = 2;
    static List<Pair>[] graph;
    static int[] distances, dp;

    static class Pair {
        int node, dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static class Node implements Comparable<Node>{
        int node, dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pathCnt = 0;
        startToEnd = Integer.MAX_VALUE;

        graph = new List[N+1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Pair(e, w));
            graph[e].add(new Pair(s, w));
        }

        dijkstra();

        dp = new int[N + 1]; // dp[i] : i번쨰 노드에서 도착지까지 가는 합리적인 경로의 개수
        dp[END] = 1; // 도착지에서 도착지까지는 1로 초기화
        dfs(START);  // 시작점 부터 도착지까지 DFS로 탐색하며 합리적인 경로의 개수를 구한다.

        System.out.println(dp[START]);
    }

    private static void dijkstra(){ // 도착지에서 부터 다익스트라를 수행하여 각 노드에서 도착지까지의 최단 거리를 구한다.
        distances = new int[N + 1]; // distances[i] : i번쨰 노드에서 도착지까지 걸리는 최단 거리

        for (int i = 0; i < N + 1; i++) {
            distances[i] = Integer.MAX_VALUE / 100;
        }
        distances[END] = 0;

        PriorityQueue<Node> pque = new PriorityQueue<>();
        pque.offer(new Node(END, 0));

        while (!pque.isEmpty()) {
            Node cur = pque.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if (curDist != distances[curNode]) { // 우선순위 큐에 남아있는 잔재일 경우
                continue;
            }

            for(Pair next : graph[curNode]){
                int nextNode = next.node;
                int nextDist = next.dist;

                int newDist = distances[curNode] + nextDist;
                if (distances[nextNode] > newDist) { // 새로운 거리가 기존 거리보다 작을 경우 distances 배열 값 갱신
                    distances[nextNode] = newDist;
                    pque.offer(new Node(nextNode, newDist));
                }
            }
        }
    }

    private static int dfs(int cur) {

        if (dp[cur] != 0) { // dp 테이블에 이미 값이 채워진 경우(이미 방문한 경우) => 중복을 제거할 수 있다.
            return dp[cur];
        }

        for (Pair next : graph[cur]) {
            int nextNode = next.node;

            if (distances[cur] > distances[nextNode]) { // 합리적이라면 dfs 탐색
                dp[cur] += dfs(nextNode);
            }
        }

        return dp[cur]; // 리프 노드일 경우
    }

}
