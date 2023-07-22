package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_21943 {
    static int n, p, q, maxResult;
    static int[] inputs, nums, mulLocations;
    static boolean[] numVisited, mulVisited;
    static List<Integer> operands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        inputs = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        nums = new int[n];
        mulLocations = new int[q];

        numVisited = new boolean[n];
        mulVisited = new boolean[n-1];

        findNumsOrder(0);

        System.out.println(maxResult);
    }

    private static void findNumsOrder(int cnt) { // 순열을 활용하여 숫자의 순서를 정해줌
        if (cnt == n) {
            findMulLocation(0, 0);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (numVisited[i]) {
                continue;
            }

            numVisited[i] = true;
            nums[cnt] = inputs[i];

            findNumsOrder(cnt + 1);

            nums[cnt] = 0;
            numVisited[i] = false;
        }
    }

    private static void findMulLocation(int cnt, int start) { // 조합을 사용하여 곱하기 연산자를 넣을 위치를 선정
        if (cnt == q) {
            findMaxResult();
            return;
        }

        for (int i = start; i < n - 1; i++) {
            if (mulVisited[i]) {
                continue;
            }

            mulVisited[i] = true;
            mulLocations[cnt] = i;

            findMulLocation(cnt + 1, i + 1);

            mulLocations[cnt] = 0;
            mulVisited[i] = false;
        }
    }

    /**
     * 곱하기 연산자를 넣을 위치를
     */
    private static void findMaxResult() { // 연산 최댓값 계산
        int result = 1; // 최종 연산
        int start = 0;  // 더해줄 원소의 시작 index

        for (int i = 0; i < q; i++) { // 마지막에서 두번째 덩어리 까지의 연산 결과를 계산
            int groupSum = 0;
            int end = mulLocations[i]; // 더해줄 마지막 원소의 index

            for (int j = start; j <= end; j++) {
                groupSum += nums[j];
                start = end + 1;
            }

            result *= groupSum;
        }

        int groupSum = 0; // 마지막 덩어리 계산
        for (int i = start; i < n; i++) {
            groupSum += nums[i];
        }

        result *= groupSum;

        maxResult = Integer.max(maxResult, result);
    }
}
