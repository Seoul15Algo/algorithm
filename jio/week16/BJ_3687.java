package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BJ_3687 {
    static int T;
    static int match;
    static Map<Integer, String> matches;
    static String[] minDp, maxDp, zeroMinDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        // Map<성냥 개수, 만들 수 있는 최소 숫자>
        matches = new HashMap<>();
        matches.put(2, "1");
        matches.put(3, "7");
        matches.put(4, "4");
        matches.put(5, "2");
        matches.put(6, "0"); // 0이 가장 앞에 오지 않는 경우
        matches.put(7, "8");

        findMin();
        findMax();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            match = Integer.parseInt(br.readLine());
            sb.append(minDp[match]).append(" ").append(maxDp[match]).append("\n");
        }

        System.out.println(sb);
    }

    private static void findMin() {
        minDp = new String[101]; // dp[i] : i개의 성냥으로 만들 수 있는 최소 숫자
        minDp[0] = "";   // 성냥을 0 또는 1개로는 만들 수 있는 숫자가 없다.
        minDp[1] = "";
        minDp[2] = "1";
        minDp[3] = "7";
        minDp[4] = "4";
        minDp[5] = "2";
        minDp[6] = "6";  // 0이 가장 앞에 나오는 경우
        minDp[7] = "8";
        minDp[8] = "10"; // 8 - 7이 1이여서 이를 따로 처리해줌

        for (int i = 9; i <= 100; i++) {
            long min = Long.MAX_VALUE;
            String newStr = "";

            for (int j = 2; j <= 7; j++) { // 성냥으로 만들 수 있는 최소 숫자들
                newStr = minDp[i - j] + matches.get(j);
//                newStr = matches.get(j) + minDp[i - j]; 이렇게 하면 0이 앞에 들어가게 된다.
                min = Math.min(min, Long.parseLong(newStr));
            }

            minDp[i] = "" + min;
        }

    }

    private static void findMax() {
        maxDp = new String[101];
        maxDp[0] = "";
        maxDp[1] = "";
        maxDp[2] = "1";
        maxDp[3] = "7";

        for (int i = 4; i <= 100; i++) {
            maxDp[i] = maxDp[i - 2] + "1";
        }
    }
}
