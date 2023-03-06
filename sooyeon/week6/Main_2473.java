import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//세 용액
public class Main_2473 {
	static int N;
	static long[] nums;
	static long[] answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new long[N];
		answer = new long[3]; //정답 값을 저장할 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		long min = Long.MAX_VALUE;
		
		for (int start = 0; start < N; start++) { //start 값은 for문으로 돌리고
			int mid = start+1; //mid -> start+1
			int end = N-1; //end -> N-1로 설정하고 투 포인터 
			while(mid < end) {
				long sum = nums[start] + nums[mid] + nums[end];
				long sumAbs = Math.abs(sum);
				if(sumAbs<min) { //min보다 값이 작으면 정답 배열에 값을 저장
					answer[0] = nums[start];
					answer[1] = nums[mid];
					answer[2] = nums[end];
					min = sumAbs;
				}
				if(sum == 0) break;
				//sum이 0보다 작으면 mid값을 올려주고 0보다 크면 end값을 내려줌
				if(sum < 0) mid++;
				if(sum > 0) end--;
			}
			
		}
		System.out.println(answer[0]+" "+answer[1]+" "+answer[2]);
		
	}

}
