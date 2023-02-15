import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2212 {
	static int N; //센서 개수
	static int K; //집중국 개수
	static int[] senser; //센서 배열
	static int[] diff; //센서 차이 배열
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		senser = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			senser[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(senser);
		diff = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			diff[i] = senser[i+1]-senser[i];
		}
		
		//차이 오름차순 정렬 후, N-K개까지 합 구하기
		Arrays.sort(diff);
		int result = 0;
		for (int i = 0; i < N-K; i++) {
			result += diff[i];
		}
		System.out.println(result);
	}

}
