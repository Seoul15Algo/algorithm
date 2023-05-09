package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2212 {
	static int N;
	static int K;
	static int[] sensor;
	static int[] distance;
	static int result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		sensor = new int[N];
		distance = new int[N-1];
		
		if(K >= N) {
			System.out.println(0);
			return;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		
		// 센서의 인덱스를 오름차순으로 정렬
		Arrays.sort(sensor);
		
		for (int i = 0; i < N-1; i++) {
			distance[i] = sensor[i+1] - sensor[i];
		}
		
		// 센서 사이의 거리를 오름차순으로 정렬
		Arrays.sort(distance);
		
		// K개의 집중국이 있다는 것은 K-1개의 센서 사이의 빈 공간을 생략할 수 있음
		for (int i = 0; i < N-K; i++) {
			result += distance[i];
		}
		
		System.out.println(result);
		
	}
}