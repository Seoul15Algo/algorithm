package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BJ 24337 : 가희와 탑
public class Main_24337 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (a + b > (N+1)) {
			System.out.println(-1);
			return;
		}
		
		int[] answer = new int[N];
		Arrays.fill(answer, 1);
		
		if (a == 1) {
			answer[0] = b;

			int left = 1;
			for (int i = (N - 1); i > (N - b); i--) {
				answer[i] = left++;
			}
			
		} else {
			answer[N - b] = Math.max(a, b);
			for (int i = (N - 1); i > (N - b); i--) {
				answer[i] = N - i;
			}
			
			int left = 1;
			for (int i = 0; i < (N - b); i++) {
				if ((N - b) - (a - 1) < i)
					left++;
				
				answer[i] = left;
			}
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		
		System.out.println(sb);
		
		br.close();
	}
}