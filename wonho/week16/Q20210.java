package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q20210 {

    private static Map<Character, Integer> priority = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int order = 1;
        for (int i = 0; i < 26; i++) {
            priority.put((char) ('A' + i), order++);
            priority.put((char) ('a' + i), order++);
        }

        Queue<String> pq = new PriorityQueue<>((v1, v2) -> {
            int v1Length = v1.length();
            int v2Length = v2.length();
            int v1Point = 0;
            int v2Point = 0;
            while (v1Point < v1Length && v2Point < v2Length) {
                char c1 = v1.charAt(v1Point);
                char c2 = v2.charAt(v2Point);
                if (!isDigit(c1) && !isDigit(c2) && c1 == c2) {
                    v1Point++;
                    v2Point++;
                    continue;
                }

                if (isDigit(c1) && !isDigit(c2)) {
                    return -1;
                }

                if (!isDigit(c1) && isDigit(c2)) {
                    return 1;
                }

                if (!isDigit(c1) && !isDigit(c2)) {
                    if (priority.get(c1) < priority.get(c2)) {
                        return -1;
                    }
                    return 1;
                }

                int v1Cur = v1Point;
                int v2Cur = v2Point;
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                while (isDigit(v1.charAt(v1Cur))) {
                    sb1.append(v1.charAt(v1Cur));
                    v1Cur++;
                    if (v1Cur >= v1Length) {
                        break;
                    }
                }
                while (isDigit(v2.charAt(v2Cur))) {
                    sb2.append(v2.charAt(v2Cur));
                    v2Cur++;
                    if (v2Cur >= v2Length) {
                        break;
                    }
                }

                BigInteger b1 = new BigInteger(sb1.toString());
                BigInteger b2 = new BigInteger(sb2.toString());
                int compared = b1.compareTo(b2);
                if (compared != 0) {
                    return compared;
                }

                if (sb1.length() < sb2.length()) {
                    return -1;
                }

                if (sb1.length() > sb2.length()) {
                    return 1;
                }

                v1Point = v1Cur;
                v2Point = v2Cur;
            }
            return Integer.compare(v1Length, v2Length);
        });

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.offer(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}