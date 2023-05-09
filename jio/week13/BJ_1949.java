package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1949 {
    static int N;
    static int[] people;
    static boolean[] visited;
    static int[][] dp;
    static List<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i + 1] = Integer.parseInt(st.nextToken());
        }

        // dp[i][1] : i 번째 마을을 우수마을로 선택한 경우 마을 주민 수의 최대 값
        // dp[i][0] : i 번째 마을을 우수마을로 선택하지 않은 경우 마을 주민 수의 최대 값

        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) { // i 번째 마을을 우수마을로 선택한 경우 i 번째 마을의 주민 수를 저장
            dp[i][1] += people[i];
        }

        edges = new List[N + 1]; // 마을 입력 제한이 10000이어서 인접 행렬로 돌려볼 시 메모리 초과 발생 => 인접리스트로 구현
        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {  // 인접 리스트에 간선 정보 저장
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
            edges[end].add(start);
        }

        visited = new boolean[N + 1]; // 깊이 우선 탐색 시 사용할 방문 배열
        visited[1] = true;  // 1번 사람을 루트로 지정(방향이 없으므로 루트노드를 어디로 두던 가능하다)

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int vertex) {

        for (int nextVertex : edges[vertex]) { // 간선이 존재하고 방문하지 않은 노드라면 탐색
            if(!visited[nextVertex]){
                visited[nextVertex] = true;
                dfs(nextVertex);

                // 리프노드까지 탐색 후 다시 되돌아오면서 dp 값 갱신
                // 현재 보고 있는 마을이 우수 마을인 경우 : 자식 마을이 우수마을일 수 없으므로 dp[child][0]을 더해준다.
                // 현재 보고 있는 마을이 우수 마을이 아닌 경우, 자식 마을이 우수마을일 수도 아닐 수도 있으므로
                // dp[child][0]과 dp[child][1] 중 더 큰 값을 더해준다.
                dp[vertex][1] += dp[nextVertex][0];
                dp[vertex][0] += Math.max(dp[nextVertex][0], dp[nextVertex][1]);
            }
        }

    }
}
