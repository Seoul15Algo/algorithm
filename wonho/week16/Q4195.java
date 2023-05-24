package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Q4195 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static Map<String, Integer> indexes;
    private static int[] parent;
    private static int[] counts;
    private static int count;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            testcase();
        }
        System.out.print(sb);
    }

    public static void testcase() throws IOException {
        int f = Integer.parseInt(br.readLine());
        indexes = new HashMap<>();
        parent = new int[200001];
        counts = new int[200001];
        for (int i = 0; i <= 200000; i++) {
            parent[i] = i;
        }
        count = 0;

        for (int i = 0; i < f; i++) {
            String[] input = br.readLine().split(" ");
            if (indexes.get(input[0]) == null) {
                indexes.put(input[0], count++);
                counts[count] = 1;
            }
            if (indexes.get(input[1]) == null) {
                indexes.put(input[1], count++);
                counts[count] = 1;
            }

            union(indexes.get(input[0]), indexes.get(input[1]));
        }
    }

    public static int getParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    public static void union(int a, int b) {
        int ap = getParent(a);
        int bp = getParent(b);
        if (counts[ap] == 0) {
            counts[ap]++;
        }
        if (counts[bp] == 0) {
            counts[bp]++;
        }

        if (ap == bp) {
            sb.append(counts[ap]).append("\n");
            return;
        }

        parent[bp] = ap;
        int total = counts[bp] + counts[ap];
        counts[ap] = total;
        counts[bp] = total;
        sb.append(counts[ap]).append("\n");
    }
}