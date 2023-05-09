package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505 {
	static int[] nums;
	static long[] tree;
	
	// 트리 설정
	static long setValue(int start, int end, int index) {
		if (start >= end) { // 리프 노드인 경우
			return tree[index] = nums[start];
		}
		
		int middle = (start + end) / 2;
		
		// 왼쪽 서브트리의 값과 오른쪽 서브트리 값을 곱한다.
		return tree[index] = (setValue(start, middle, index * 2) * setValue(middle + 1, end, index * 2 + 1)) % 1000000007;
	}
	
	// 트리 업데이트
	static long updateValue(int targetIdx, int start, int end, int index) {
		if (start >= end) {
			return tree[index] = nums[targetIdx];
		}
	
		int middle = (start + end) / 2;
		
		if (targetIdx <= middle) { // 업데이트할 인덱스가 중간값보다 같거나 작은 경우
			// 왼쪽 서브트리를 갱신한 값과 현재 오른쪽 자식 노드의 값을 곱한다.
			return tree[index] = (tree[index * 2 + 1] * updateValue(targetIdx, start, middle, index * 2)) % 1000000007;
		} else { // 업데이트할 인덱스가 중간값보다 큰 경우
			// 오른쪽 서브트리를 갱신한 값과 현재 왼쪽 자식 노드의 값을 곱한다.
			return tree[index] = (tree[index * 2] * updateValue(targetIdx, middle + 1, end, index * 2 + 1)) % 1000000007;
		}
	}
	
	static long getValue(int start, int end, int startIdx, int index, int length) {
		if (start == end) { // 리프 노드인 경우
			return nums[start];
		}
		
		// 찾는 구간의 길이(end - start + 1)와 현재 트리 인덱스가 곱한 구간의 길이와 같은 경우
		if ((end - start + 1) == length) {
			return tree[index];
		}
		
		int middle = startIdx + length / 2;

        if (length % 2 == 0)
        	middle--;

		// 찾는 구간이 중간값보다 작거나 같은 경우
        if (end <= middle) {
        	
        	// 왼쪽만 조회
            return getValue(start, end, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1);
        }
        
        // 찾는 구간이 중간값보다 큰 경우
        if (start > middle) {
        	// 오른쪽만 조회
            return getValue(start, end, middle + 1, (index * 2 + 1), length / 2);
        }

        // 왼쪽과 오른쪽 모두 조회
        return (getValue(start, middle, startIdx, index * 2, length % 2 == 0 ? length / 2 : length / 2 + 1) * getValue(middle + 1, end, middle+1, index * 2 + 1, length / 2)) % 1000000007;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int treeSize = (int)Math.pow(2, Integer.toBinaryString(N).length() + 1);
		tree = new long[treeSize];
		setValue(0, N-1, 1);

		for (int i = 0; i < (M + K); i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) {
				nums[b] = c;
				
				updateValue(b, 0, N-1, 1);
				
				continue;
			}

			c--;
			sb.append(getValue(b, c, 0, 1, N)).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}