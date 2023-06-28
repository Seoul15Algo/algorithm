package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1368 {
    static int N, minCost;
    static int[] parents;

    static PriorityQueue<Edge> edges;
    static class Edge implements Comparable<Edge>{
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.w , e.w);
        }
    }

    /*
        우물이라는 가상의 0번 노드를 추가하여 크루스칼 수행
        그렇지 않으면 우물을 직접 파는 경우를 고려하기 까다롭다...
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new PriorityQueue<>();

        // 직적 물을 파는 경우 가상의 0번 노드에서 논으로 간선을 그어 준다.
        for (int i = 1; i <= N; i++) {
            edges.offer(new Edge(0, i, Integer.parseInt(br.readLine())));
        }

        for (int s = 1; s <= N; s++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int e = 1; e <= N; e++) {
                int w = Integer.parseInt(st.nextToken());
                edges.offer(new Edge(s, e, w));
            }
        }

        makeSet(); // parents 배열 초기화

        minCost = 0;

        kruskal(); // kruskal

        System.out.println(minCost);
    }

    private static void makeSet() {
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x) {
        if(x == parents[x]){
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return false;
        }

        parents[py] = px;
        return true;
    }

    private static void kruskal() {
        int edgeCnt = 0;
        while (!edges.isEmpty()) {

            if (edgeCnt == N) {
                break;
            }

            Edge cur = edges.poll();

            int s = cur.s;
            int e = cur.e;
            int w = cur.w;

            if (union(s, e)) {
                edgeCnt++;
                minCost += w;
            }
        }
    }
}