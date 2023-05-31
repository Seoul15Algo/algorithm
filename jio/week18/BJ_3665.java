package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3665 {
    static int T, N, M;
    static int winner, loser;
    static int[] front, rank, origin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            front = new int[N + 1];  // front[i] : i 번 사람 앞에 있는 사람의 수
            origin = new int[N + 1]; // 기존 순위

            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                origin[num] = i + 1;
                front[num] = i;
            }

            M = Integer.parseInt(br.readLine());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());

                swap(first, second);
            }

            if (!check()) {
                sb.append("IMPOSSIBLE").append("\n");
                continue;
            }

            print(sb);
        }

        System.out.println(sb);
    }

    /*
        기존 순위가 앞선 사람은 앞에 있는 사람의 수가 1 증가
        기존 순위가 느린 사람은 앞에 있는 사람의 수가 1 감소
     */
    private static void swap(int first, int second) {
        if (origin[first] < origin[second]) {
            winner = first; // 기존 순위가 앞선 사람
            loser = second; // 기존 순위가 느린 사람

        }

        if (origin[first] > origin[second]) {
            winner = second;
            loser = first;
        }

        front[winner]++;
        front[loser]--;
    }

    /*
        앞에 서 있는 사람의 수(순위)가 중복되면 불가능
        => N이 5일 경우 4, 3, 2, 1 모두 다르게 나와야 하는데 3, 3, 2, 2 이렇게 나오면 옳지 않은 경우
     */
    private static boolean check() {  // 가능한 경우인 지 확인
        for (int i = 0; i < N; i++) {
            int frontCnt = 0;

            for (int j = 1; j <= N ; j++) {
                if (front[j] == i) {
                    frontCnt++;
                }
            }

            if (frontCnt > 1) { // 앞선 사람의 수 중 중복이 있는 지 확인
                return false;
            }
        }
        return true;
    }

    private static void print(StringBuilder sb) { // 앞에 있는 사람의 수로 등수 출력
        rank = new int[N + 1]; // 여기서 문제
        for (int i = 1; i <= N; i++) {
            rank[front[i] + 1] = i;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(rank[i]).append(" ");
        }

        sb.append("\n");
    }
}