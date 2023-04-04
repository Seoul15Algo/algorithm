package week10;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main1208 {
    private static int n;
    private static int s;
    private static long[] numbers;
    private static Map<Long, Long> leftCounts;
    private static Map<Long, Long> rightCounts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ns = br.readLine().split(" ");
        n = Integer.parseInt(ns[0]);
        s = Integer.parseInt(ns[1]);
        leftCounts = new HashMap<>();
        rightCounts = new HashMap<>();
        int mid = n / 2;

        numbers = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }
        leftSearch(0, mid, 0);
        rightSearch(mid, n, 0);

        long count = 0;
        for (Map.Entry<Long, Long> left: leftCounts.entrySet()) {
            count += rightCounts.getOrDefault(s - left.getKey(), 0L) * left.getValue();
        }

        System.out.println(s == 0 ? count - 1: count);
    }

    public static void leftSearch(int start, int dest, long total) {
        leftCounts.put(total, leftCounts.getOrDefault(total, 0L) + 1);
        for (int i = start; i < dest; i++) {
            leftSearch(i + 1, dest, total + numbers[i]);
        }
    }

    public static void rightSearch(int start, int dest, long total) {
        rightCounts.put(total, rightCounts.getOrDefault(total, 0L) + 1);
        for (int i = start; i < dest; i++) {
            rightSearch(i + 1, dest, total + numbers[i]);
        }
    }
}
