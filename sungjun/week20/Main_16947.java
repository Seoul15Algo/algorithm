package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16947 {
	static int N;
	static ArrayList<Integer> station[];
	static boolean[] visited, cycle;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		station = new ArrayList[N+1];
		cycle = new boolean[N+1];
		
		for (int i = 1; i < N+1; i++) {
			station[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			station[a].add(b);
			station[b].add(a);
		}
		
		for (int i = 1; i < N+1; i++) {
			visited = new boolean[N+1];
			if(findCycle(i, i, 0)) break;	// 사이클 체크		
		}
				
		for (int i = 1; i < N+1; i++) {
			if(cycle[i]) {
				sb.append(0).append(" ");
				continue;
			}
			sb.append(getDistance(i)).append(" ");
		}
		
		System.out.println(sb);
	}

	private static boolean findCycle(int start, int now, int before) {
		visited[now] = true;
		
		for (int i = 0; i < station[now].size(); i++) {
			int next = station[now].get(i);
			if(!visited[next]) {	// 아직 방문하지 않았다면
				if(findCycle(start, next, now)) {
					cycle[next] = true;
					return true;
				}
				continue;
			}
			
			// 다음 역이 이미 방문한 역이라면
			// 왔던 방향으로 되돌아가는 경우가 아니면서 다음 역이 시작 역이라면 사이클 형성
			if(next != before && next == start) {
				cycle[next] = true;
				return true;
			}
		}
		
		visited[now] = false;
		return false;
	}
	
	private static int getDistance(int now) {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N+1];
		
		// 현재 역과 연결된 역들 중 순환선에 포함되지 않은 역을 큐에 추가
		for (int i = 0; i < station[now].size(); i++) {
			int next = station[now].get(i);
			q.add(new int[] {next, 1});
			visited[next] = true;
		}
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			if(cycle[cur[0]]) {	// 현재 역이 순환선에 포함된 역이라면
				return cur[1];	// 현재 역까지의 거리 반환
			}
			
			for (int i = 0; i < station[cur[0]].size(); i++) {
				int next = station[cur[0]].get(i);
				if(visited[next]) continue;
				q.add(new int[] {next, cur[1]+1});
				visited[next] = true;
			}
		}
		
		return 0;
	}
}
