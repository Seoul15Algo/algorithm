package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_15732 {
	static int N, K, D, result;
	static int[] box;
	static ArrayList<Rule> rules;
	
	static class Rule implements Comparable<Rule>{
		int start;
		int end;
		int interval;
		
		public Rule(int start, int end, int interval) {
			super();
			this.start = start;
			this.end = end;
			this.interval = interval;
		}

		@Override
		public int compareTo(Rule o) {
			return Integer.compare(this.start, o.start);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		rules = new ArrayList<>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			rules.add(new Rule(A, B, C));
		}
		
		Collections.sort(rules);
		
		int left = 1;
		int right = N;
		
		while(left <= right) { 
			int mid = (left + right) / 2;
			long count = 0L;
			
			for (int i = 0; i < K; i++) {
				if(rules.get(i).start > mid) continue;	// 시작점이 현재 선택한 위치보다 뒤라면 고려 X
				if(rules.get(i).end <= mid) {		// 시작점과 끝점 모두 현 위치보다 앞에 있다면
					count += (long)((rules.get(i).end - rules.get(i).start) / rules.get(i).interval + 1);
					continue;
				}
				
				// 시작점은 현 위치보다 앞에, 끝점은 현 위치보다 뒤에 있다면
				count += (long)((mid - rules.get(i).start) / rules.get(i).interval + 1);
			} 
			
			if(count < D) {
				left = mid + 1;
				continue;
			}
			
			right = mid - 1;
		}
		
		System.out.println(left);
	}
}