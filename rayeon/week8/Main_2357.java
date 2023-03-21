package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2357 {
    static int[] nums;
    static int[] maxTree;
    static int[] minTree;
    
    // 최댓값 트리 설정
    static int setMaxValue(int start, int end, int index) {
        if (start >= end) // 리프 노드인 경우
            return maxTree[index] = nums[start];
        
        int middle = (start + end) / 2;
        
        // 중간값을 기준으로 왼쪽 서브트리의 최댓값과 오른쪽 서브트리의 최댓값 비교하기
        return maxTree[index] = Math.max(setMaxValue(start, middle, index * 2),setMaxValue(middle + 1, end, index * 2 + 1));
    }
    
    // 최솟값 트리 설정
    static int setMinValue(int start, int end, int index) {
        if (start >= end) 
            return minTree[index] = nums[start];
        
        int middle = (start + end) / 2;
        
     // 중간값을 기준으로 왼쪽 서브트리의 최솟값과 오른쪽 서브트리의 최솟값 비교하기
        return minTree[index] = Math.min(setMinValue(start, middle, index * 2),setMinValue(middle + 1, end, index * 2 + 1));
    }
    
    static int getMaxValue(int start, int end, int startIdx, int index, int length) {
    	if (start == end) { // 리프 노드인 경우
    		return nums[start];
    	}
    	
    	// 찾는 구간의 길이(end - start + 1)와 현재 트리 인덱스가 비교한 구간의 길이와 같은 경우
        if ((end - start + 1) == length) { 
            return maxTree[index];
        }

        int middle = startIdx + length / 2;

        if (length % 2 == 0)
        	middle--;

        // 찾는 구간이 중간값보다 작거나 같은 경우
        if (end <= middle) {
        	// 왼쪽만 조회
            return getMaxValue(start, end, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1);
        }
        
        // 찾는 구간이 중간값보다 큰 경우
        if (start > middle) {
        	// 오른쪽만 조회
            return getMaxValue(start, end, middle + 1, (index * 2 + 1), length / 2);
        }

        // 왼쪽과 오른쪽 모두 조회
        return Math.max(getMaxValue(start, middle, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1), getMaxValue(middle + 1, end, middle+1, index * 2 + 1, length / 2));
    }
    
    static int getMinValue(int start, int end, int startIdx, int index, int length) {
    	if (start >= end) {
    		return nums[end];
    	}
    	
        if ((end - start + 1) == length) {
            return minTree[index];
        }

        int middle = startIdx + length / 2;
        
        if (length % 2 == 0)
        	middle--;

        if (end <= middle) {
            return getMinValue(start, end, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1);
        }
        
        if (start > middle) {
            return getMinValue(start, end, middle + 1, index * 2 + 1, length / 2);
        }
        
        return Math.min(getMinValue(start, middle, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1), getMinValue(middle + 1, end, middle+1, index * 2 + 1, length / 2));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        
        int treeSize = (int)Math.pow(2, Integer.toBinaryString(N).length() + 1);
        maxTree = new int [treeSize];
        setMaxValue(0, N-1, 1);
        
        minTree = new int [treeSize];
        setMinValue(0, N-1, 1);
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            
            // 시작점, 끝점, 조회할 서브트리의 시작 인덱스, 트리 인덱스, 트리 인덱스가 비교한 구간의 길이
            sb.append(getMinValue(start, end, 0, 1, N)).append(" ");
            sb.append(getMaxValue(start, end, 0, 1, N)).append("\n");
        }
        
        System.out.println(sb);

        br.close();
    }
}