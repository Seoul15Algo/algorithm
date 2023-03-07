package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main1377 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = new int[] { num, i }; // 각 요소값과 요소의 인덱스 값을 입력
        }

        // 요소 값을 기준으로 정렬
        Arrays.sort(arr, Comparator.comparingInt(v -> v[0]));

        // 이전 요소 인덱스와 정렬 후 요소 인덱스를 비교하여 차이가 가장 큰 값 출력
        // 단 정렬이 stable sort된 상태여야 함 -> bubble sort는 stable sort기 때문에
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            count = Math.max(count, arr[i][1] - i);
        }

        System.out.println(count + 1);
    }
}