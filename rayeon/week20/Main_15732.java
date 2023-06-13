package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 15732: 도토리 숨기기
// 참고: https://baelanche.tistory.com/124
public class Main_15732 {
	static class Rule {
		int start;
		int end;
		int interval;
		
		public Rule(int start, int end, int interval) {
			this.start = start;
			this.end = end;
			this.interval = interval;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		Rule[] rules = new Rule[K];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			rules[i] = new Rule(A, B, C);
		}
		
		int answer = 0;
		
		int left = 1;
		int right = N;
		while (left <= right) {
			int mid = (left + right) / 2;	
			long sum = 0;
			
			for (int i = 0; i < K; i++) {
				if (rules[i].start > mid) {
					continue;
				}
				
				if (rules[i].end <= mid) {
					sum += (rules[i].end - rules[i].start) / rules[i].interval + 1;
				} else {
					sum += (mid - rules[i].start) / rules[i].interval + 1;
				}
			}

			if (sum >= D) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}