import java.util.*;
import java.io.*;

public class Main_1507 {
    public static class Edge implements Comparable<Edge>{
        int a, b, cost;

        public Edge(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[][] map = new int[N][N];

        // 도로 가중치의 오름차순으로 정렬된 pq
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        ArrayList<Integer>[] lists = new ArrayList[N];
        int answer = 0;

        for(int i = 0; i < N; i++){
            lists[i] = new ArrayList<>();
            Arrays.fill(map[i], Integer.MAX_VALUE);

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                // 양방향 도로를 모두 집어넣을 필요가 없으므로 하나만 집어넣음
                if(i > j){
                    pq.offer(new Edge(i, j, arr[i][j]));
                }
            }
        }

        // 가장 가중치가 짧은 도로부터 전체 지도를 완성하는 방법으로 진행
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            // 만약 처음 문제에서 주어진 가중치보다 짧은 가중치가 만들어 지는 경우 불가능한 경우로 생각 할 수 있다.
            if(map[now.a][now.b] < now.cost){
                System.out.println(-1);
                return;
            }

            // 도로가 오름차순으로 주어지기 때문에 앞으로 나올 도로로는 지금보다 짧은 도로를 만드는 것이 불가능
            // 현재까지 완성된 도로보다 한 번에 가는 도로가 짧다면 해당 도로는 한 번에 갈 수 있는 도로
            if(map[now.a][now.b] > now.cost){
                map[now.a][now.b] = now.cost;
                map[now.b][now.a] = now.cost;
                answer += now.cost;
            }

            // 새로 추가된 도로를 통해 움직일 수 있는 도시의 이동 시간을 갱신
            // a 와 연결된 새로운 도시
            for(int next : lists[now.a]){
                map[now.b][next] = Math.min(map[now.b][next], map[now.a][next] + now.cost);
                map[next][now.b] = map[now.b][next];
            }

            // b 와 연결된 새로운 도시
            for(int next : lists[now.b]){
                map[now.a][next] = Math.min(map[now.a][next], map[now.b][next] + now.cost);
                map[next][now.a] = map[now.a][next];
            }

            // 새로 이어진 도로를 추가
            lists[now.a].add(now.b);
            lists[now.b].add(now.a);
        }

        System.out.println(answer);
    }
}