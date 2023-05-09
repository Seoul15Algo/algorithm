package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2357 {
    static int N, M;
    static int[] nums;
    static int[] minTree, maxTree;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        minTree = new int[N * 4];	// 최소값 트리
        maxTree = new int[N * 4];	// 최대값 트리

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        
        // 세그먼트 트리 생성
        minInit(1, N, 1);
        maxInit(1, N, 1);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            
        	int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 최소값 최대값 탐색
            int min = findMin(1, N, 1, a, b);
            int max = findMax(1, N, 1, a, b);

            sb.append(min).append(" ").append(max).append("\n");
        }
        
        System.out.println(sb);
    }
    
    // 세그먼트 트리 생성
    static int minInit(int start, int end, int node) {
    	// 더이상 쪼갤 수 없다면 리프 노드
        if (start == end) {
            minTree[node] = nums[start];
            return minTree[node];
        }

        int mid = (start + end) / 2;

        // 쪼갤 수 있다면 리프 노드들 중에 작은 값을 저장
        return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
    }
    
    static int maxInit(int start, int end, int node) {
        if (start == end) {
            maxTree[node] = nums[start];
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(maxInit(start, mid, node * 2), maxInit(mid + 1, end, node * 2 + 1));
    }
    
    // 구간 최소값 반환
    static int findMin(int start, int end, int node, int left, int right) {
    	// 범위 밖에 있다면 연산에서 제외
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }
        
        // 범위 안에 있다면 그대로 반환
        if (left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;
        
        // 부분적으로 겹친다면 리프노드까지 내려가서 연산
        return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
    }

    static int findMax(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return maxTree[node];
        }

        int mid = (start + end) / 2;
        return Math.max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right));
    }
}
