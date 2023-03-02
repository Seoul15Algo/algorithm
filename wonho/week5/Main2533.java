package wonho.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2533 {

    private static List<List<Integer>> graph;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        // n의 범위가 2부터 1000000까지기 때문에 1000001로 초기화
        dp = new int[1000001][2];
        visited = new boolean[1000001];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        search(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void search(int curIndex) {
        visited[curIndex] = true;
        dp[curIndex][0] = 1;
        List<Integer> cur = graph.get(curIndex);

        for (int child : cur) {
            if (visited[child]) {
                continue;
            }

            search(child);

            dp[curIndex][1] += dp[child][0];
            dp[curIndex][0] += Math.min(dp[child][1], dp[child][0]);
        }
    }
}
