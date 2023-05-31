package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// BOJ 4195 : 친구 네트워크
public class Main_4195 {
	static int[] P;
	static int[] R;
	
	static int find(int x) {
		if (P[x] == x) {
			return x;
		}
		
		return P[x] = find(P[x]);
	}
	
	static boolean union(int start, int end) {
		int ps = find(start);
		int pe = find(end);
		
		if (ps == pe) {
			return false;
		}
		
		if (R[pe] > R[ps]) {
			P[ps] = pe;
			R[pe] += R[ps];
		} else {
			P[pe] = ps;
			R[ps] += R[pe];
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answers = new StringBuilder();
		
		int testcase = Integer.parseInt(br.readLine());
		for (int T = 0; T < testcase; T++) {
			int F = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			
			// 2 * F + 1 = 친구의 최대 개수
			P = new int[2 * F + 1];
			R = new int[2 * F + 1];
			
			for (int i = 0; i < 2 * F + 1; i++) {
				P[i] = i;
				R[i] = 1;
			}
			
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				
				String start = st.nextToken();
				String end = st.nextToken();

				if (!map.containsKey(start)) {
					map.put(start, map.size());
				}
				if (!map.containsKey(end)) {
					map.put(end, map.size());
				}

				union(map.get(start), map.get(end));

				answers.append(R[find(map.get(start))]).append("\n");
			}
		}
		
		System.out.println(answers.toString());
		br.close();
	}
}
