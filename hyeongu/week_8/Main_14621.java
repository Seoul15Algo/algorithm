import java.io.*;
import java.util.*;

public class Main_14621 {
    static int N, M;
    static int[] p, r;
    static boolean[] isMan;
    static ArrayList<Edge> al;
    static class Edge implements Comparable<Edge>{
        int u, v, d;

        public Edge(int u, int v, int d){
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Edge e){
            return this.d - e.d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isMan = new boolean[N + 1];
        al = new ArrayList<>();
        makeSet();

        int answer = 0;
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            // 남초인지 여초인지 boolean 배열로 저장
            if(st.nextToken().equals("M")){
                isMan[i] = true;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 주어진 경로가 남초와 여초가 연결된 경로일 경우에만 경로 추가
            if(isMan[u] != isMan[v]){
                al.add(new Edge(u, v, d));
            }
        }

        // 만들 수 있는 경로만 저장했기 때문에 저장된 경로로 크루스칼 진행
        // 이때 싸이클을 제외한 모든 경로를 추가하더라도 간선의 갯수가 N - 1보다 작을 경우 -> 모든 노드가 연결되어있지 않음
        Collections.sort(al);

        for(int i = 0; i < al.size(); i++){
            Edge now = al.get(i);

            if(union(now.u, now.v)){
                answer += now.d;
                cnt++;
            }
            if(cnt == N - 1){
                break;
            }
        }

        if(cnt < N - 1){
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return false;

        if(r[x] < r[y]){
            r[y] += r[x];
            p[x] = y;
            return true;
        }

        r[x] += r[y];
        p[y] = x;
        return true;
    }
    static int find(int x){
        if(x == p[x]){
            return x;
        }
        return p[x] = find(p[x]);
    }

    static void makeSet(){
        p = new int[N + 1];
        r = new int[N + 1];

        for(int i = 1; i <= N; i++){
            p[i] = i;
            r[i] = 1;
        }
    }
}