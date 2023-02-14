package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11000 {
	static int N;
	static int[][] time;
	static boolean[] visited;
	static int count = 0;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			time[i][0] = s;
			time[i][1] = t;
		}
		
		Arrays.sort(time, new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1]-o2[1];
				}
				return o1[0] - o2[0];
			}
		});	
		
		// 가장 처음 시작하는 수업이 끝나는 시간을 큐에 삽입
		pq.offer(time[0][1]);
		
		for (int i = 1; i < N; i++) {
			// 시작시간이 큐의 첫번째 원소, 즉 가장 빨리 끝나는 수업보다 같거나 느리다면
			if(time[i][0] >= pq.peek()) {
				pq.poll();		// 첫번째 원소 제거, 해당 수업의 종료 시간 큐에 삽입
				pq.offer(time[i][1]);		// 우선순위 큐이기 때문에 자동으로 정렬돼서 들어감
				continue;
			}
			// 시작시간이 큐의 첫번째 원소, 즉 가장 빨리 끝나는 수업보다 빠르다면
			// 새로운 강의실 배정 필요, 큐에 그대로 삽입
			pq.offer(time[i][1]);
		}
		
		System.out.println(pq.size());
	}

}