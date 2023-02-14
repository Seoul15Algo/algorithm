import java.io.*;
import java.util.*;

public class Main_3055 {
    static int[][] arr;
    static boolean[][] spread;
    static int R, C;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] hog = new int[2];
        arr = new int[R][C];
        spread = new boolean[R][C];
        Queue<int[]> water = new LinkedList<>();
        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                char c = str.charAt(j);
                switch (c){
                    case 'D':
                        arr[i][j] = -2;
                        break;
                    case '*':
                        arr[i][j] = 0;
                        water.offer(new int[] {i, j});
                        spread[i][j] = true;
                        break;
                    case 'X':
                        arr[i][j] = -1;
                        break;
                    case 'S':
                        hog = new int[] {i, j};
                        break;
                    default:
                        arr[i][j] = R * C;
                }
            }
        }

        spreadWater(water);

        int answer = bfs(hog[0], hog[1]);
        if(answer < 0){
            System.out.println("KAKTUS");
            return;
        }
        System.out.println(answer);
    }

    static int bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];
        q.offer(new int[] {r, c, 0});
        visit[r][c] =true;

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i= 0; i < 4; i++){
                int nr = now[0] + dx[i];
                int nc = now[1] + dy[i];
                int nd = now[2] + 1;

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc]){
                    continue;
                }
                if(arr[nr][nc] == -2){
                    return nd;
                }
                if(arr[nr][nc] > nd){
                    q.offer(new int[] {nr, nc, nd});
                    visit[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    static void spreadWater(Queue<int[]> q){
        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i= 0; i < 4; i++){
                int nr = now[0] + dx[i];
                int nc = now[1] + dy[i];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || spread[nr][nc] || arr[nr][nc] < 0){
                    continue;
                }

                q.offer(new int[] {nr, nc});
                spread[nr][nc] = true;
                arr[nr][nc] = arr[now[0]][now[1]] + 1;
            }
        }
    }
}
