package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ 1507: 궁금한 민호
public class Main_1507 {
	static int N;
	static int[][] input;
	static int[][] output;
	static PriorityQueue<Edge> edges;
	
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static int calc() {
		int sum = 0;
		
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			if (output[edge.s][edge.e] <= edge.w) {
				continue;
			}
			
			output[edge.s][edge.e] = edge.w;
			output[edge.e][edge.s] = edge.w;
			sum += edge.w;

			// 새롭게 고정한 도로를 이용했을 때 이동 시간이 짧아지는 경우, 이동 시간을 갱신한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						output[j][k] = Math.min(output[j][k], output[j][i] + output[i][k]);
						output[k][j] = output[j][k];
					}
				}
			}
		}
		
		return sum;
	}
	
	// 입력 값과 계산한 결과 값이 일치하는지 체크
	public static boolean isSame() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (input[i][j] != output[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];
		output = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(output[i], 2501);
			output[i][i] = 0;
		}
		edges = new PriorityQueue<>();
		
		/*
			이동 시간이 낮은 순서대로 도로를 고정시켜 최소 이동 시간을 구한다.
		 */
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				input[i][j] = w;
				
				if (i < j) {
					edges.offer(new Edge(i, j, w));
				}
			}
		}
		
		int answer = calc();

		if (!isSame()) {
			answer = -1;
		}
		
		System.out.println(answer);
		
		br.close();
	}

}