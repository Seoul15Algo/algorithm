package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q3665 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static boolean[][] graph;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            testcase();
        }
    }

    public static void testcase() throws IOException {
        sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        graph = new boolean[k + 1][k + 1];
        inDegree = new int[k + 1];
        String[] t = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                graph[Integer.parseInt(t[i])][Integer.parseInt(t[j])] = true;
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if (graph[b][a]) {
                graph[b][a] = false;
                graph[a][b] = true;
            } else {
                graph[a][b] = false;
                graph[b][a] = true;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k; j++) {
                if (graph[i][j]) {
                    inDegree[j]++;
                }
            }
        }

        search(k);
        System.out.println(sb);
    }

    public static void search(int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                dq.offerLast(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dq.isEmpty()) {
                sb = new StringBuilder("IMPOSSIBLE");
                return;
            }

            if (dq.size() > 1) {
                sb = new StringBuilder("?");
                return;
            }

            int cur = dq.poll();
            sb.append(cur).append(" ");

            for (int j = 1; j <= n; j++) {
                if (graph[cur][j]) {
                    if (--inDegree[j] == 0) {
                        dq.offerLast(j);
                    }
                }
            }
        }
    }
}