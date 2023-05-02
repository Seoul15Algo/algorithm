package Seoul15Algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BJ 13460 구슬 탈출2
public class Main_13460 {
    static int N, M;
    static char[][] map;
    static int result;

    static int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static class Turn{
        int redR, redC;
        int blueR, blueC;
        int direction;
        int count;

        public Turn(int redR, int redC, int blueR, int blueC, int direction, int count) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.direction = direction;
            this.count = count;
        }
    }

    public static int[] move(int r, int c, int d) {
        while (map[r][c] == '.') {
            r += directions[d][0];
            c += directions[d][1];
        }

        if (map[r][c] == 'O') {
            return new int[] {-1, -1};
        }

        if (map[r][c] == 'B') {
            return new int[] {-2, -2};
        }

        return new int[] {r - directions[d][0], c - directions[d][1]};
    }

    public static void bfs(int redR, int redC, int blueR, int blueC) {
        Queue<Turn> turns = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            turns.add(new Turn(redR, redC, blueR, blueC, i, 1));
        }

        while (!turns.isEmpty()) {
            Turn turn = turns.poll();

            int[] nRed, nBlue;

            map[turn.blueR][turn.blueC] = 'B';
            nRed = move(turn.redR, turn.redC, turn.direction);
            map[turn.blueR][turn.blueC] = '.';
            if (nRed[0] > 0) {
                map[nRed[0]][nRed[1]] = 'R';
            }
            nBlue = move(turn.blueR, turn.blueC, turn.direction);
            if (nRed[0] > 0) {
                map[nRed[0]][nRed[1]] = '.';
            }

            if (nBlue[0] == -1) { // 파란 구슬이 구멍에 들어간 경우
                continue;
            }

            if (nRed[0] == -1) { // 빨간 구슬이 구멍에 들어간 경우
                result = turn.count;
                break;
            }

            if (turn.count == 10) { // 이동 횟수가 10번인 경우
                continue;
            }

            if (nRed[0] == -2) { // 이동하다가 파란 구슬을 만난 경우
                nRed[0] = nBlue[0] - directions[turn.direction][0];
                nRed[1] = nBlue[1] - directions[turn.direction][1];
            }

            for (int i = 0; i < 4; i++) {
                if (i == turn.direction)
                    continue;

                turns.add(new Turn(nRed[0], nRed[1], nBlue[0], nBlue[1], i, turn.count + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int redR=0, redC=0;
        int blueR=0, blueC=0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    redR = i;
                    redC = j;
                    map[i][j] = '.';
                }

                if (map[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                    map[i][j] = '.';
                }
            }
        }

        result = Integer.MAX_VALUE;
        bfs(redR, redC, blueR, blueC);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        br.close();
    }
}