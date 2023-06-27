package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// BOJ 16947: 서울 지하철 2호선
public class Main_16947 {
	static int N;
	static List<Integer>[] graph;
	static int[] distance;
	
	// 순환선 찾기
	public static int findCycle(int prev, int now, boolean[] visited) {
		visited[now] = true;
		
		for(int next : graph[now]) {
			if (next != prev && visited[next]) { // next ~ now 간 순환 발생
				distance[now]  = 0; // 순환 시작점으로 가정
				
				return next; // 순환 끝점 반환
			} 
			
			if (!visited[next]){
				int result = findCycle(now, next, visited);
				
				if (result >= 0) { // 현재 역이 순환선에 포함이 되는 경우
					distance[now] = 0;
					
					if (result == now) { // 현재 역이 순환 끝점인 경우
						return -1; // 순환이 종료되었으므로 현재 역을 호출한 역은 순환선에 포함되지 않는다.
					} else {
						return result; // 순환선은 현재 역을 호출한 역을 포함한다.
					}
				}
			}
		}
		
		return -1; // 현재 역이 순환선에 포함되지 않는 경우
	}
	
	// 순환선에 포함되지 않는 역들의 거리 찾기
	public static void setDistance(int now) {
		for (int next : graph[now]) {
			if (distance[next] == Integer.MAX_VALUE) { // 거리를 구하지 못한 역인 경우
				distance[next] = distance[now] + 1;
				setDistance(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			
			graph[s].add(e);
			graph[e].add(s);
		}
		
		/*
			순환선을 먼저 찾은 뒤,
			순환선에 속하지 않는 역들의 거리 계산
		 */
		findCycle(-1, 0, new boolean[N]); 
		
		for (int i = 0; i < N; i++) {
			if (distance[i] < Integer.MAX_VALUE) { // 현재 역을 기준으로 거리 구하기
				setDistance(i);
			}
		}
		
		for (int i = 0; i < N; i++) {
			answer.append(distance[i]).append(" ");
		}
		System.out.println(answer);
		
		br.close();
	}
}
