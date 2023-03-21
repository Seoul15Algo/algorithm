import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_14621 {
    static int N, M;
    static int[] P;
    static Node[] nodes;
    static class Node{ //노드 정보
        String type;

        public Node(String type) {
            this.type = type;
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
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nodes[i+1] = new Node(temp[i]);
        }

        pque = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pque.offer(new Edge(s, e, w));
        }

        makeSet();

        int cnt = 0; //초기 통로 개수
        int min = 0;
        while(cnt != N-1){
            Edge cur = pque.poll();
            if(cur == null){ // 모든 노드를 연결할 수 없는 경우 break
                break;
            }
            String sType = nodes[cur.s].type;
            String eType = nodes[cur.e].type;
            if(sType.equals(eType)) continue; //같은 type이라면 continue
            if(union(cur.s, cur.e)){ //아직 이어지지 않은 노드일 경우
                cnt ++;
                min += cur.w;
            }
        }

        for (int i = 1; i < N + 1; i++) { //모든 노드의 P[i] 값을 최상위 부모로 갱신
            find(i);
        }

        int ans = min;
        for (int i = 1; i < N; i++) { //모든 노들르 순회하며 최상위 부모가 다른 경우가 있는지 확인
            if(P[i] != P[i+1]){
                ans = -1;
                break;
            }
        }
        System.out.println(ans);
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
}