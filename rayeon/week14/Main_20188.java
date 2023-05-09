package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BJ 20188 : 등산 마니아
// https://velog.io/@qwerty1434/%EB%B0%B1%EC%A4%80-20188%EB%B2%88-%EB%93%B1%EC%82%B0-%EB%A7%88%EB%8B%88%EC%95%84
public class Main_20188 {
	static int N;
	static List<ArrayList<Integer>> graph;
	static long[] subtreeCount;
	static long answer;
	
	static long calcSubtreeCount(int now) {
		subtreeCount[now] = 1;
		
		for(int next : graph.get(now)) {
			if (subtreeCount[next] > 0)
				continue;
			
			subtreeCount[now] += calcSubtreeCount(next);
			answer += (subtreeCount[next] * (subtreeCount[next] - 1)) / 2 + (subtreeCount[next] * (N - subtreeCount[next]));
		}

		return subtreeCount[now];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		answer = 0;
		N = Integer.parseInt(br.readLine());
		subtreeCount = new long[N];
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			
			graph.get(s).add(e);
			graph.get(e).add(s);
		}

		calcSubtreeCount(0);
		System.out.println(answer);
		
		br.close();
	}
}
