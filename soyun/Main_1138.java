import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1138 {
	
	static int n;
	static List<Integer> init = new ArrayList<>();
	static List<Integer> nums = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		init.add(n);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = n - 2; i >= 0; i--) {
			int cur = nums.get(i);
			List<Integer> left = getLeft(cur);
			List<Integer> right = getRight(cur);
			init.clear();
			init.addAll(left);
			init.add(i + 1);
			init.addAll(right);
		}
		
		for (int i : init) {
			System.out.print(i + " ");
		}
	}
	
	static List<Integer> getLeft(int cnt) {
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			temp.add(init.get(i));
		}
		return temp;
	}
	
	static List<Integer> getRight(int start) {
		List<Integer> temp = new ArrayList<>();
		for (int i = start; i < init.size(); i++) {
			temp.add(init.get(i));
		}
		return temp;
	}
}
