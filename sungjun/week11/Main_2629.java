package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629 {
	static int WN, BN;
	static int[] weight;
	static boolean[][] measurable;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		WN = Integer.parseInt(br.readLine());
		weight = new int[WN];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;	// 최대 무게
		
		for (int i = 0; i < WN; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			sum += weight[i];
		}
				
		measurable = new boolean[WN+1][sum+1];	
		BN = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		dfs(0, 0);

		for (int i = 0; i < BN; i++) {
			int bead = Integer.parseInt(st.nextToken());
			if(bead > sum) {		// 최대 무게 초과
				sb.append("N").append(" ");
				continue;
			}
			
			if(measurable[WN][bead]) {		// 가능한 무게라면
				sb.append("Y").append(" ");
				continue;
			}
			
			sb.append("N").append(" ");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int cnt, int sum) {
		if(measurable[cnt][sum]) return;	// 이미 확인한 경우 리턴
		measurable[cnt][sum] = true;	// 가능 여부 체크
		if(cnt == WN) return;
		
		dfs(cnt+1, sum+weight[cnt]);		// 이번 추를 구슬의 반대편에 올려놓을 때
		dfs(cnt+1, Math.abs(sum-weight[cnt]));	// 이번 추를 구슬쪽에 올려놓을 때
		dfs(cnt+1, sum);		// 이번 추를 사용하지 않을 때
	}

}
