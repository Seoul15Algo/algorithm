import java.util.*;
import java.io.*;
public class Main_13460 {
    static int N, M, answer;
    static int[] move, goal;
    static int[][] arr, balls, nowBalls;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 11;                    // 정답의 최대 10
        arr = new int[N][M];            // 맵 배열
        balls = new int[2][2];          // 공의 초기 위지
        nowBalls = new int[2][2];       // 공의 현재 위치
        goal = new int[2];              // 도착지
        move = new int[11];             // 이동하는 방향 subset 배열
        move[0] = -1;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                char c = str.charAt(j);

                // 벽과 공 모두 -1로 초기화
                // 도착지는 1로 초기화
                // 위치는 전역변수로 저장
                switch(c) {
                    case 'R':
                        balls[0][0] = i;
                        balls[0][1] = j;
                        arr[i][j] = -1;
                        break;
                    case 'B':
                        balls[1][0] = i;
                        balls[1][1] = j;
                        arr[i][j] = -1;
                        break;
                    case 'O':
                        goal[0] = i;
                        goal[1] = j;
                        arr[i][j] = 1;
                        break;
                    case '#':
                        arr[i][j] = -1;
                }
            }
        }

        subset(1);
        if(answer == 11) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static void subset(int depth) {
        // answer보다 더 많이 움직이는 경우는 확인할 필요 X
        if(depth == 11 || answer < depth) {
            playGame();
            resetMap();
            return;
        }

        for(int i = 0; i < 4; i++) {
            // 같은 방향으로 연속으로 움직일 필요 X
            if(move[depth - 1] != i) {
                move[depth] = i;
                subset(depth + 1);
            }
        }
    }

    static void playGame() {
        // 공의 위치 깊은복사
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                nowBalls[i][j] = balls[i][j];
            }
        }

        for(int i = 1; i < answer; i++) {
            int now = move[i];
            // 빨간공과 파란공중에 먼저 움직일 우선순위
            // 진행방향 기준으로 더 앞에 위치한 공이 우선
            int p_red = nowBalls[0][0] * dr[now] + nowBalls[0][1] * dc[now];
            int p_blue = nowBalls[1][0] * dr[now] + nowBalls[1][1] * dc[now];
            boolean red = false;
            boolean blue = false;

            if(p_red > p_blue) {
                red = moveBalls(0, now);
                blue = moveBalls(1, now);
            }
            else {
                blue = moveBalls(1, now);
                red = moveBalls(0, now);
            }

            // 파란공이 들어간 경우 실패
            if(blue) {
                return;
            }

            // 파란공이 들어가지 않고 빨간공이 들어간 경우
            if(red) {
                answer = Math.min(answer, i);
                return;
            }
        }
    }

    static boolean moveBalls(int ball, int dir) {
        int r = nowBalls[ball][0];
        int c = nowBalls[ball][1];
        arr[r][c] = 0;

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        // 빈 공간이 아닐 때 까지 진행
        while(arr[nr][nc] == 0) {
            nr += dr[dir];
            nc += dc[dir];
        }

        // 구멍에 빠진경우 true 리턴
        if(arr[nr][nc] == 1) {
            return true;
        }

        // 그렇지 않은 경우 마지막 위치를 저장하고 false 리턴
        r = nr - dr[dir];
        c = nc - dc[dir];

        arr[r][c] = -1;
        nowBalls[ball][0] = r;
        nowBalls[ball][1] = c;
        return false;
    }

    static void resetMap() {
        // 다음 경우의수를 확인하기 위해 배열 초기화
        // 마지막으로 공이 있던 위치를 지우고 초기값으로 변경
        arr[nowBalls[0][0]][nowBalls[0][1]] = 0;
        arr[nowBalls[1][0]][nowBalls[1][1]] = 0;
        arr[goal[0]][goal[1]] = 1;
        arr[balls[0][0]][balls[0][1]] = -1;
        arr[balls[1][0]][balls[1][1]] = -1;
    }
}