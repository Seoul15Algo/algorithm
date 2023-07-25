package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21943 {
    static int N, P, Q, result;
    static int[] input, mixed;
    static boolean[] op, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        mixed = new int[N];
        op = new boolean[N-1];
        visited = new boolean[N];
        result = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        mixNum(0);      // 주어진 숫자로 구성 가능한 모든 조합 탐색
        System.out.println(result);
    }

    private static void mixNum(int cnt) {
        if(cnt == N) {
            mixOp(0, 0);    // 주어진 연산자로 구성 가능한 모든 조합 탐색
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            mixed[cnt] = input[i];
            mixNum(cnt+1);
            visited[i] = false;
        }
    }

    private static void mixOp(int n, int cnt) {
        if(cnt == Q) {
            int[] addedNum = new int[Q + 1];
            int[] idx = new int[Q + 2];
            int count = 1;

            for (int i = 0; i < N - 1; i++) {
                if (op[i]) {
                    idx[count++] = i;
                }
            }

            idx[0] = -1;
            idx[count] = N - 1;
            count = 0;
            int tmp;

            for (int i = 1; i < Q + 2; i++) {
                tmp = 0;
                for (int j = idx[i - 1] + 1; j <= idx[i]; j++) {
                    tmp += mixed[j];
                }
                addedNum[count++] = tmp;
            }

            tmp = 1;

            for (int i = 0; i < Q + 1; i++) {
                tmp *= addedNum[i];
            }

            result = Math.max(result, tmp);
            return;
        }
        if(n == N-1) return;

        op[n] = true;
        mixOp(n+1,cnt+1);
        op[n] = false;
        mixOp(n+1, cnt);
    }
}
