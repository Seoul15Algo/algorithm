import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1931 {
	static int N;
	static int[][] timeTable;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		timeTable = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			timeTable[i][0] = Integer.parseInt(st.nextToken());
			timeTable[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//오름차순 정렬
		Arrays.sort(timeTable, (a,b)->{
			if(a[1] == b[1])
				return Integer.compare(a[0], b[0]);
			else
				return Integer.compare(a[1], b[1]);
		});
		
		int count = 0;
		int end_time = 0;
		//모든 배열 순회
		for (int i = 0; i < N; i++) {
			//끝나는시간이 시작시간보다 같거나 작으면
			if(end_time <= timeTable[i][0]) {
				//카운트 올리고 끝나는시간을 바꿔준다
				count++;
				end_time = timeTable[i][1];
			}
		}
		System.out.println(count);
	}
}