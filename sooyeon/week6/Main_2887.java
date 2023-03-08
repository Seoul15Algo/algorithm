import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//행성 터널
public class Main_2887 {
	static int N;
	static int[] p; //parent
	static long result;
	static class Planet{
		int index;
		int x;
		int y;
		int z;
		public Planet(int index, int x, int y, int z) {
			super();
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
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
		public int compareTo(Edge o) {
			return Integer.compare(this.w,o.w);
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		Planet [] planet = new Planet[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planet[i] = new Planet(i,x,y,z);
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//x좌표 기준으로 정렬, 인접한것만 pq 에 넣기
		Arrays.sort(planet,(p1,p2) ->  Integer.compare(p1.x, p2.x));
		for (int i = 1; i < N; i++) {
			int weight = planet[i].x - planet[i-1].x;
			pq.offer(new Edge(planet[i].index, planet[i-1].index, weight));
		}
		//y좌표 기준으로 정렬, 인접한것만 pq 에 넣기
		Arrays.sort(planet,(p1,p2) ->  Integer.compare(p1.y, p2.y));
		for (int i = 1; i < N; i++) {
			int weight = planet[i].y - planet[i-1].y;
			pq.offer(new Edge(planet[i].index, planet[i-1].index, weight));
		}
		//z좌표 기준으로 정렬, 인접한것만 pq 에 넣기
		Arrays.sort(planet,(p1,p2) ->  Integer.compare(p1.z, p2.z));
		for (int i = 1; i < N; i++) {
			int weight = planet[i].z - planet[i-1].z;
			pq.offer(new Edge(planet[i].index, planet[i-1].index, weight));
		}
		
		//크루스칼
		makeSet();
		result = 0;
		int count = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.s,edge.e)) {
				result += edge.w;
				count++;
				if(count== N-1) break;
			}
			
		}
		System.out.println(result);
	}
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		p[y] = x;
		return true;
	}
	static int find(int x) {
		if(x == p[x]) return x;
		return p[x] = find(p[x]);
	}
	static void makeSet() {
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
	}

}
