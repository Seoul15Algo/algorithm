package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11505 {

    private static int n;
    private static long[] arr;
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        arr = new long[n + 1];
        tree = new long[n * 4];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, n, 1);
        for (int i = 0; i < m + k; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);
            if (input[0].equals("1")) {
                arr[a] = b;
                update(1, n, 1, a, b);
                continue;
            }
            sb.append(intervalMul(1, n, 1, a, b)).append("\n");
        }

        System.out.print(sb);
    }

    public static long init(int start, int end, int idx) {
        if (start == end) {
            return tree[idx] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[idx] = (init(start, mid, idx * 2) * init(mid + 1, end, idx * 2 + 1)) % 1000000007;
    }

    public static long intervalMul(int start, int end, int idx, int left, int right) {
        if (end < left || start > right) {
            return 1;
        }

        if (start >= left && end <= right) {
            return tree[idx];
        }

        int mid = (start + end) / 2;
        return (intervalMul(start, mid, idx * 2, left, right) * intervalMul(mid + 1, end, idx * 2 + 1, left, right))
                % 1000000007;
    }

    public static long update(int start, int end, int idx, int updateIdx, long src) {
        if (updateIdx < start || updateIdx > end) {
            return intervalMul(1, n, 1, start, end);
        }

        if (updateIdx == start && start == end) {
            return tree[idx] = src;
        }

        int mid = (start + end) / 2;
        return tree[idx] = (update(start, mid, idx * 2, updateIdx, src)
                * update(mid + 1, end, idx * 2 + 1, updateIdx, src)) % 1000000007;
    }
}