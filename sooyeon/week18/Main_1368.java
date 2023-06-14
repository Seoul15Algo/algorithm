import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368 {
	static int N;
	static int[] self; //직접 우물을 파는 비용 저장
	static int[] parent;
	static int result;
	static class Edge implements Comparable<Edge> {
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		self = new int[N+1];
		parent = new int[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			self[i] = Integer.parseInt(br.readLine());
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(i == j) { //출발지와 목적지가 같은 경우 직접 우물을 파는 경우로 넣어줌
					pq.offer(new Edge(0,i,self[i]));
				}
				if(i != j) {
					pq.offer(new Edge(i,j,weight));
				}
			}
		}
		
		
		//크루스칼
		result = 0;
		int count = 0;

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.s,edge.e)) {
				result += edge.w;
				count ++;
			}
			if(count == N) {
				break;
			}
		}
		System.out.println(result);
	}
	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false;
		parent[y] = x;
		return true;
	}
	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

}
