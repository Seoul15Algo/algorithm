import java.io.*;
import java.util.*;

public class Main_2206 {
    static int[][] arr;
    static int[][] visit;
    static int R, C;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                char c = str.charAt(j);
                arr[i][j] = c -'0';
            }
        }
        if(R == 1 && C == 1){
            System.out.println(1);
            return;
        }
        System.out.println(bfs(0, 0));
    }

    static int bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        visit = new int[R][C];
        q.offer(new int[] {r, c, 0, 1});
        visit[r][c] = 1;
        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i= 0; i < 4; i++){
                int nr = now[0] + dx[i];
                int nc = now[1] + dy[i];
                int nd = now[3] + 1;
                if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] > 2){
                    continue;
                }
                if(nr == R-1 && nc == C-1){
                    return nd;
                }
                if(arr[nr][nc] == 1 && now[2] == 0){
                    q.offer(new int[] {nr, nc, 1, nd});
                    visit[nr][nc] = 2;
                    continue;
                }
                if(arr[nr][nc] == 0 && now[2] == 0){
                    if(visit[nr][nc] % 2 == 0){
                        q.offer(new int[] {nr, nc, 0, nd});
                        visit[nr][nc]++;
                        continue;
                    }
                }
                if(arr[nr][nc] == 0 && now[2] == 1){
                    if(visit[nr][nc] < 2){
                        q.offer(new int[] {nr, nc, 1, nd});
                        visit[nr][nc] += 2;
                    }
                }
            }
        }
        return -1;
    }
}
