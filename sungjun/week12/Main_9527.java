package week12;

import java.util.Scanner;

public class Main_9527 {
	static long A, B, result;
	static long[] dp;
	static int size, powA, powB; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextLong();
		B = sc.nextLong();
		size = log(B)+4;	// dp배열 초기화를 위해 최소값 4 보장
		
		dp = new long[size];
		dp[0] = 0L;
		dp[1] = 1L;
		dp[2] = 3L;
		dp[3] = 8L;
				
		powA = log(A);
		powB = log(B);
		
		// dp[i] 는 2의 i-1제곱 부터 2의 i제곱-1 까지의 1의 합
		// 이전 배열에다가 이전 배열의 값에 1씩 더해준 값을 이어붙인 형태로 증가
		// ex)
		// 1223
		// 1223 2334
		// 1223 2334 2334 3445
		// 1223 2334 2334 3445 2334 3445 3445 4556
		for (int i = 4; i < size; i++) {
			dp[i] = 2L*dp[i-1] + (long)Math.pow(2L, i-2);
		}
		
		// 5에서 18까지의 1의 합인 경우
		// 5 -> 2^2+1   18 -> 2^4+2
		// dp[3]+dp[4]
		for (int i = powA+1; i < powB+1; i++) {
			result += dp[i];
		}
		
		A -= (long)Math.pow(2L, powA);
		B -= (long)Math.pow(2L, powB);
		
		// dp[n]의 값이 2의 n제곱 -1까지의 1의 총 합을 나타내기 때문에 
		// B가 18이었다면 위에서 계산된 값은 15까지의 1의 개수의 총합이다
		// 따라서 아래 3개의 숫자가 더 계산되어야 하는데, 18-2^4 = 2이기 떄문에 B에 1을 더해준다
		// 이어지는 수들은 첫번째 자리를 제외하고 본다면 dp테이블의 형태와 동일한 구조를 갖고 있기 때문에 재귀적으로 계산이 가능하다
		// 10000  ->  1  0000
		// 10001  ->  1  0001
		// 10010  ->  1  0010
		B++;
		
		result -= calc(A);
		result += calc(B);
		
		System.out.println(result);
	}
	
	private static long sum(int n) {
		long tmp = 0L;
		
		for (int i = 0; i <= n; i++) {
			tmp += dp[i];
		}
		
		return tmp;
	}
	
	private static long calc(long n) {
		if(n < 1) return 0L;
		int p = log(n);
		return sum(p)+n+calc((long) (n-Math.pow(2L, p)));
	}
	
	// 2^47 이상의 수부터는 Math.log(n)/Math.log(2)로 계산하면 틀린 값이 나옴
	public static int log(long n) {
		return 63 - Long.numberOfLeadingZeros(n);
	}
	
}