package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BJ 9527: 1의 개수 세기
public class Main_9527 {
	static long[] dp = new long[55]; // 자릿수(인덱스) 별 1의 누적 개수
	
	static long count(int n) {
		if (n <= 1)
			return dp[n];
		
		if (dp[n] > 0)
			return dp[n];
		
		return dp[n] = count(n-1) * 2 + (long)Math.pow(2, n-1);
	}
	
	static long process(long n) {
		if (n <= 1)
			return dp[(int)n];
		
		int size = Long.toBinaryString(n).length();
		
		if (n == (long)Math.pow(2, size) - 1)
			return count(size);
		
		long next = n % (long)Math.pow(2, size-1);
		
		return count(size-1) + process(next) + (next + 1);
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 4;
        count(54);

        System.out.println(process(B) - process(A-1));

        br.close();
    }
}
 