package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6087 {
    static int W, H, startX, startY, endX, endY, minMirrorCnt;
    static char[][] grid;
    static int[][] mirrorCountGrid;
    static Queue<Point> que;
    static class Point{
        int x, y;
        int dir;
        int mirrorCount;

        public Point(int x, int y, int dir, int mirrorCount) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrorCount = mirrorCount;
        }
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        grid = new char[H][W];
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        boolean isFirst = true;
        for (int i = 0; i < H; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                grid[i][j] = chars[j];
                if(grid[i][j] == 'C' && isFirst){ //두 번째 C의 위치를 시작 위치로 지정
                    startX = i;
                    startY = j;
                    isFirst = false;
                }
                if(grid[i][j] == 'C' && !isFirst){ //두 번째 C의 위치를 시작 위치로 지정
                    endX = i;
                    endY = j;
                }
            }
        }

        minMirrorCnt = Integer.MAX_VALUE;
        bfs();
        System.out.println(mirrorCountGrid[endX][endY]);
    }

    private static void bfs() {
        //1. 각 칸까지 도달하는 사용한 거울의 최소 개수를 저장할 mirrorCountGrid 생성 및 초기화
        mirrorCountGrid = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                mirrorCountGrid[i][j] = Integer.MAX_VALUE;
            }
        }

        //2. C의 4방을 돌며 que에 bfs를 시작할 지점(Point)들을 방향 정보를 담아 넣어줌
        que = new ArrayDeque<>();
        mirrorCountGrid[startX][startY] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = startX + dx[d];
            int ny = startY + dy[d];
            if(canGo(nx, ny)){
                mirrorCountGrid[nx][ny] = 0;
                que.offer(new Point(nx, ny, d, 0));
            }
        }

        //3. bfs 시작
        while (!que.isEmpty()) {
            Point cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir; //현재 방향
            int mirrorCount = cur.mirrorCount; //현재까지 사용한 거울의 개수
            for (int d = 0; d < 4; d++) {
                if (d == (dir + 2) % 4) { //현재 방향에서 갈 수 없는 방향인 경우 고려
                    continue;
                }
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(!canGo(nx, ny)){ //격자내 존재하지 않거나 벽인 경우
                    continue;
                }
                if(dir != d && mirrorCount + 1 <= mirrorCountGrid[nx][ny]){ //방향 전환이 이루어진 경우
                    mirrorCountGrid[nx][ny] = mirrorCount + 1;  //사용한 거울 개수 증가
                    que.offer(new Point(nx, ny, d, mirrorCount + 1));
                    continue;
                }
                //현재 방향이 같고 거울의 개수가 같으면 여태 온 경로가 달라도 이후 상황은 동일하므로 이를 제외하여 가지치기 수행
                if(dir == d && mirrorCount < mirrorCountGrid[nx][ny]){ //부등식에 등호를 빼주어 pruning
                    mirrorCountGrid[nx][ny] = mirrorCount;
                    que.offer(new Point(nx, ny, d, mirrorCount));
                }
            }
        }
    }

    private static boolean inRange(int x, int y){
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    private static boolean canGo(int x, int y){ //격자 내에 존재하고 벽이 아니며 방문한 적이 없다면 이동
        if(!inRange(x, y) || grid[x][y] == '*') {
            return false;
        }
        return true;
    }
}