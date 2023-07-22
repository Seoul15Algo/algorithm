package baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21943 연산 최대로
public class Main_21943 {

    static int N;
    static int[] numbers;
    static boolean[] visited;
    static int[] operand;
    static boolean[] operator;
    static int sum, mul;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        visited = new boolean[N + 1];
        operand = new int[N];
        operator = new boolean[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        sum = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        max = 0;

        pickOperand(0);

        System.out.println(max);
    }

    // 피연산자 고르기
    static void pickOperand(int cnt) {
        if (cnt == N) {
            pickOperator(0, 0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            operand[cnt] = numbers[i];
            pickOperand(cnt + 1);
            visited[i] = false;
        }
    }

    // 연산자 고르기 -> 곱셈의 위치만 골라준다
    static void pickOperator(int start, int cnt) {
        if (cnt == mul) {
            int result = 1;
            int sum = operand[0];
            // 덧셈 한 뭉탱이를 가로로 묶고 곱셈을 해야 값이 최대가 된다
            for (int i = 1; i < N; i++) {
                // 곱셈일 경우
                if (operator[i - 1]) {
                    result = result * sum;
                    sum = operand[i];
                    continue;
                }
                sum = sum + operand[i];
            }
            result = result * sum;
            max = Math.max(max, result);

            return;
        }

        for (int i = start; i < N - 1; i++) {
            operator[i] = true;
            pickOperator(i + 1, cnt + 1);
            operator[i] = false;
        }
    }
}
