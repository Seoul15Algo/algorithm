import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main_20188 {

    static int N;
    static List<Integer>[] tree;
    static int[] subtree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        subtree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        DFS(1, 1);
        long answer = 0;
        // 1번 노드는 루트노드이므로 고려대상이 아님
        for (int i = 2; i <= N; i++) {
            int X = N - subtree[i]; // X는 해당 간선 아래에 존재하지 않는 노드의 개수
            // 해당 간선을 활용하는 총 횟수 =
            // 전체 노드 쌍 개수 - 해당 간선 아래에 존재하지 않는 노드들의 쌍 개수
            answer += countNodePair(N) - countNodePair(X);
        }
        System.out.println(answer);
    }

    // nC2
    public static long countNodePair(int n) {
        return 1L * n * (n - 1) / 2;
    }

    // cur 노드를 루트로 하는 서브트리에 속한 노드의 개수를 구한다.
    public static int DFS(int cur, int parent) {
        subtree[cur] = 1;
        for (int child : tree[cur]) {
            if (child == parent) {
                continue;
            }
            subtree[cur] += DFS(child, cur);
        }
        return subtree[cur];
    }
}