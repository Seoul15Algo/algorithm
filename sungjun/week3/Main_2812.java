package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_2812 {
	static int N, K;
	static int count = 0;
	static Deque<Integer> result = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		char[] cs = br.readLine().toCharArray();
		result.offer(cs[0]-'0');
		
		// 입력받은 숫자를 큐에 넣을 때 큐에 들어가있는 수 중에 지금 넣을 숫자보다 작은 수는 모두 제거
		for (int i = 1; i < N; i++) {
			while(!result.isEmpty() && count < K) {
				if(result.peekLast() < cs[i]-'0') {
					result.pollLast();
					count++;
					continue;
				}
				break;
			}
			result.offer(cs[i] - '0');
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 775999999와 같이 같은 숫자가 이어서 나와서 K개의 숫자보다 적게 제거된 경우를 대비하여
		// 뒤에서부터 K-count 개의 숫자는 제외하고 출력
		while(result.size() > K-count) {
			sb.append(result.poll());
		}
		System.out.println(sb);
	}
}