import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_22948 {
	static int N;
	static ArrayList<Integer>[] nodeList;
	static class Point implements Comparable<Point>{ //x -> x축 좌표, num -> 원 번호
		int x;
		int num;
		
		public Point(int x, int num) {
			super();
			this.x = x;
			this.num = num;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}	
	}

	static class node {
		int node;
		int cnt;
		String allNode;
		public node(int node, int cnt, String allNode) {
			super();
			this.node = node;
			this.cnt = cnt;
			this.allNode = allNode;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nodeList = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		pq.add(new Point(-1_000_000,0));
		pq.add(new Point(1_000_000,0));
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.add(new Point(b-c,a));
			pq.add(new Point(b+c,a));
			
		}
		
		makeTree(pq,-1);
		
		//bfs
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean visited[] = new boolean[N+1];
		Queue<node> q = new LinkedList<>();
		
		visited[start] = true;
		
		q.add(new node(start,1,start+""));
		
		while(!q.isEmpty()) {
			node cur = q.poll();
			if(cur.node == end) {
				System.out.println(cur.cnt);
				System.out.println(cur.allNode);
				return;
			}
			
			for (int next : nodeList[cur.node]) {
				if(visited[next]) continue;
				visited[next] = true;
				q.add(new node(next, cur.cnt+1, cur.allNode+" "+next));
			}
		}
		

	}

	private static void makeTree(PriorityQueue<Point> pq, int parent) {
		Point cur = pq.poll();
		if(parent != -1) {
			nodeList[parent].add(cur.num);
			nodeList[cur.num].add(parent);
		}
		
		while(cur.num != pq.peek().num) {
			makeTree(pq,cur.num);
		}
		pq.poll();
	}

}
