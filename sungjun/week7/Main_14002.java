package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14002 {
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dp = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 1;
		int maxIndex = 1;
		
		Arrays.fill(dp, 1);		// 가장 짧은 경우에도 1이 보장됨
		
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 1; j < i; j++) {
				if(nums[i] > nums[j]) {		// 현재 비교하는 수가 j번째 수보다 크다면
					if(dp[i] <= dp[j]) {	// 현 위치의 LIS가 j번째 위치에서의 LIS보다 이미 길다면 갱신이 불필요
						dp[i] = dp[j]+1;	// 현 위치의 LIS는 j번째 위치에서의 LIS + 1의 길이가 보장됨
					}						
					
					if(max < dp[i]) {
						max = dp[i];
						maxIndex = i;		// 추후 LIS를 출력하기 위해서 필요
					}
				}
			}
		}
		
		Stack<Integer> result = new Stack<>();
		result.push(maxIndex);		// LIS가 마지막으로 갱신된 위치의 인덱스 스택에 추가
		
		int temp = max;
		temp--;
		
		for (int i = maxIndex-1; i >= 1; i--) {		// LIS의 원소들을 내림차순으로 탐색
			if(dp[i] == temp) {
				result.push(i);
				temp--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!result.isEmpty()) {
			sb.append(nums[result.pop()]).append(" ");
		}
		
		System.out.println(max);
		System.out.println(sb);
	}

}