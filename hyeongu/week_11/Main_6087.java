import java.util.*;
import java.io.*;

public class Main_6087 {
    static int H, W;
    static int[][] arr, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Node implements Comparable<Node>{
        // x좌표, y좌표, 거울의 갯수, 진행방향
        int r, c, cnt, dir;

        public Node(int r, int c, int cnt, int dir){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][W];
        C = new int[2][2];
        int tmp = 0;
        for(int i = 0; i < H; i++){
            String str = br.readLine();
            for(int j = 0; j < W; j++){
                char c = str.charAt(j);

                if(c == 'C'){
                    C[tmp][0] = i;
                    C[tmp++][1] = j;
                    continue;
                }
                if(c == '*'){
                    arr[i][j] = -1;
                }
            }
        }

        System.out.println(bfs(C[0][0], C[0][1]));
    }

    static int bfs(int r, int c){
        PriorityQueue<Node>pq = new PriorityQueue<>();
        int[][][] visit = new int[H][W][4];     // 해당 좌표에 대해서 모든 방향으로 들어오는 경우의 최솟값을 저장하는 배열
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }
        }
        for(int i = 0; i < 4; i++){
            pq.offer(new Node(r, c, 0, i));
            visit[r][c][i] = 0;
        }

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.cnt >= result){
                break;
            }

            for(int i = 0; i < 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                // 현재 진행방향과 다음 진행방향이 다른 경우 cnt + 1
                int cnt = (i == now.dir) ? now.cnt : now.cnt+1;

                if(!isRange(nr, nc) || arr[nr][nc] == -1 || visit[nr][nc][i] <= cnt){
                    continue;
                }

                if(nr == C[1][0] && nc == C[1][1]){
                    result = Math.min(cnt, result);
                    continue;
                }
                visit[nr][nc][i] = cnt;
                pq.offer(new Node(nr, nc, cnt, i));
            }
        }
        return result;
    }

    static boolean isRange(int r, int c){
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}