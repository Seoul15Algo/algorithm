package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1949 {

    private static int[] members;
    private static int[][] dp;
    private static boolean[] visited;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        members = new int[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            members[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        search(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void search(int cur) {
        visited[cur] = true;
        dp[cur][0] = members[cur];
        for (int child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }
            search(child);

            dp[cur][1] += Math.max(dp[child][0], dp[child][1]);
            dp[cur][0] += dp[child][1];
        }
    }
}