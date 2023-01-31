import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;
    static int[][] arr;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move(r, c, d);
        System.out.println(answer);
    }
/*
    static int move(int r, int c, int d){
        if(!visit[r][c]) {
            answer++;
            visit[r][c] = true;
        }

        for(int i = 1; i <= 4; i++){
            int nd = (d + 4 - i) % 4;
            int nr = r + dx[nd];
            int nc = c + dy[nd];

            if(arr[nr][nc] == 1 || visit[nr][nc] ){
                continue;
            }

            return move(nr, nc, nd);
        }

        int nr = r + dx[(d + 2) % 4];
        int nc = c + dy[(d + 2) % 4];
        if(arr[nr][nc] == 1){
            return 0;
        }
        return move(nr, nc, d);
    }
*/
    static void move(int r, int c, int d){
        if(!visit[r][c]) {
            answer++;
            visit[r][c] = true;
        }

        for(int i = 1; i <= 4; i++){
            int nd = (d + 4 - i) % 4;
            int nr = r + dx[nd];
            int nc = c + dy[nd];

            if(arr[nr][nc] == 1 || visit[nr][nc] ){
                continue;
            }
            move(nr, nc, nd);
            return;
        }

        int nr = r + dx[(d + 2) % 4];
        int nc = c + dy[(d + 2) % 4];
        if(arr[nr][nc] == 1){
            return;
        }
        move(nr, nc, d);
    }
}