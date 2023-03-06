import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
//버블 소트
public class Main_1377 {
	static int N;
	static B[] nums;
	static class B implements Comparable<B> {
		int value;
		int index;
		@Override
		public int compareTo(B o) {
			return Integer.compare(this.value, o.value);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new B[N];
		for (int i = 0; i < N; i++) { //값과 인덱스를 넣어줌
			nums[i] = new B();
			nums[i].value = Integer.parseInt(br.readLine());
			nums[i].index = i;
		}
		Arrays.sort(nums); //정렬
		int max = 0;
		for (int i = 0; i < N; i++) { //정렬 후 인덱스와 정렬 전 인덱스 차이중 가장 큰 값+1 을 출력 
			if(max < nums[i].index - i) {
				max = nums[i].index - i;
			}
		}
		System.out.println(max+1);
	}

}