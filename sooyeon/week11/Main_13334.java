import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13334 {
	static int N,D;
	static int result;
	static class Node implements Comparable<Node> {
		long left;
		long right;
		public Node(long left, long right) {
			super();
			this.left = left;
			this.right = right;
		}
		@Override
		public int compareTo(Node o) { //끝나는 숫자 기준 오름차순 정렬
			if(this.right == o.right) {
				return Long.compare(this.left, o.left);
			}
			return Long.compare(this.right, o.right);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Node> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			//리스트에 (작은 숫자, 큰숫자) 순으로 넣어줌
			list.add(new Node(Math.min(a, b),Math.max(a, b)));
		}
		//정렬
		Collections.sort(list);
		
		D = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			Node cur = list.get(i);
			if(cur.right - cur.left > D) { //길이가 D이상인 것들은 제외(답에 포함이 되지 않는다.)
				continue;
			}
			pq.offer(cur.left); //시작점을 넣어줌
			while(!pq.isEmpty()) { //범위 벗어난 것들을 우선순위 큐에서 빼 준다.
				if(cur.right - pq.peek()<=D) { //범위 안쪽이면 빠져나옴
					break;
				}
				pq.poll();
			}
			result = Math.max(result, pq.size());
		}
		
		System.out.println(result);
	}
}