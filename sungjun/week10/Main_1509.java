package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1509 {
	static char[] cs;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cs = br.readLine().toCharArray();
		int len = cs.length;
		
		dp = new int[len+1];
		Arrays.fill(dp, Integer.MAX_VALUE/1000);
		dp[0] = 0;
		
		for (int i = 0; i < len; i++) {
			boolean check = false;

			for (int j = 0; j < i; j++) {
				if(isPalindrome(j, i)) {	// j번째 문자부터 i번째 문자까지가 팰린드롬인지 확인
					// 팰린드롬이라면 해당 지점에서의 최소 분할 개수는
					// 직전 지점 i-1에서의 분할 개수+1과 j-1번째 지점에서의 분할 개수+1 중 더 작은 값을 
					// 현재까지 갱신된 해당 지점에서의 최소 분할 개수와 비교한 것 중 작은 값
					dp[i+1] = Math.min(dp[i+1], Math.min(dp[j]+1, dp[i]+1));
					check = true;
				}
			}
			
			// 팰린드롬이 아니라면 직전까지의 분할 개수+1
			if(!check){
				dp[i+1] = dp[i]+1;
			}
		}
		
		System.out.println(dp[len]);
	}
	
	// 팰린드롬 검사
	private static boolean isPalindrome(int start, int end) {
		boolean check = true;

		while (start < end) {
			if(cs[start++] != cs[end--]) {
				check = false;
				break;
			}
		}
		
		return check;
	}

}
