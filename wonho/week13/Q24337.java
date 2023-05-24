package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q24337 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);

        int a = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);
        int maxHeight = Math.max(a, b);
        int[] towers = new int[n + 1];
        towers[n - b + 1] = maxHeight;

        int seeACount = a - 1;
        int cur = seeACount == 0 ? maxHeight : Math.min(maxHeight, a - 1);
        for (int i = n - b; i > 0; i--) {
            towers[i] = cur;
            if (towers[i + 1] == towers[i]) {
                towers[i + 1] = 1;
            }
            if (cur > 1 && seeACount > 0) {
                cur--;
                seeACount--;
            }
        }

        cur = Math.min(maxHeight - 1, b - 1);
        for (int i = n - b + 2; i <= n; i++) {
            towers[i] = cur--;
        }


        int canSeeA = 0;
        int prev = 0;
        for (int i = 1; i <= n; i++) {
            if (towers[i] > prev) {
                canSeeA++;
            }
            prev = Math.max(prev, towers[i]);
            sb.append(towers[i]).append(" ");
        }

        if (canSeeA != a) {
            System.out.println(-1);
            return;
        }
        System.out.println(sb);

    }
}