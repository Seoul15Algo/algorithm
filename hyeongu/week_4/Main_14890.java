import java.util.*;
import java.io.*;

public class Main_14890 {
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static int[][] arr;
    static int N, L;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        int answer = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            if(check(0, i, 0)){
                answer++;
            }
            if(check(i, 0, 1)){
                answer++;
            }
        }
        System.out.println(answer);

    }

    static boolean check(int r, int c, int dir){
        int last = arr[r][c];
        int cnt = 1;
        int nr = r;
        int nc = c;

        for(int i = 1; i < N; i++){
            nr += dr[dir];
            nc += dc[dir];

            if(last == arr[nr][nc]){
                cnt++;
                continue;
            }
            if(Math.abs(last - arr[nr][nc]) > 1){
                return false;
            }
            if(last < arr[nr][nc]){
                if(cnt >= L){
                    last = arr[nr][nc];
                    cnt = 1;
                    continue;
                }
                return false;
            }
            if(nextCheck(nr, nc, dir, arr[nr][nc])){
                last = arr[nr][nc];
                cnt = 1 - L;
                continue;
            }
            return false;
        }
        return true;
    }

    static boolean nextCheck(int r, int c, int dir, int value){
        for(int i = 1; i < L; i++){
            r += dr[dir];
            c += dc[dir];

            if(r >= N || c >= N || value != arr[r][c]) return false;
        }
        return true;
    }
}
