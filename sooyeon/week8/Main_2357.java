import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2357 {
	static int N,M;
	static int[] nums;
	static int[] min;
	static int[] max;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		//min값 트리, max값 트리 두 개 이용
		min = new int[4*N];
		max = new int[4*N];
		
		//트리 값 채우기
		segMin(1,1,N); //node index, start, end 
		segMax(1,1,N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			//시작범위, 끝범위, node index, start, end
			sb.append(minFind(start,end,1,1,N)).append(" ").append(maxFind(start,end,1,1,N)).append("\n");	
		}
		System.out.println(sb.toString());

	}
	static int maxFind(int left, int right, int index, int s, int e) {
		if(right < s || e < left) { //범위 벗어남 
			return Integer.MIN_VALUE;
		}
		if(left<=s&& e<= right) { //범위 안에 존재
			return max[index];
		}
		int mid = (s+e)/2;
		return Math.max(maxFind(left,right,index*2,s,mid), maxFind(left,right,index*2+1,mid+1,e));
	}
	
	static int minFind(int left, int right, int index, int s, int e) {
		if(s > right || e <left) {
			return Integer.MAX_VALUE;
		}
		if(left<=s && e<=right) {
			return min[index];
		}
		int mid = (s+e)/2;
		return Math.min(minFind(left,right,index*2,s,mid), minFind(left,right,index*2+1,mid+1,e));
	}
	
	static int segMax(int index, int s, int e) {
		if(s == e) { //리프노드일경우  값 저장
			return max[index] = nums[s];			
		}
		int mid = (s+e)/2;
		return max[index] = Math.max(segMax(index*2,s,mid), segMax(index*2+1,mid+1,e));
		
	}
	
	static int segMin(int index, int s, int e) {
		if(s==e) {
			return min[index] = nums[s];
		}
		int mid = (s+e)/2;
		return min[index] = Math.min(segMin(index*2,s,mid), segMin(index*2+1,mid+1,e));
	}

}
