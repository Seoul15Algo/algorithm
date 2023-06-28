import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_16947 {
    static int N;
    static int[] prev, minDis;
    static List<Integer>[] edges;
    static boolean[] isCycle, visited;
    static boolean findCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        edges = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {  // 간선 정보 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edges[from].add(to);
            edges[to].add(from);
        }

        isCycle = new boolean[N + 1]; // 순환선 여부

        visited = new boolean[N + 1]; // 방문 여부
        visited[1] = true;

        prev = new int[N + 1]; // prev[i] : 탐색 중 i 번쨰 노드를 방문하기 직전에 방문한 노드
        prev[1] = 0;

        findCycle(1);

        minDis = new int[N + 1]; // 순환선 까지의 최소 거리를 담을 배열
        Arrays.fill(minDis, -1);

        for (int i = 1; i <= N; i++) { // 순환선인 경우 거리를 0으로 지정
            if (isCycle[i]) {
                minDis[i] = 0;
            }
        }

        for (int i = 1; i <= N; i++) {  // 각 지하철 역들의 순환선 까지 거리를 계산
            if (isCycle[i] && edges[i].size() >= 3) { // 순환선이면서 연결된 노드가 2개 이상이면 순환선에 포함되지 않은 역과 이어지는 간선이 존재하므로 DFS 탐색으로 최소 거리 탐색
                findMinDis(i, 0, i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(minDis[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static void findCycle(int cur) {
        for (Integer next : edges[cur]) {
            if (next == prev[cur]) { // 직전에 탐색한 노드일 경우(양방향이기 때문에 고려)
                continue;
            }

            if (visited[next]) { // 싸이클 발생
                if (findCycle) { // 이미 싸이클을 발견한 경우 탐색 중단
                    return;
                }

                findCycle = true; // 싸이클을 발견했다고 표시

                saveCycle(cur, next);  // 싸이클 저장
                return;
            }

            if (!visited[next]) { // 방문하지 않은 경우
                visited[next] = true;
                prev[next] = cur;  // 이전 노드를 저장
                findCycle(next);
            }
        }
    }

    private static void saveCycle(int start, int end) {  // 싸이클 내 존재하는 노드 저장(== 순환선 표시)
        int cur = start;
        while (true) { // 싸이클의 끝점 부터 시작점까지 돌며 그 사이에 있는 노드들에 싸이클이 존재한다고 표시
            if (cur == end) {
                isCycle[cur] = true;
                break;
            }

            isCycle[cur] = true;
            cur = prev[cur];
        }
    }

    private static void findMinDis(int cur, int dis, int startNode) { // DFS로 순환선 까지의 최소 거리 탐색
        if (cur != startNode && minDis[cur] != -1) { // 시작 노드가 아니고 이미 방문한 노드인 경우
            return;
        }

        minDis[cur] = dis; // 최소 거리 저장

        for (Integer next : edges[cur]) {
            if (minDis[next] != -1) { // 이미 방문한 노드인 경우
                continue;
            }

            findMinDis(next, dis + 1, startNode); // 다음 노드 탐색
        }
    }
}