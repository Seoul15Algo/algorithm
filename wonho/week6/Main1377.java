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
            arr[i] = new int[] { num, i };
        }

        Arrays.sort(arr, Comparator.comparingInt(v -> v[0]));

        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            count = Math.max(count, arr[i][1] - i);
        }

        System.out.println(count + 1);
    }
}