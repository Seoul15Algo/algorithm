import java.util.*;
import java.io.*;

public class Main_1774 {
    static int[] p;
    static int N, M;
    static int[][] arr;

    static class Edge implements Comparable<Edge>{
        int u, v;
        double cost;
        public Edge(int u, int v, double cost){
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return this.cost < e.cost ? -1 : 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Edge> list = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][2];
        double answer = 0.0;
        int cnt = 0;
        makeSet();

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 이미 연결된 통로 중에서 사이클이 생기지 않는 간선을 모두 추가
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a, b)){
                cnt++;
            }
        }

        // 가능한 모든 간선 중 거리가 가장 짧은 간선부터 크루스칼
        for(int i = 1; i <= N; i++){
            for(int j = i + 1; j <= N; j++){
                list.add(new Edge(i, j, len(i, j)));
            }
        }

        Collections.sort(list);

        int index = 0;

        // N개의 노드에 싸이클이 없는 N-1개의 간선이 만들어지면 MST 완성
        while(cnt < N - 1){
            Edge now = list.get(index++);

            if(union(now.u, now.v)){
                cnt++;
                answer += now.cost;
            }
        }
        System.out.printf("%.2f", answer);
    }

    // 두 점 사이의 거리
    static double len(int a, int b){
        return Math.sqrt(Math.pow(arr[a][0] - arr[b][0], 2) + Math.pow(arr[a][1] - arr[b][1], 2));
    }

    // 유니온 파인드
    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x > y){
            int tmp = x;
            x = y;
            y = tmp;
        }

        if(x == y)return false;

        p[y] = x;
        return true;
    }
    static void makeSet() {
        p = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            p[i] = i;
        }
    }
    static int find(int x) {
        if(x == p[x]) return x;

        return p[x] = find(p[x]);
    }
}