import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138 {
	static int N;
	static boolean[] start;
	static boolean[] end; //1이면 true, 0 이면 false
	static int flag;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		start = new boolean[N]; //1이면 true, 0 이면 false
		end = new boolean[N];
		
		String light = br.readLine();
		for (int i = 0; i < N; i++) {
			if(light.charAt(i)=='1') {
				start[i] = true;
			}
		}
		light = br.readLine();
		for (int i = 0; i < N; i++) {
			if(light.charAt(i)=='1') {
				end[i] = true;
			}
		}
		

		
		//1번스위치 안누르고 시작
		go(start,0);
		
		//1번스위치 누르고 시작
		start[0] = !start[0];
		start[1] = !start[1];
		go(start, 1);	
		
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}

	}

	static void go(boolean[] light, int count) {
		//깊은 복사
		boolean[] copy = new boolean[N];
		System.arraycopy(light, 0, copy, 0, light.length);
		
		if(isSame(copy,0)) { //같은지 검사 하면서 flag값 구함
			result = Math.min(result, count);
			return;
		}
		
		int st = flag;
		while(st!=N-1) {
			copy[st] = !copy[st];
			copy[st+1] = !copy[st+1];
			if(st+2<=N-1) {
				copy[st+2] = !copy[st+2];				
			}
			
			count++; //이동횟수 늘려주고
			if(isSame(copy, st)) { //st부터 끝까지검사(그 전값은 안바꾸기 때문), end와 같다면 result와 비교
				result = Math.min(result, count);
				return;
			}
			st = flag;	
		}
		//여기 도달하면 마지막 전구만 다름 (같아질 수 없음)
	}
	private static boolean isSame(boolean[] check,int start) { //start 지점부터 끝까지 목표 전구와 같은지 검사
		for (int i = start; i < N; i++) {
			if(check[i] != end[i]) {
				flag = i; //제일 처음으로 다른 지점
				return false;
			}
		}
		return true;
	}

}