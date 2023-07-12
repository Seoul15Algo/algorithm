package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 1939: 중량제한
public class Main_1939 {
    static int N, M;
    static List<int[]>[] map;

    static boolean bfs(int s, int e, int w) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];

        q.offer(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int[] next : map[now]) {
                if (visited[next[0]] || next[1] < w) {
                    continue;
                }

                if (next[0] == e) {
                    return true;
                }

                visited[next[0]] = true;
                q.offer(next[0]);
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            map[i] = new ArrayList<int[]>();
        }

        int minW = Integer.MAX_VALUE;
        int maxW = Integer.MIN_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            minW = Math.min(minW, C);
            maxW = Math.max(maxW, C);

            map[A].add(new int[] {B, C});
            map[B].add(new int[] {A, C});
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        int answer = 0;
        while (minW <= maxW) {
            int midW = (minW + maxW) / 2;

            if (bfs(s, e, midW)) {
                answer = midW;
                minW = midW + 1;
            } else {
                maxW = midW - 1;
            }
        }

        System.out.println(answer);
        br.close();
    }
}
