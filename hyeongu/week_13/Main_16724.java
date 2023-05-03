import java.util.*;
import java.io.*;

public class Main_16724 {
    static int N, M, answer;
    static int[][] arr;
    static boolean[][] visit;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Character, Integer> hm = new HashMap<>();
        hm.put('U', 0);
        hm.put('D', 1);
        hm.put('L', 2);
        hm.put('R', 3);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                arr[i][j] = hm.get(str.charAt(j));
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visit[i][j]){
                    dfs(i, j);
                }
            }
        }
        System.out.println(answer);
    }

    static int dfs(int r, int c){
        int nr = r + dr[arr[r][c]];
        int nc = c + dc[arr[r][c]];
        arr[r][c] = 0;
        visit[r][c] = true;

        if(!visit[nr][nc]){
            return arr[r][c] = dfs(nr, nc);
        }
        if(arr[nr][nc] == 0){
            arr[nr][nc] = ++answer;
            return arr[r][c] = answer;
        }
        return arr[r][c] = arr[nr][nc];
    }
}