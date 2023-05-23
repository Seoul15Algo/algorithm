package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195 {
	static int T;
	static int[] parent, friend;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(br.readLine());
			
			parent = new int[F*2+1];		// 아무도 중복이 없으면 F*2가 최대 길이
			friend = new int[F*2+1];
			
			Arrays.fill(parent, 1);
			Arrays.fill(friend, 1);		// 자기 자신이 기본
			
			int idx = 0;
			
			Map<String, Integer> name = new HashMap<>();
			
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!name.containsKey(a)) {
					name.put(a, idx);
					parent[idx] = idx++;
				}
				
				if(!name.containsKey(b)) {
					name.put(b, idx);
					parent[idx] = idx++;
				}
				
				int x = name.get(a);
				int y = name.get(b);
				
				union(x, y);
				
				sb.append(friend[find(y)]).append("\n");
			}			
		}
		
		System.out.println(sb);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		parent[y] = x;
		friend[x] += friend[y];
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}

}
