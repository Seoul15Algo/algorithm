package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ 1368 : 물대기
public class Main_1368 {
	static int N;
	static int[] W;
	static int[][] P;
	
	public static int process(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
		boolean[] visited = new boolean[N];
		int[] distance = new int[N]; // start번째 논의 우물을 팠을 때 각 논의 최소 비용
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		pq.offer(new int[] {start, 0});
		distance[start] = 0;
		
		int result = W[start];
		
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if (visited[now[0]]) {
				continue;
			}
			
			visited[now[0]] = true;
			result += distance[now[0]];
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					continue;
				}
				
				if (P[now[0]][i] < distance[i]) {
					// now[0]번째 논에서 i번째 논으로 물을 끌어오는 비용과 i번째 우물을 팔 때 드는 비용 비교 
					distance[i] = Math.min(P[now[0]][i], W[i]);
					pq.offer(new int[] {i, distance[i]});
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		W = new int[N];
		P = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				P[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// i번째 논의 우물을 팠을 때 최소 비용 
			answer = Math.min(answer, process(i));
		}
		
		System.out.println(answer);
		br.close();
	}
}
