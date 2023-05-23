import java.util.*;
import java.io.*;

public class Main_16954 {
    static final int N = 8;
    static int[][] arr;
    static int[] height;
    static int[] dr = {0, -1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[N * 2][N];		// 범위를 벗어나는 경우를 편하게 하기 위해서 N * 2로 생성
        height = new int[N];			// 해당 열에서 가장 높은곳에 있는 벽의 위치

        /* 
         * 아래에서 위로 올라가야하기 때문에 인덱스를 뒤집어서 배열에 저장
         * 
         * [40, 41, 42, 43, 44]
         * [30, 31, 32, 33, 34]
         * [20, 21, 22, 23, 24]
         * [10, 11, 12, 13, 14]
         * [00, 01, 02, 03, 04]
         * 
         * 00 -> 출발지, 44-> 도착지
         */
        for(int i = N - 1; i >= 0; i--){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                if(str.charAt(j) == '#'){
                    arr[i][j] = 1;
                    height[j] = Math.max(height[j], i);		// 해당 열에서 가장 높은 벽의 위치를 저장
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs(){
        Queue<int[]>q = new LinkedList<>();
        boolean[][][] visit = new boolean[N][N][N];
        q.offer(new int[] {0, 0, 0});
        visit[0][0][0] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            
            // 1초에 1칸씩 벽이 내려오게 되는데
            // 현재 높이 >= (해당 열의 가장 높은 벽 - 시간)이 되는 경우
            // 해당 열에서 현재 위치보다 위에 벽이 없다고 판단할 수 있음
            // 따라서 맨 꼭대기까지 올라갈 수 있게되고 도착지에 도착할 수 있음
            if(now[0] >= height[now[1]] - now[2]){
                return 1;
            }

            int nd = now[2] + 1;
            for(int i = 0; i < 9; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                // 벽을 직접 움직이지 않고 시간 변수를 이용해 시간만큼 값을 더해서 확인
                if(!isRange(nr, nc) || visit[nr][nc][nd] || arr[nr + now[2]][nc] == 1 || arr[nr + nd][nc] == 1){
                    continue;
                }

                visit[nr][nc][nd] = true;
                q.offer(new int[] {nr, nc, nd});
            }
        }
        return 0;
    }

    static boolean isRange(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}