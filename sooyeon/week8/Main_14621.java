import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14621 {
	static int N,M;
	static int[] sex;
	static int[] parent;
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sex = new int[N+1]; //M이면 1, N이면2 저장
		parent = new int[N + 1]; //부모
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			String s = st.nextToken();
			if(s.charAt(0) == 'M') sex[i] = 1;
			if(s.charAt(0) == 'W') sex[i] = 2;
		}
		//가중치 순으로 pq에 저장
		PriorityQueue<Edge> pq = new PriorityQueue<>(); 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(start,end,weight));
		}
		
		makeSet();
		
		int min = 0;
		int cnt = 0;
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			Edge edge = pq.poll();
			if (sex[edge.s] != sex[edge.e]) { //성별이다른 경우만 실행
				if(union(edge.s,edge.e)) { //크루스칼
					cnt++;
					min+= edge.w;
				}
			}
		}

		if(cnt == N-1)
			System.out.println(min);
		if(cnt != N-1)
			System.out.println(-1);
    }
	

	static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false; //자기자신이 부모일때 X
		parent[y] = x;
		return true;
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
}