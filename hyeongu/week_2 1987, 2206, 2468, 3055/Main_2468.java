import java.io.*;
import java.util.*;

public class Main_2468 {
    static int N;
    static int[][] arr;
    static boolean[] height;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        height = new boolean[101];
        int answer = 1;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                height[arr[i][j]] = true;
            }
        }

        for(int h = 1; h < 101; h++){
            visit = new boolean[N][N];
            int area = 0;
            if(height[h]){
                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        if(!visit[i][j] && arr[i][j] > h){
                            dfs(i, j, h);
                            area++;
                        }
                    }
                }
            }
            answer = Math.max(answer, area);
        }
        System.out.println(answer);
    }

    static void dfs(int r, int c, int h){
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || arr[nr][nc] <= h){
                continue;
            }
            visit[nr][nc] = true;
            dfs(nr, nc, h);
        }
    }
}