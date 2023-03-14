

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14002 {
	static int N;
	static int[] arr;
	static int[] dp;
	static int result;
	static int[] answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1]; //수열입력
		dp = new int[N+1]; //자신위치 기준으로 가장 긴 부분수열 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < i; j++) {
				//dp[이전값] 을 가져오고, arr[지금] > arr[이전값] 이라면 dp[이전값]+1
				if(arr[i] > arr[j]) { //자신 값보다 이전 값이 작다면 
					dp[i] = Math.max(dp[i], dp[j]+1); //이전값+1 과 자신값 중 큰 것 
					result = Math.max(dp[i], result); //가장 큰 수열 길이
				}
			}
		}
		
		sb.append(result).append("\n");
		
		//dp배열을 뒤부터 탐색 -> result값이면 배열에 저장하고 result--;
		//앞부터 탐색하면 수열이 계속 증가하지 않을 수 있다.
		answer = new int[result];
		for (int i = N; i > 0; i--) {
			if(dp[i] == result) {
				answer[result-1] = arr[i];
				result--;
			}
		}
		
		for (int i = 0; i < answer.length; i++) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}
