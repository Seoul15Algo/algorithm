package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        //
        // System.out.println(Arrays.toString(numbers));

        long minLeft = numbers[0];
        long minRight = numbers[n - 1];
        long minMid = numbers[(n - 1) / 2];
        long minTotal = Long.MAX_VALUE;

        for (int i = 1; i < n - 1; i++) {
            boolean isZero = false;
            int left = 0;
            int right = n - 1;
            while (left < i && i < right) {
                long l = numbers[left];
                long r = numbers[right];
                long m = numbers[i];
                long sum = l + m + r;
                long absSum = Math.abs(l + m + r);

                if (absSum == 0) {
                    isZero = true;
                    minLeft = l;
                    minMid = m;
                    minRight = r;
                    break;
                }

                if (absSum < minTotal) {
                    minLeft = l;
                    minMid = m;
                    minRight = r;
                    minTotal = absSum;
                }

                if (sum < 0) {
                    left++;
                    continue;
                }
                right--;
            }

            if (isZero) {
                break;
            }
        }

        System.out.println(minLeft + " " + minMid + " " + minRight);
    }
}