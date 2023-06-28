package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// BOJ 14725 : 개미굴 
public class Main_14725 {
	static StringBuilder answer = new StringBuilder();
	
	static class Node {
		Map<String, Node> child;
		
		public Node() {
			child = new TreeMap<>();
		}
	}
	
	static void dfs(int depth, String data, Node now) {
		if (depth > 0) {
			for (int i = 0; i < (depth - 1) * 2; i++) {
				answer.append("-");
			}

			answer.append(data).append("\n");
		}
		
		for (String key : now.child.keySet()) {
			dfs(depth + 1, key, now.child.get(key));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Node root = new Node();
	
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int K = Integer.parseInt(st.nextToken());
			Node now = root;

			while (st.hasMoreTokens()) {
				String data = st.nextToken();
				
				if (!now.child.containsKey(data)) {
					now.child.put(data, new Node());
				}
				
				now = now.child.get(data);
			}
		}
		
		dfs(0, null, root);
		System.out.println(answer);
		br.close();
	}
}
