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

        similarInOrderStatus = new ArrayList<>();  // 유사 중위 순회 경로 저장
//        visited = new boolean[N + 1];
//        visited[0] = true;
        visitedCnt = 1;

        similarInOrder(1);
    }

    private static void similarInOrder(int cur) { // 유사 중위 순회
//        System.out.println("cur = " + cur);
//        System.out.println("visitedCnt = " + visitedCnt);
        similarInOrderStatus.add(cur);
//        visited[cur] = true;

        Node curNode = tree[cur]; //현재 노드
        int left = curNode.left;
        int right = curNode.right;

        if (left != -1) { // 왼쪽 자식 노드가 있는 경우 왼쪽으로 탐색 진행
            visitedCnt++;
            similarInOrder(left);
            similarInOrderStatus.add(cur); // 탐색 후 부모 노드를 경로에 추가
        }

        if (right != -1) { // 오른쪽 자식 노드가 있는 경우 오른쪽으로 탐색 진행
            visitedCnt++;
            similarInOrder(right);
            similarInOrderStatus.add(cur);  // 탐색 후 부모 노드를 경로에 추가
        }

        if (visitedCnt == N && cur == lastNode) { // 모든 노드를 방문하고 중위 순회의 마지막 노드와 일치할 경우 탐색 종료
            System.out.println(similarInOrderStatus.size() - 1);
//            System.out.println(visitedCnt);
            System.exit(0);
        }
    }

    private static boolean isAllVisited() { // 모든 노드를 방문했는지 여부 확인
        for (int i = 0; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
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