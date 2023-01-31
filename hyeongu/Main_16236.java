import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16236 {
    static int N;
    static int[][] arr;
    static int[] shark = new int[3];
    static int move = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9){
                    shark[0] = i;
                    shark[1] = j;
                    shark[2] = 2;
                    arr[i][j] = 0;
                }
            }
        }

        int eat_cnt = 0;
        while(bfs(shark[0], shark[1])){
            if(++eat_cnt == shark[2]){
                eat_cnt = 0;
                shark[2]++;
            }
        }
        System.out.println(move);
    }

    static boolean bfs(int r, int c){
        boolean[][]visit = new boolean[N][N];
        Queue<int[]>q = new LinkedList<>();
        int min_r = N;
        int min_c = N;
        int min_cnt = N * N;

        visit[r][c] = true;
        q.offer(new int[] {r, c, 0});

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[2] + 1 > min_cnt){
                break;
            }

            for(int i = 0; i < 4; i++){
                int nr = now[0] + dx[i];
                int nc = now[1] + dy[i];
                int cnt = now[2] + 1;

                if(nr >= N || nc >= N || nr < 0 || nc < 0 || visit[nr][nc] || arr[nr][nc] > shark[2]){
                    continue;
                }
                shark[0] = nr;
                shark[1] = nc;

                if(arr[nr][nc] < shark[2] && arr[nr][nc] != 0){
                    if(min_r == nr){
                        min_c = Math.min(min_c, nc);
                    }
                    else if (min_r > nr){
                        min_r = nr;
                        min_c = nc;
                    }
                    min_cnt = cnt;
                }

                visit[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt});
            }
        }
        if(min_cnt < N * N){
            shark[0] = min_r;
            shark[1] = min_c;
            move += min_cnt;
            arr[min_r][min_c] = 0;
            return true;
        }
        return false;
    }
}