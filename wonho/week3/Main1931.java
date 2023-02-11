package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] times = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            times[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(times, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int stand = 0;
        int count = 0;

        for (int[] time : times) {
            if (time[0] >= stand) {
                stand = time[1];
                count++;
            }
        }

        System.out.println(count);
    }
}
