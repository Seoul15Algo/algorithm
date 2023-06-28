import java.util.*;
import java.io.*;

public class Main_16985 {
    static final int SIZE = 5;
    static final int DIRECTION = 4;
    static int[][][][] map;
    static int[][][] maze;
    static int[] p;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][DIRECTION][SIZE][SIZE];     // 주어진 맵을 회전시킨 경우를 모두 저장한 배열
        maze = new int[SIZE][SIZE][SIZE];               // 새로 만들어진 미로
        p = new int[SIZE];                              // 순열
        visit = new boolean[SIZE];
        answer = Integer.MAX_VALUE;

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                // 미리 회전을 시켜서 배열에 저장
                for(int k = 0; k < SIZE; k++){
                    int now = Integer.parseInt(st.nextToken());
                    map[i][0][j][k] = now;
                    map[i][1][k][SIZE - 1 - j] = now;
                    map[i][2][SIZE - 1 - j][SIZE - 1 - k] = now;
                    map[i][3][SIZE - 1 - k][j] = now;
                }
            }
        }
        perm(0);
        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        System.out.println(answer);
    }

    // 순열로 미로에 넣을 판의 순서를 정함
    public static void perm(int depth){
        if(depth == SIZE){
            makeMaze(0);
            return;
        }

        for(int i = 0; i < SIZE; i++){
            if(visit[i]){
                continue;
            }

            p[depth] = i;
            visit[i] = true;
            perm(depth + 1);
            visit[i] = false;
        }
    }

    // 순서가 정해진 판을 회전 시켜서 미로를 만듦
    public static void makeMaze(int depth){
        if(depth == SIZE){
            if(maze[0][0][0] == 1 && maze[SIZE - 1][SIZE - 1][SIZE - 1] == 1){
                escapeMaze();
            }
            return;
        }
        for(int i = 0; i < DIRECTION; i++){
            maze[depth] = map[p[depth]][i];
            makeMaze(depth + 1);
        }
    }

    // 만들어진 미로를 탈출하는 bfs
    public static void escapeMaze(){
        int[] dr = {1, -1, 0, 0, 0, 0};
        int[] dc = {0, 0, 1, -1, 0, 0};
        int[] dh = {0, 0, 0, 0, 1, -1};

        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visit = new boolean[SIZE][SIZE][SIZE];

        q.offer(new int[] {0, 0, 0, 0});
        visit[0][0][0] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();

            if(now[0] == SIZE - 1 && now[1] == SIZE - 1 && now[2] == SIZE - 1){
                answer = Math.min(answer, now[3]);
                return;
            }
            int nd = now[3] + 1;

            for(int i = 0; i < 6; i++){
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                int nh = now[2] + dh[i];

                if(!isRange(nr, nc, nh) || maze[nr][nc][nh] == 0 || visit[nr][nc][nh]){
                    continue;
                }
                visit[nr][nc][nh] = true;
                q.offer(new int[] {nr, nc, nh, nd});
            }
        }
    }

    public static boolean isRange(int r, int c, int h){
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE && h >= 0 && h < SIZE;
    }
}
