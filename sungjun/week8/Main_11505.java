package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505 {
    static int N, M, K;
    static int[] nums;
    static long[] tree;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        tree = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M+K; i++) {
        	st = new StringTokenizer(br.readLine());
            
        	int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(a == 1) {
            	nums[b] = c;
            	updateTree(1, N, 1, b, c);
            	continue;
            } 
            if(a == 2) {
            	sb.append(findNode(1, N, 1, b, c)).append("\n");
            }
        }
        
        System.out.println(sb);
    }
    
    // 세그먼트 트리 생성
    static long init(int start, int end, int node) {
    	// 더이상 쪼갤 수 없다면 리프 노드
        if (start == end) {
            tree[node] = nums[start];
            return tree[node];
        }
        
        int mid = (start + end) / 2;
        
        // 쪼갤 수 있다면 리프 노드들의 곱을 저장 
        // 모듈러 연산의 특징에 따라 미리 1000000007로 나누어줌
        tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % 1000000007;
        return tree[node];
    }
    
    // 구간 곱 반환
    static long findNode(int start, int end, int node, int left, int right) {
    	// 곱 연산이기 때문에 정해진 구간 범위를 벗어난다면 1을 반환
        if (left > end || right < start) {
            return 1;
        }
        
        // 범위 안에 있다면 그대로 반환
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        // 부분적으로 겹친다면 리프노드까지 내려가서 계산
        int mid = (start + end) / 2;
        return (findNode(start, mid, node * 2, left, right) * findNode(mid + 1, end, node * 2 + 1, left, right)) % 1000000007;
    }
    
    // 트리 업데이트
    static long updateTree(int start, int end, int node, int index, int changeNum) {
    	// 범위 밖이라면 그대로 리턴
    	if(index < start || index > end) return tree[node];
    	
    	// 리프 노드에 도달했다면 값 갱신
    	if(start == end) {
    		tree[node] = changeNum;
    		return tree[node];
    	}
    	
    	int mid = (start + end) / 2;
    	
    	// 올라오면서 수정된 노드와 연결된 모든 노드를 갱신
    	tree[node] = (updateTree(start, mid, node * 2, index, changeNum) * updateTree(mid + 1, end, node * 2 + 1, index, changeNum)) % 1000000007;
    	return tree[node];
    }

}
