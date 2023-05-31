import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_4195 {
	static int T;
	static int N;
	static int[] parent;
	static int[] count;
	static HashMap<String,Integer> map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			map = new HashMap<>();
			N = Integer.parseInt(br.readLine());
			parent = new int[N*2]; //부모
			count = new int[N*2];
			for (int i = 0; i < N*2; i++) {
				parent[i] = i;
				count[i] = 1; //최초 친구수 = 1
			}
			int index = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) { //처음들어온 이름인경우 맵에 넣기
					map.put(a, index++);
				}
				if(!map.containsKey(b)) { //처음들어온 이름인경우 맵에 넣기
					map.put(b, index++);
				}
				
				//유니온파인드
				union(map.get(a),map.get(b));
				
				sb.append(count[find(map.get(b))]).append("\n");
			}
			
		}
		System.out.println(sb.toString());
		

	}
	static void union(int a, int b) {
		a = find(a); //부모 찾기
		b = find(b);
		
		if(a == b) { //이미 연결되어 있는 경우
			return;
		}
		
		parent[b] = a;
		count[a] += count[b];
	}
	static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

}