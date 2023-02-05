import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987 {
    static int R, C;
    static int[][] arr;
    static boolean[][] visit;
    static boolean[] number = new boolean[26];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        visit = new boolean[R][C];

        for(int i = 0; i < R; i++){
            String str = br.readLine();
            for(int j = 0; j < C; j++){
                char c = str.charAt(j);
                arr[i][j] = c - 'A';
            }
        }

        number[arr[0][0]] = true;
        visit[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int r, int c, int depth){
        boolean flag = false;

        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || number[arr[nr][nc]]){
                continue;
            }
            flag = true;
            number[arr[nr][nc]] = true;
            visit[nr][nc] = true;
            dfs(nr, nc, depth + 1);
            number[arr[nr][nc]] = false;
            visit[nr][nc] = false;
        }

        if(!flag){
            answer = Math.max(answer, depth);
        }
    }
}
