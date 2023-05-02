package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_20188 {
    static int N, variety;
    static List<Integer>[] tree;
    static int[] subTree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        subTree = new int[N + 1]; // subTree[i] : i 번째 노드를 루트노드로 하는 서브트리에 포함되는 노드의 수
        for (int i = 1; i <= N; i++) { // 자기 자신을 무조건 포함하기 때문에 1로 초기화
            subTree[i] = 1;
        }

        for (int i = 0; i < N - 1; i++) { // 인접리스트로 트리 표현
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }

        visited = new boolean[N + 1];
        visited[1] = true;
        DFS(1); // 루트 노드부터 dfs 탐색

        // 주어진 서브 테스크에 따르면 루트 노드를 제외한 모든 노드는 부모로 가는 간선을 하나씩 지니게 된다.
        // 따라서 2번 노드 위로 뻗어 나가는(부모로 가는) 간선 부터 마지막 노드 위로 뻗어 나가는 간선까지 순회하면 모든 간선을 고려하게 된다.
        long variety = 0;
        for (int i = 2; i <= N; i++) {
            int restNodeCnt = N - subTree[i]; // 현재 선택한 간선을 사용하지 않는 조합을 이루는 노드의 개수(B영역에 속하는 노드의 개수)
            variety += nC2(N) - nC2(restNodeCnt); // nC2 - (B영역의개수)C2
        }
        System.out.println(variety);
    }

    private static long nC2(int n) { // nC2를 구해주는 메소드, n^2이 int를 초과하므로 long으로 선언
        return (long) n * (n - 1) / 2;
    }

    private static int DFS(int cur) { // 깊이 우선 탐색을 통해 루트 노드 부터 리프 노드까지 내려가며 subTree 배열에 값을 채운다.

        for (Integer next : tree[cur]) { // 자식 노드 탐색
            if (visited[next]) { // 이미 방문한 곳이라면 continue(양방향 그래프이기 때문에 부모 노드로 다시 올라가는 걸 방지)
                continue;
            }
            visited[next] = true;
            subTree[cur] += DFS(next); // 서브트리 내 노드 개수 갱신
        }

        return subTree[cur];
    }
}
