import java.util.*;
import java.io.*;

// 직접 논에서 우물을 파거나 물을 대고있는 다른 논으로부터 물을 끌어와야 한다
// 우물을 또 다른 하나의 노드로 보고 총 N + 1개의 노드로 MST를 만들게 되면 결과적으로 모든 논과 우물을 연결 시킬 수 있다.
// 따라서 크루스칼을 이용해서 최소 비용을 구한다
// 간선의 개수가 300 * 299 이므로 프림 알고리즘을 사용하는 것이 더 효율적이지만 귀찮았습니다

public class Main_1368 {
    static int[] p;
    static class Node implements Comparable<Node>{
        int A, B, cost;

        public Node(int A, int B, int cost){
            this.A = A;
            this.B = B;
            this.cost = cost;
        }
        // 크루스칼을 쓰기 위해 비용으로 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        long answer = 0L;
        makeSet(N);

        // 0번 노드 : 우물
        // 1 ~ N번 노드 : 논
        // 우물과 각각의 논을 연결하는 간선을 추가
        for(int i = 1; i <= N; i++){
            pq.offer(new Node(0, i, Integer.parseInt(br.readLine())));
        }

        // 논과 논 사이의 간선을 추가
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                int now = Integer.parseInt(st.nextToken());
                if(j > i){
                    pq.offer(new Node(i, j, now));
                }
            }
        }

        // 유니온파인드를 이용해 N개의 간선이 연결될 때 까지 간선을 추가
        int cnt = 1;
        while(cnt <= N){
            Node now = pq.poll();
            if(union(now.A, now.B)){
                answer += now.cost;
                cnt++;
            }
        }
        System.out.println(answer);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y){
            return false;
        }
        if(x > y){
            p[y] = x;
        }else{
            p[x] = y;
        }

        return true;
    }

    static int find(int x){
        if(x == p[x]){
            return x;
        }
        return p[x] = find(p[x]);
    }

    static void makeSet(int N){
        p = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            p[i] = i;
        }
    }
}
