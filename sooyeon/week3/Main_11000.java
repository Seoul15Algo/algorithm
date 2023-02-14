import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11000 {
	static int N;
	static int[][] time;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//오름차순 정렬
		Arrays.sort(time,(a,b)->{
			if(a[0]==b[0])
				return Integer.compare(a[1], b[1]);
			else
				return Integer.compare(a[0],b[0]);
		});
		
		//우선순위 큐
		PriorityQueue<Integer> q = new PriorityQueue<>();
		q.add(time[0][1]); //첫번째 강의의 끝나는 시간 넣음
		for (int i = 1; i < N; i++) {
			//가장 작은 종료 시간과 강의 시작시간을 비교 
			if(q.peek()<=time[i][0]) {
				q.poll();
			}
			q.add(time[i][1]);
		}
		System.out.println(q.size());
	}
}