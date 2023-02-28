import java.util.*;
import java.io.*;

public class Main_12100 {
    static int[][] map;
    static int answer;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        answer = 0;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(copy(map), 0);
        System.out.println(answer);
    }

    static void dfs(int[][] arr, int depth){
        if(depth == 5){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    answer = Math.max(answer, arr[i][j]);
                }
            }
            return;
        }

        up(copy(arr), depth);
        down(copy(arr), depth);
        left(copy(arr), depth);
        right(copy(arr), depth);
    }

    static void right(int[][] arr, int depth) {
        Queue<Integer>q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            for(int j = N - 1; j >= 0; j--){
                if(arr[i][j] > 0){
                    q.offer(arr[i][j]);
                }
            }
            int cnt = N-1;

            while(!q.isEmpty()){
                int now = q.poll();
                if(!q.isEmpty() && q.peek() == now){
                    now += q.poll();
                }
                arr[i][cnt--] = now;
            }

            for(int j = cnt; j >= 0; j--){
                arr[i][j] = 0;
            }
        }
        dfs(arr, depth + 1);
    }

    static void left(int[][] arr, int depth) {
        Queue<Integer>q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j] > 0){
                    q.offer(arr[i][j]);
                }
            }
            int cnt = 0;

            while(!q.isEmpty()){
                int now = q.poll();
                if(!q.isEmpty() && q.peek() == now){
                    now += q.poll();
                }
                arr[i][cnt++] = now;
            }

            for(int j = cnt; j < N; j++){
                arr[i][j] = 0;
            }
        }
        dfs(arr, depth + 1);
    }

    static void down(int[][] arr, int depth) {
        Queue<Integer>q = new LinkedList<>();

        for(int j = 0; j < N; j++){
            for(int i = N - 1; i >= 0; i--){
                if(arr[i][j] > 0){
                    q.offer(arr[i][j]);
                }
            }
            int cnt = N-1;

            while(!q.isEmpty()){
                int now = q.poll();
                if(!q.isEmpty() && q.peek() == now){
                    now += q.poll();
                }
                arr[cnt--][j] = now;
            }

            for(int i = cnt; i >= 0; i--){
                arr[i][j] = 0;
            }
        }
        dfs(arr, depth + 1);
    }

    static void up(int[][] arr, int depth){
        Queue<Integer>q = new LinkedList<>();

        for(int j = 0; j < N; j++){
            for(int i = 0; i < N; i++){
                if(arr[i][j] > 0){
                    q.offer(arr[i][j]);
                }
            }
            int cnt = 0;

            while(!q.isEmpty()){
                int now = q.poll();
                if(!q.isEmpty() && q.peek() == now){
                    now += q.poll();
                }
                arr[cnt++][j] = now;
            }

            for(int i = cnt; i < N; i++){
                arr[i][j] = 0;
            }
        }
        dfs(arr, depth + 1);
    }
    static int[][] copy(int[][] arr){
        int[][] clone = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                clone[i][j] = arr[i][j];
            }
        }
        return clone;
    }
}