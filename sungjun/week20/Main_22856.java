package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_22856 {
	static int N;
	static ArrayList<Node> tree;
	static class Node implements Comparable<Node>{
		int now;
		int left;
		int right;
		
		public Node(int now, int left, int right) {
			super();
			this.now = now;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.now, o.now);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		tree.add(new Node(-1, -1, -1)); 	// index를 1부터 시작으로 맞추기 위해 0번 노드를 쓰레기 값으로 대체
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			tree.add(new Node(a, b, c));
		}
		
		Collections.sort(tree);
		
		int cnt = dfs(1, 0);
		System.out.println(2*(N-1)-cnt);	// 총 간선의 개수 * 2 - 마지막 노드에서 루트 노드까지의 거리
	}
	
	private static int dfs(int cur, int cnt) {
		if(tree.get(cur).right != -1) return dfs(tree.get(cur).right, cnt+1);
		return cnt;
	}
}
