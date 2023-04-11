package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_13460 {
    static int N, M, minCount, hx, hy, rx, ry, bx, by; //hx, hy : 구멍의 x,y 좌표
    static char[][] grid, copy;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = chars[j];
                if(grid[i][j] == 'R'){
                    rx = i;
                    ry = j;
                }
                if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                }
                if(grid[i][j] == 'O'){
                    hx = i;
                    hy = j;
                }
            }
        }
        minCount = Integer.MAX_VALUE;
        simulation(1);
        if(minCount != Integer.MAX_VALUE){
            System.out.println(minCount);
        }else{
            System.out.println(-1);
        }
    }

    private static void simulation(int cnt){
        if(cnt > minCount){ //여태 나온 최소 횟수보다 현재 기울임 횟수가 더 클 경우 뒤에 상황을 고려하지 않아도 된다.
            return;
        }
        if (cnt > 10) { //10번 움직였을 경우
            return;
        }
        // 구슬을 이동하기 전 원래 구슬의 좌표를 저장
        int originRx = rx;
        int originRy = ry;
        int originBx = bx;
        int originBy = by;
        for (int dir = 0; dir < 4; dir++) {
            boolean isRedEnd = move(rx, ry, dir, 'R');  //빨간 구슬 이동
            boolean isBlueEnd = move(bx, by, dir, 'B'); //파란 구슬 이동

            if(isBlueEnd){ //파란공이 구멍에 들어간 경우
                goBack(originRx, originRy, originBx, originBy); //원상 복귀
                continue;
            }
            if(isRedEnd){ //빨간 공만 구멍에 들어간 경우
                goBack(originRx, originRy, originBx, originBy); //원상 복귀
                minCount = Math.min(minCount, cnt);
                continue;
            }

            if(rx == bx && ry == by){ //기울였는데 두 구슬이 겹치는 경우
                int redDis = Math.abs(originRx - rx) + Math.abs(originRy - ry);
                int blueDis = Math.abs(originBx - bx) + Math.abs(originBy - by);
                if(redDis < blueDis){ //빨간색 구슬이 먼저 도착하는 경우 파란색 구슬을 이동 방향의 반대 방향으로 한칸 이동
                    bx += dx[(dir + 2) % 4];
                    by += dy[(dir + 2) % 4];
                }else{ //파란색 구슬이 먼저 도착하는 경우 빨간색 구슬을 이동 방향의 반대 방향으로 한칸 이동
                    rx += dx[(dir + 2) % 4];
                    ry += dy[(dir + 2) % 4];
                }
            }

            changeGrid(originRx, originRy, originBx, originBy); //기울임에 따른 격자 상태 변경

            simulation(cnt+1);

            goBack(originRx, originRy, originBx, originBy); //기울이기 전으로 격자 상태 복귀 및 구슬 위치 복귀
        }
    }

    private static void changeGrid(int originRx, int originRy, int originBx, int originBy) {
        grid[originRx][originRy] = '.';
        grid[originBx][originBy] = '.';
        grid[rx][ry] = 'R';
        grid[bx][by] = 'B';
    }

    private static void goBack(int originRx, int originRy, int originBx, int originBy) {
        //격자 원상태로 복귀
        grid[rx][ry] = '.';
        grid[bx][by] = '.';
        grid[originRx][originRy] = 'R';
        grid[originBx][originBy] = 'B';
        // 구슬 좌표 원위치
        rx = originRx;
        ry = originRy;
        bx = originBx;
        by = originBy;
    }

    private static boolean move(int marbleX, int marbleY, int dir, char color) { //구슬을 기울여 위치 이동
        int curX = marbleX;
        int curY = marbleY;
        while (true) {
            int nx = curX + dx[dir];
            int ny = curY + dy[dir];
            if(!inRange(nx, ny)){ //격자를 벗어날 경우
                break;
            }
            if (grid[nx][ny] == '#') { //벽일 경우
                break;
            }
            if (grid[nx][ny] == 'O') { //구멍에 도달한 경우
                return true;
            }
            curX = nx;
            curY = ny;
        }
        if (color == 'R') { //빨간색 구슬일 경우
            rx = curX;
            ry = curY;
        }else{  //파란색 구슬일 경우
            bx = curX;
            by = curY;
        }
        return false;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
