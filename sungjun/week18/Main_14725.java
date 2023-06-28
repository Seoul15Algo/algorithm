package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_14725 {
	static int N, K;
	static StringBuilder sb = new StringBuilder();
	static class Node implements Comparable<Node> {
		String parent;		// 부모 층
		ArrayList<Node> children = new ArrayList<>(); 	// 한 단계 아래의 자식 층
		
		public Node() {}

		public Node(String parent) {
			super();
			this.parent = parent;
		}

		@Override
		public int compareTo(Node o) {
			return this.parent.compareTo(o.parent);		// 같은 층일때는 알파벳 순서
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Node nest = new Node();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			Node cur = nest;
			
			for (int j = 0; j < K; j++) {
				String s = st.nextToken();
				boolean flag = false;
				
				for (int k = 0; k < cur.children.size(); k++) {
					if(cur.children.get(k).parent.equals(s)) {	// 현재 노드의 하위 계층에 포함된 먹이라면
						cur = cur.children.get(k);	// 해당 위치로 포인터를 옮겨주고 넘어간다
						flag = true;
						break;
					}					
				}
				
				if(!flag) {		// 새롭게 추가해야 하는 경우라면
					Node nextNest = new Node(s);	// 새로운 먹이의 정보를 담은 노드 생성
					cur.children.add(nextNest);		// 현재 노드의 하위 계층에 새로운 노드 추가
					cur = nextNest;		// 포인터를 새로 추가한 노드로 이동
				}
			}
		}

		print(nest, 0);
		System.out.println(sb);
	}

	private static void print(Node nest, int cnt) {
		Collections.sort(nest.children);	// 사전순으로 정렬
		
		for (int i = 0; i < nest.children.size(); i++) {
			for (int j = 0; j < cnt; j++) {
				sb.append("--");	// 계층 구분
			}
			sb.append(nest.children.get(i).parent).append("\n");
			print(nest.children.get(i), cnt+1);
		}
	}
}
