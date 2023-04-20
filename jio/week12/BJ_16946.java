package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16946 {
    static int N, M;
    static int[][] grid, roundGrid, countGrid;
    static boolean[][] visited;
    static int[] countByRound;
    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        roundGrid = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = chars[j] - '0';
            }
        }

        visited = new boolean[N][M];
        countByRound = new int[N*M+1]; //bfs를 수행할 수 있는 round의 최대 범위 만큼 배열 생성
        int round = 1; //bfs를 수행한 라운드
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
            {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    countByRound[round] = bfs(i, j, round); //해당 라운드에 방문(인접)한 칸의 개수
                    round++;
                }
            }
        }

        fillCountGrid();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(countGrid[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void fillCountGrid() { //벽을 부수고 그 위치에서 이동할 수 있는 칸의 개수를 저장할 countGrid 배열을 채워준다.
        countGrid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(grid[i][j] == 1){
                    List<Integer> alreadyVisitedRound = new ArrayList<>(); //방문했던 라운드를 관리
                    for (int d = 0; d < 4; d++) { //4방을 보면서 인접합 길의 집합을 찾는다.
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(!inRange(nx, ny)){ //격자를 벗어나는 경우
                            continue;
                        }
                        if(grid[nx][ny] == 1){ //벽인 경우
                            continue;
                        }
                        int curRound = roundGrid[nx][ny]; //현재 이동하려는 칸이 몇 번째 라운드에 방문했는지
                        if (alreadyVisitedRound.contains(curRound)) { //이미 방문한 라운드라면
                            continue;
                        }
                        alreadyVisitedRound.add(curRound);
                        countGrid[i][j] += countByRound[curRound]; //해당 라운드에 방문한 칸의 개수를 더해줌
                    }
                    countGrid[i][j]++; //자기자신 추가
                    countGrid[i][j] %= 10;
                }
            }
        }
    }

    private static int bfs(int r, int c, int round) { //BFS로 인접한 길들이 있는 구역을 찾고 몇 번째 라운드에 방문한 곳인지 저장
        visited[r][c] = true;
        roundGrid[r][c] = round;
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(r, c));
        int count = 1; //인접한 칸의 개수
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!inRange(nx, ny)) { //격자 범위 내가 아닐 경우
                    continue;
                }
                if (grid[nx][ny] != 0) { //벽일 경우
                    continue;
                }
                if (visited[nx][ny]) { //방문했던 경우
                    continue;
                }
                count++;
                visited[nx][ny] = true;
                roundGrid[nx][ny] = round; //몇 번째 라운드에 방문했는지를 저장
                que.offer(new Pair(nx, ny));
            }
        }
        return count;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
