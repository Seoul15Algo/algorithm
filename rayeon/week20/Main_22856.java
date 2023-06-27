package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 22856: 트리 순회
public class Main_22856 {
	static int N;
	static Node[] tree;
	static int stackCount; // 방문해야 할 부모 노드 개수
	static int answer;
	
	static class Node {
		int left;
		int right;
	}
	
	public static void traversal(int now) {
		int left = tree[now].left;
		int right = tree[now].right;
		
		/* 
			중위 순회는 왼쪽 하위 트리를 모두 방문한 뒤 현재 노드를 방문 처리하므로,
			왼쪽 자식이 있는 경우 방문해야 할 부모 개수를 추가한다.
			왼쪽 하위 트리를 모두 방문했다면, 현재 노드를 방문 처리하고 방문해야 할 부모 개수를 1 차감한다.
		 */
		if (left > 0) {
			stackCount++;
			traversal(left);
			answer += 2;
			stackCount--; 
		}
		
		if (right > 0) {
			answer++;
			traversal(right);

			// 더 이상 방문해야 할 부모 노드가 없다면, 중위 순회가 끝난 것이므로 종료한다.
			if (stackCount == 0) {
				return;
			} 
			
			// 방문해야 할 부모 노드가 있다면, 다시 부모 노드로 이동해야 한다.
			answer++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new Node[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new Node();
		}
		stackCount = 0;
		answer = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (b > 0) {
				tree[a].left = b;
			}
			
			if (c > 0) {
				tree[a].right = c;
			}
		}

		traversal(1);
		
		System.out.println(answer);
		
		br.close();
	}
}
