import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_22856 {
    static int N, moveCnt, lastNode, visitedCnt;
    static Node[] tree;
    static boolean[] visited;
    static List<Integer> similarInOrderStatus, inOrderStatus;
    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        tree = new Node[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[num] = new Node(left, right);
        }

        inOrderStatus = new ArrayList<>(); // 중위 순회 경로 저장
        inOrder(1);

        lastNode = inOrderStatus.get(N - 1); // 중위 순회의 마지막 노드

        similarInOrderStatus = new ArrayList<>();  // 유사 중위 순회 경로 저장, 꼭 필요하진 않지만 과정을 확인하기 위해 추가
        visited = new boolean[N + 1];
        visited[0] = true;

        similarInOrder(1);
    }

    private static void similarInOrder(int cur) { // 유사 중위 순회
        similarInOrderStatus.add(cur);

        if (!visited[cur]) {
            visited[cur] = true;
            visitedCnt++; // 방문한 노드 수 증가
        }

        Node curNode = tree[cur]; //현재 노드
        int left = curNode.left;
        int right = curNode.right;

        if (left != -1) { // 왼쪽 자식 노드가 있는 경우 왼쪽으로 탐색 진행
            similarInOrder(curNode.left);
            similarInOrderStatus.add(cur); // 탐색 후 부모 노드를 경로에 추가
        }

        if (right != -1) { // 오른쪽 자식 노드가 있는 경우 오른쪽으로 탐색 진행
            similarInOrder(curNode.right);
            similarInOrderStatus.add(cur);  // 탐색 후 부모 노드를 경로에 추가
        }

        if (visitedCnt == N && cur == lastNode) { // 모든 노드를 방문하고 중위 순회의 마지막 노드와 일치할 경우 탐색 종료
            System.out.println(similarInOrderStatus.size() - 1);
            System.exit(0);
        }
    }

    private static void inOrder(int cur) { // in-order(중위 순회)
        Node curNode = tree[cur];
        int left = curNode.left;
        int right = curNode.right;

        if (left != -1) {
            inOrder(left);
        }

        inOrderStatus.add(cur);

        if (right != -1) {
            inOrder(right);
        }
    }
}