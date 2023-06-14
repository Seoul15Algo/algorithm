import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_14725 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		TreeMap<String,TreeMap> map = new TreeMap<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			TreeMap target = map;
			
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				String str = st.nextToken();
				if(target.get(str) == null) { //비어 있다면 넣고 새로운 트리맵을 만들어줌
					target.put(str, new TreeMap<>());
				}
				target = (TreeMap) target.get(str); //타겟을 바꿔줌
				
			}
		}
		
		//출력- dfs
		print(map,0);
		System.out.println(sb.toString());

	}
	private static void print(TreeMap<String, TreeMap> map, int depth) {
		for (Object s : map.keySet()) {
			for (int i = 0; i < depth; i++) {
				sb.append("--");
			}
			sb.append(s).append("\n");
			print((TreeMap)map.get(s),depth+1);
		}
		
	}

}
