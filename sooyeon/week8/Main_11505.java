import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505 {
	static int N,M,K;
	static int[] nums;
	static long[] result;
	static int MOD = 1000000007;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nums = new int[N+1];
		result = new long[4*N]; //4*N개보다 넘을 수 없다.
		for (int i = 1; i < N+1; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		//세그먼트 트리
		seg(1,1,N); //index, start, end
		
		
		for (int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				nums[b]=c;
				//index, 바뀐값, node index, start, end
				update(b,c,1,1,N);
			}
			if(a == 2) {
				//시작범위, 끝범위, index, start, end
				sb.append(find(b,c,1,1,N)).append("\n");
			}
		}
		System.out.println(sb.toString());

	}
	static long update(int index, int v, int node, int s, int e) {
		//index가 범위 밖일경우
		if(index< s || e < index) {
			return result[node];
		}
		//index일 경우 리프노드 값 바꿔줌
		if(s == e) {
			return result[node] = v;
		}
		
		int mid = (s+e)/2;
		long left = update(index,v,node*2,s,mid);
		long right = update(index,v,node*2+1,mid+1,e);
		return result[node] = (left*right)%MOD;
		
	}
	static long find(int left, int right, int index, int s, int e) {
		if(left > e || right < s) { //범위 벗어난 경우
			return 1;
		}
		
		if(left<=s && e<=right) { //범위 안에 있는 경우
			return result[index];
		}
		
		int mid = (s+e)/2;
		long l = find(left,right,index*2,s,mid);
		long r = find(left,right,index*2+1,mid+1,e);
		return (l*r)%MOD;
	}
	static long seg(int index, int s, int e) {
		if(s==e) {
			return result[index] = nums[s];
		}
		int mid = (s+e)/2;
		long left = seg(index*2,s,mid);
		long right = seg(index*2+1,mid+1,e);
		
		return result[index] = (left*right)%MOD;
		
	}
}
