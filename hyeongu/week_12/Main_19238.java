import java.util.*;
import java.io.*;

public class Main_19238 {
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static int N, M, fuel;
    static int[][] arr, goals;
    static Node taxi;
    static class Node implements Comparable<Node>{
        int r, c, cost;

        public Node(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        // 문제의 조건에 맞는 승객을 먼저 태우기 위한 정렬 기준
        // 우선순위 : 거리 -> 행 -> 열
        @Override
        public int compareTo(Node o) {
            if(this.cost == o.cost){
                return this.r == o.r ? this.c - o.c : this.r - o.r;
            }
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        goals = new int[M + 1][2];

        for(int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                // 벽을 -1로 초기화
                arr[i][j] = -Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        // 택시의 위치를 Node로 저장
        // 이후 승객을 찾을 때 택시 Node부터 시작
        taxi = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            // 승객을 구분하기 위해 배열에 승객의 인덱스를 저장
            int start_r = Integer.parseInt(st.nextToken()) - 1;
            int start_c = Integer.parseInt(st.nextToken()) - 1;
            arr[start_r][start_c] = i;

            // 해당 승객의 도착지는 배열에 저장
            goals[i][0] = Integer.parseInt(st.nextToken()) - 1;
            goals[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        int cnt = 0;
        // 연료가 떨어지거나 승객을 모두 태우기 전까지 진행
        while(fuel > 0 && cnt < M){
            Node now = findPassenger();
            // 승객은 남아있지만 태울 수 없는 경우
            if(now == null){
                System.out.println(-1);
                return;
            }
            fuel -= now.cost;
            int result = findGoal(now.r, now.c);

            // 연료가 부족하거나 도착지에 도달하지 못한 경우
            if(result < 0){
                System.out.println(-1);
                return;
            }
            fuel += result;
            cnt++;
        }
        System.out.println(fuel);
    }

    // BFS + 우선순위 큐를 이용해서 승객을 탐색
    static Node findPassenger(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][N];
        visit[taxi.r][taxi.c] = true;
        pq.offer(taxi);

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(arr[now.r][now.c] > 0){
                return now;
            }
            if(now.cost == fuel){
                continue;
            }

            for(int i = 0; i < 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if(!isRange(nr, nc) || visit[nr][nc] || arr[nr][nc] < 0){
                    continue;
                }

                visit[nr][nc] = true;
                pq.offer(new Node(nr, nc, now.cost + 1));
            }
        }
        return null;
    }

    // BFS + 큐를 이용해 승객에 맞는 도착지를 탐색
    static int findGoal(int r, int c){
        Queue<int[] >q = new LinkedList<>();
        q.offer(new int[] {r, c, 0});
        boolean[][] visit = new boolean[N][N];
        visit[r][c] = true;
        int[] goal = goals[arr[r][c]];
        arr[r][c] = 0;

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[2] == fuel){
                return -1;
            }

            for(int i = 0; i < 4; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if(!isRange(nr, nc) || visit[nr][nc] || arr[nr][nc] < 0){
                    continue;
                }

                if(nr == goal[0] && nc == goal[1]){
                    taxi.r = nr;
                    taxi.c = nc;
                    return now[2] + 1;
                }
                visit[nr][nc] = true;
                q.offer(new int[] {nr, nc, now[2] + 1});
            }
        }
        return -1;
    }
    static boolean isRange(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}