package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2357 {

    private static int n;
    private static int[] arr;
    private static int[] maxTree;
    private static int[] minTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        arr = new int[n + 1];
        maxTree = new int[n * 4];
        minTree = new int[n * 4];
        int m = Integer.parseInt(nm[1]);

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMaxTree(1, n, 1);
        initMinTree(1, n, 1);

        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            sb.append(getMinNumber(1, n, 1, a, b)).append(" ").append(getMaxNumber(1, n, 1, a, b)).append("\n");
        }

        System.out.print(sb);
    }

    public static int initMaxTree(int start, int end, int idx) {
        if (start == end) {
            return maxTree[idx] = arr[start];
        }

        int mid = (start + end) / 2;

        return maxTree[idx] = Math.max(initMaxTree(start, mid, idx * 2), initMaxTree(mid + 1, end, idx * 2 + 1));
    }

    public static int initMinTree(int start, int end, int idx) {
        if (start == end) {
            return minTree[idx] = arr[start];
        }

        int mid = (start + end) / 2;

        return minTree[idx] = Math.min(initMinTree(start, mid, idx * 2), initMinTree(mid + 1, end, idx * 2 + 1));
    }

    public static int getMaxNumber(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (start >= left && end <= right) {
            return maxTree[idx];
        }

        int mid = (start + end) / 2;
        return Math.max(getMaxNumber(start, mid, idx * 2, left, right),
                getMaxNumber(mid + 1, end, idx * 2 + 1, left, right));
    }

    public static int getMinNumber(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if (start >= left && end <= right) {
            return minTree[idx];
        }

        int mid = (start + end) / 2;
        return Math.min(getMinNumber(start, mid, idx * 2, left, right),
                getMinNumber(mid + 1, end, idx * 2 + 1, left, right));
    }
}