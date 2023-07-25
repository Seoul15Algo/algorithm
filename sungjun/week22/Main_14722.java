package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14722 {
    static int N;
    static int[][] map;
    static int[][][] milk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        milk = new int[N+1][N+1][2];      // milk[N][N][0] = 최대 우유 개수, milk[N][N][1] = 마지막으로 먹은 우유의 종류

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    milk[i+1][j+1][0] = 1;        // 무조건 딸기우유부터 시작하기 때문에 딸기우유를 파는 도시는 최대 우유 개수를 1로 초기화
                    milk[i+1][j+1][1] = 0;        // 마지막으로 먹은 우유 저장
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                boolean up = false;     // 위에서 왔을 때 순서에 맞는 우유인지
                boolean left = false;   // 왼쪽에서 왔을 때 순서에 맞는 우유인지

                // 순서에 맞는 우유인지 체크
                if(i > 1) {
                    if ((milk[i - 1][j][1] + 1) % 3 == map[i - 1][j - 1]) {
                        up = true;
                    }
                }
                if (j > 1) {
                    if ((milk[i][j - 1][1] + 1) % 3 == map[i - 1][j - 1]) {
                        left = true;
                    }
                }

                if(milk[i-1][j][0] == 0 && milk[i][j-1][0] == 0) continue;   // 아직 딸기우유가 없어서 시작하지 못한 경우일 때

                // 각각의 방향에서 오는 경우에 따른 현재 우유 개수
                int upCnt = up ? milk[i-1][j][0]+1 : milk[i-1][j][0];
                int leftCnt = left ? milk[i][j-1][0]+1 : milk[i][j-1][0];

                // 더 많은 쪽으로 갱신
                if(upCnt > leftCnt) {
                    milk[i][j][0] = upCnt;
                    if(up) {
                        milk[i][j][1] = map[i-1][j-1];
                        continue;
                    }
                    milk[i][j][1] = milk[i-1][j][1];
                    continue;
                }

                milk[i][j][0] = leftCnt;
                if(left) {
                    milk[i][j][1] = map[i-1][j-1];
                    continue;
                }
                milk[i][j][1] = milk[i][j-1][1];
            }
        }

        System.out.println(milk[N][N][0]);
    }
}
