import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1507 {
    static int N, totalDist;
    static int MAX_NUM = Integer.MAX_VALUE / 1000;
    static int[][] minDist;
    static int[] dis;

    static List<Element>[] adj;
    static PriorityQueue<Element> pque;
    static class Element {
        int index, dist;

        public Element(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    static PriorityQueue<Edge> edges;
    static class Edge{
        int from, to, dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        edges = new PriorityQueue<>((x, y) -> (x.dist - y.dist)); //입력으로 주어진 간선 정보 입력

        for (int from = 0; from < N; from++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int to = 0; to < N; to++) {
                if (to <= from) { // 양방향으로 입력이 주어져서 우선 단방향으로 입력받음
                    st.nextToken();
                    continue;
                }

                int dist = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, dist));
            }
        }

        adj = new List[N]; // 새로 그려나갈 그래프(인접리스트)
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        /**
         *    가중치가 가장 작은 간선 부터 확인
         * 1. 다익스트라의 결과 값이 주어진 간선의 가중치보다 크다면 그래프에 추가
         * 2. 다익스트라의 결과 값이 주어진 간선의 가중치보다 작다면 불가능한 경우
         * 3. 다익스트라의 결과 값이 주어진 간선의 가중치와 같다면 넘어감
         */
        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            int from = cur.from;
            int to = cur.to;
            int dist = cur.dist;

            int result = dijkstra(from, to);

            if (result > dist) {
                adj[from].add(new Element(to, dist));
                adj[to].add(new Element(from, dist));
                totalDist += dist;
            }

            if (result < dist) {
                totalDist = -1;
                break;
            }
        }

        System.out.println(totalDist);
    }

    private static int dijkstra(int start, int end) {
        dis = new int[N];
        Arrays.fill(dis, MAX_NUM);
        dis[start] = 0;

        pque = new PriorityQueue<>((x, y) -> (x.dist - y.dist));
        pque.add(new Element(start, 0));

        while (!pque.isEmpty()) {
            Element cur = pque.poll();
            int index = cur.index;
            int dist = cur.dist;

            if (dis[index] < dist) { // pque에 남은 잔재일 경우
                continue;
            }

            if (index == end) { // 도착지에 도달한 경우
                break;
            }

            for (Element next : adj[index]) {
                int nextIndex = next.index;
                int nextDist = next.dist;

                int newDist = dis[index] + nextDist;

                if (dis[nextIndex] > newDist) {
                    dis[nextIndex] = newDist;
                    pque.offer(new Element(nextIndex, newDist));
                }
            }
        }

        return dis[end];
    }
}