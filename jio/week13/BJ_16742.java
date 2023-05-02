package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16742 {
    static int N, M, groupNum;
    static int[][] grid;
    static int[][] groupNumGrid;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        groupNumGrid = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                switch(chars[j]){
                    case 'U': grid[i][j] = 0;
                        break;
                    case 'R': grid[i][j] = 1;
                        break;
                    case 'D': grid[i][j] = 2;
                        break;
                    case 'L': grid[i][j] = 3;
                        break;
                }
            }
        }

        // 화살표가 가리키는 방향으로 이동하며 같은 그룹인지 판별 후 visited 배열에 그룹 번호 입력

        // 입력 예시
        // R L R L
        // D D U U
        // U U U L
        // U U L L

        // visited 배열(번호가 같은 칸들은 같은 그룹이다.)
        // 1 1 2 2
        // 3 4 2 2
        // 3 4 2 2
        // 3 4 4 4

        groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(groupNumGrid[i][j] == 0){ //방문하지 않은 곳이라면 simulation
                    groupNumGrid[i][j] = groupNum;
                    simulation(i, j);
                }
            }
        }

        System.out.println(groupNum-1);
    }

    private static void simulation(int x, int y) {
        int dir = grid[x][y];
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (groupNumGrid[nx][ny] == 0) { // 아직 방문하지 않은 곳이라면
            groupNumGrid[nx][ny] = groupNum; // groupNumGrid 배열에 그룹 번호 저장

            simulation(nx, ny); // 시뮬레이션 계속 진행

            // 재귀에 끝에 도달 후 다시 돌아오면서 마지막에 방문했던 칸의 groupNum을 groupNumGrid 배열에 저장
            groupNumGrid[x][y] = groupNumGrid[nx][ny];
            return;
        }

        if (groupNumGrid[nx][ny] != groupNumGrid[x][y]) { // 이미 방문했던 칸으로 이동한다면
            groupNumGrid[x][y] = groupNumGrid[nx][ny];    // 방문하려는 칸의 groupNum을 현재 groupNumGrid 위치에 저장
            return;
        }

        if (groupNumGrid[nx][ny] == groupNumGrid[x][y]) { // 왔던 경로로 돌아가는 경우 그룹 찾기가 종료되었으므로 groupNum을 증가시키고 return
            groupNum++;
            return;
        }
    }

}
