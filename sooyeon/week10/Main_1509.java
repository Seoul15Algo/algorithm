import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1509 {
	static boolean[][] palindrome;
	static int[] dp;
	static int size;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		size = str.length();
		
		palindrome = new boolean[size+1][size+1];
		
		//palindrome[i][j] => i~j까지의 문자가 팰린드롬수인지
		for (int i = 1; i <= size; i++) {
			for (int j = i; j <= size; j++) {
				boolean isSame = true;
				
				int from = i -1;
				int to = j-1;
				
				while(from<=to) {
					if(str.charAt(from++) != str.charAt(to--)) {
						isSame = false;
						break;
					}
				}
				if(isSame) {
					palindrome[i][j] = true;
				}
			}
		}
		
		//dp점화식 - 현재값 max로 초기화, 팰린드롬수 일 때 팰린드롬수 시작점의 dp+1
		dp = new int[size+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= i; j++) {
				if(palindrome[j][i]) {
					dp[i] = Math.min(dp[i], dp[j-1]+1);					
				}
			}
		}

		System.out.println(dp[size]);

	}

}