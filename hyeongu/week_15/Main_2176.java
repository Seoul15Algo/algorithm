import java.util.*;
import java.io.*;
public class Main_2176 {
    static int N;
    static int[][] arr;
    static List<Node> list;
    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node>{
        int index;              // 노드 번호
        List<Node> link;        // 인접한 노드
        int distance;           // 2번째 노드까지의 거리

        public Node(int index) {
            this.index = index;
            link = new ArrayList<>();
            distance = INF;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];

        list = new ArrayList<>();
        list.add(new Node(0));      // 인덱스를 1부터 사용하기 위한 더미데이터

        for(int i = 1; i <= N; i++) {
            list.add(new Node(i));
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(u).link.add(list.get(v));
            list.get(v).link.add(list.get(u));
            arr[u][v] = cost;
            arr[v][u] = cost;
        }
        dijkstra();

        System.out.println(dp());
    }

    // 도착지부터 시작하여 연결된 노드로 이동하면서 경우의 수를 구해준다
    // 정렬을 하게되면 거리가 0인 도착지가 맨 앞으로 오게 되고 처음에 넣었던 더미데이터는 맨 마지막으로 가게 된다
    // 따라서 1 부터 size - 1 까지가 나머지 노드
    static int dp(){
        int[] dp = new int[N + 1];
        Collections.sort(list);

        // 도착지와 연결된 노드 => 경우의 수 1
        for(Node tmp : list.get(0).link){
            dp[tmp.index] = 1;
        }

        // 자신과 연결된 노드 중에서 자신보다 거리가 먼 노드에게 경우의 수 전달
        // 거리순으로 정렬을 했기 때문에 자신의 경우의수가 나중에 바뀌는 경우는 없음
        for(int i = 1; i < list.size() - 1; i++){
            Node now = list.get(i);
            System.out.println(now.distance);
            for(Node next : now.link){
                if(next.distance > now.distance){
                    dp[next.index] += dp[now.index];
                }
            }
        }
        // 출발지의 경우의 수
        return dp[1];
    }


    // 도착지까지의 거리를 구해야하기 때문에 도착지를 기준으로 다익스트라
    // 해당 노드부터 도착지까지의 거리는 노드.distance에 저장
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        list.get(2).distance = 0;
        pq.offer(list.get(2));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node next : now.link){
                if(next.distance > now.distance + arr[now.index][next.index]){
                    next.distance = now.distance + arr[now.index][next.index];
                    pq.offer(next);
                }
            }
        }
    }

}