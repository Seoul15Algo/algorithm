import java.io.*;
import java.util.*;

// 크루스칼 알고리즘을 반대로 이용했습니다
// 가장 가중치가 큰 간선을 선택해서 출발지와 목적지를 연결
public class Main_1939 {
    static int[] p;
    static class Edge implements Comparable<Edge>{
        int A, B, C;

        public Edge(int A, int B, int C){
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public int compareTo(Edge o) {
            return o.C - this.C;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;
        makeset(N);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            pq.add(new Edge(A, B, C));
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()) - 1;
        int B = Integer.parseInt(st.nextToken()) - 1;

        // pq에서 가중치가 가장 큰 간선을 뽑아 연결
        // A와 B가 연결될 때 까지 간선을 뽑아서 연결
        while(find(A) != find(B)){
            Edge now = pq.poll();

            union(now.A, now.B);
            answer = now.C;
        }
        System.out.println(answer);
    }

    // 아래부터는 유니온-파인드
    public static void makeset(int N){
        p = new int[N];
        for(int i = 0; i < N; i++){
            p[i] = i;
        }
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            p[x] = y;
        }
    }

    public static int find(int x){
        if(p[x] == x){
            return x;
        }
        return p[x] = find(p[x]);
    }
}