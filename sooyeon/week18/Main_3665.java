import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3665 {
	static int T,N,M;
	static int[] inDegree;
	static boolean[][] edges;
	static Queue<Integer> q;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			inDegree = new int[N+1]; //진입차수
			edges = new boolean[N+1][N+1]; // a,b -> (true)a가 b보다 우선순위가 높다.
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				inDegree[num] = i;
				
				for (int j = 1; j <= N; j++) {
					if(j!= num && !edges[j][num]) {
						edges[num][j] = true;
					}
				}

			}
			
			M = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				swap(num1,num2);
			}
			
			System.out.println(TPS());
		}
		

	}
	private static String TPS() {
		q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) { //진입차수가 0인 정점을 찾는다.
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		//정점의 개수만큼 반복
		for (int i = 1; i <= N; i++) {
			if(q.size() == 0) { //사이클이 형성되어 큐가 빈 경우
				return "IMPOSSIBLE";
			}
			if(q.size() > 1) { //동시에 여러개의 정점이 진입한 상태(결과가 여러개 나올 수 있어 안됨)
				return "?";
			}
			
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for (int j = 1; j <= N; j++) {
				if(edges[cur][j]) { 
					edges[cur][j] = false;
					if(--inDegree[j] == 0) { //진입차수 하나를 뺏는데 0이라면 큐에 넣기
						q.offer(j);
					}
				}
			}
			
		}

		return sb.toString();
	}
	private static void swap(int x, int y) { //edges 배열바꿔주고, 진입차수도 바꿔줌
		if(!edges[x][y]) { //false 인 경우
			edges[x][y] = true;
			edges[y][x] = false;
			inDegree[x]--;
			inDegree[y]++;
		}else {
			edges[x][y] = false;
			edges[y][x] = true;
			inDegree[x]++;
			inDegree[y]--;
		}
		
	}

}
