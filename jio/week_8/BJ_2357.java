import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2357 {
    static int N, M;
    static int[] nums;
    static int[] minTree;
    static int[] maxTree;
    static Pair[] pairs;
    static class Pair{
        int left;
        int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        pairs = new Pair[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken())-1;
            pairs[i] = new Pair(left, right);
        }

        minTree = new int[N*4]; //최소 값을 저장할 세그먼트 트리
        maxTree = new int[N*4]; //최대 값을 저장할 세그먼트 트리
        buildMax(1, 0, N-1);
        buildMin(1, 0, N-1);

        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairs) {
            int min = minQuery(pair.left, pair.right, 1, 0, N-1);
            int max = maxQuery(pair.left, pair.right, 1, 0, N-1);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);

    }

    private static int buildMax(int node, int nodeLeft, int nodeRight){ //최댓값 트리 생성
        if (nodeLeft == nodeRight) {
            return maxTree[node] = nums[nodeLeft];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        int leftValue = buildMax(node * 2, nodeLeft, mid);
        int rightValue = buildMax(node * 2 + 1, mid + 1, nodeRight);
        return maxTree[node] = Math.max(leftValue, rightValue);
    }

    private static int maxQuery(int left, int right, int node, int nodeLeft, int nodeRight) { //최대값 계산
        if (nodeRight < left || right < nodeLeft) {
            return Integer.MIN_VALUE;
        }
        if (left <= nodeLeft && nodeRight <= right) {
            return maxTree[node];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        int leftValue = maxQuery(left, right, node * 2, nodeLeft, mid);
        int rightValue = maxQuery(left, right, node * 2 + 1, mid + 1, nodeRight);
        return Math.max(leftValue, rightValue);
    }
    private static int buildMin(int node, int nodeLeft, int nodeRight){ //최소값 트리 생성
        if (nodeLeft == nodeRight) {
            return minTree[node] = nums[nodeLeft];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        int leftValue = buildMin(node * 2, nodeLeft, mid);
        int rightValue = buildMin(node * 2 + 1, mid + 1, nodeRight);
        return minTree[node] = Math.min(leftValue, rightValue);
    }

    private static int minQuery(int left, int right, int node, int nodeLeft, int nodeRight) { //최소값 계산
        if (nodeRight < left || right < nodeLeft) {
            return Integer.MAX_VALUE;
        }
        if (left <= nodeLeft && nodeRight <= right) {
            return minTree[node];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        int leftValue = minQuery(left, right, node * 2, nodeLeft, mid);
        int rightValue = minQuery(left, right, node * 2 + 1, mid + 1, nodeRight);
        return Math.min(leftValue, rightValue);
    }
}