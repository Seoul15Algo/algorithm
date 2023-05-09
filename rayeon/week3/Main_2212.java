import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2212{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] sensors = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			sensors[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(sensors);
		
		int[] diff = new int[N-1];
		
		for (int i = 0; i < N-1; i++)
			diff[i] = sensors[i+1] - sensors[i];
		
		Arrays.sort(diff);
		
		int result = sensors[N-1] - sensors[0];
		if(N >= 2) {
			for (int i = 0; i < K-1; i++)
				result -= diff[N-2-i];
		}
		
		System.out.println(result);
	}
}
