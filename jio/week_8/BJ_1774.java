import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1774 {
    static int N, M;
    static int[] P;
    static Node[] nodes;
    static class Node{ //노드 정보
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static PriorityQueue<Edge> pque;
    static class Edge implements Comparable<Edge>{ //간선 정보
        int s, e; //s: 시작점, e : 도착점
        double w; //w : 가중치(weight)

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[N+1]; //1번 노드부터 시작하기 위해 N+1
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        pque = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pque.offer(new Edge(s, e, 0)); //이미 주어진 통로는 weight를 0으로 설정
        }

        //모든 노드에서 다른 모든 노드로 가는 통로를 pque에 추가
        for (int i = 1; i < N+1; i++) {
            for (int j = i+1; j < N+1; j++) {
                pque.offer(new Edge(i, j, cal(i, j)));
            }
        }

        makeSet();

        int cnt = 0; //초기 통로 개수
        double min = 0;
        while(cnt != N-1){
            Edge cur = pque.poll();
            if(union(cur.s, cur.e)){ //아직 이어지지 않은 노드일 경우
                cnt ++;
                min += cur.w;
            }
        }
        System.out.printf("%.2f", min);
    }

    private static void makeSet(){
        P = new int[N+1];
        for (int i = 0; i < N + 1; i++) {
            P[i] = i;
        }
    }

    private static int find(int x){ //최상위 부모 탐색
        if(P[x] == x) return x;
        else return P[x] = find(P[x]);
    }

    private static boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py) return false; //부모가 같으면 false return, 다르면 union
        P[py] = px;
        return true;
    }

    private static double cal(int s, int e) { //거리 계산
        return Math.sqrt(Math.pow(nodes[s].x-nodes[e].x, 2) + Math.pow(nodes[s].y-nodes[e].y, 2));
    }
}