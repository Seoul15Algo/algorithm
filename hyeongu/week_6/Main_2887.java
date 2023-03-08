import java.io.*;
import java.util.*;

public class Main_2887 {
    static int N, answer;
    static Node[] nodes;
    static ArrayList<Edge> edges;
    static boolean[] visited;
    static int[] p;

    static class Node {
        int num, x, y, z;

        public Node(int num, int x, int y, int z) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a, b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        visited = new boolean[N];
        answer = 0;
        edges = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(i + 1, x, y, z);
        }

        // x좌표 오름차순 정렬 후 사이 간선 추가
        Arrays.sort(nodes, (o1, o2) -> o1.x - o2.x);

        for(int i = 0; i < N - 1; i++) {
            int v = Math.abs(nodes[i].x - nodes[i + 1].x);
            edges.add(new Edge(nodes[i].num, nodes[i + 1].num, v));
        }

        // y좌표 오름차순 정렬 후 사이 간선 추가
        Arrays.sort(nodes, (o1, o2) -> o1.y - o2.y);

        for(int i = 0; i < N - 1; i++) {
            int v = Math.abs(nodes[i].y - nodes[i + 1].y);
            edges.add(new Edge(nodes[i].num, nodes[i + 1].num, v));
        }

        // z좌표 오름차순 정렬 후 사이 간선 추가
        Arrays.sort(nodes, (o1, o2) -> o1.z - o2.z);

        for(int i = 0; i < N - 1; i++) {
            int v = Math.abs(nodes[i].z - nodes[i + 1].z);
            edges.add(new Edge(nodes[i].num, nodes[i + 1].num, v));
        }

        // x, y, z 간선 모두 정렬
        // 각각의 좌표에서 간선들은 모든 노드사이의 거리의 집합이므로 최소신장트리가 보장된다
        // 그러므로 모든 좌표에서의 간선들을 합한 집합 역시 값이 중복되더라도 최소신장트리가 보장된다.
        Collections.sort(edges);

        // makeSet
        p = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            p[i] = i;
        }

        // 크루스칼을 이용하여 최소신장트리 생성
        for(Edge now : edges) {
            if(find(now.a) != find(now.b)) {
                union(now.a, now.b);
                answer += now.cost;
            }
        }
        System.out.println(answer);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x < y) {
            p[x] = y;
            return;
        }
        p[y] = x;
    }

    static int find(int x) {
        if(x == p[x]) return x;
        return find(p[x]);
    }
}