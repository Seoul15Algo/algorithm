import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9527 {
    static long[] DP = new long[55];	//1의 개수 누적합 저장 배열
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        set();	//누적합 계산
        
        long result = cal(B) - cal(A-1); //B의 누적합 - (A-1)의 누적합
        System.out.print(result);
    }
    //1~N 정수에 대한 1의 개수 구하는 함수
    static long cal(long N) {
        long count = N & 1; //마지막에 1이 있는 경우 -> count 하나 추가해주기 위함
        
        //N보다 작은 2ⁿ의 n의 최대값
        for(int i = 54; i > 0; i--) {
            if((N & (1L<<i)) != 0L) { //숫자의 i번째 비트가 1인경우 합 더하고 비트 이동
            	//ex) 12라면 (1100) -> 000~111의 1개수는 DP[2], 1000 ~ 1100 의 개수는 (12-8(1000)+1) 로 구할 수 있다.(4자리수의 개수) 이후 000 ~ 100 까지는 다음 for문을 타고 진행 
                count += DP[i-1] +(N - (1L<<i) + 1); 
                N -= (1L << i);	//비트 이동시키기
            }
        }
        
        
        return count;	//1의 개수 반환
    }
    /*
     * 점화식 -> DP[n] = DP[n-1] × 2 + 2ⁿ (DP[n] : n자리수까지의 1 개수 누적 합)
     * ex. DP[1] = 4 /  00~11 까지의 1 개수
     * 	   DP[2] = 12 /000 ~ 111 까지의 1 개수
     */
    
    static void set() {
        DP[0] = 1;	//초기화
        //1L << i == 2ⁿ
        for(int i=1;i<55;i++) {
        	DP[i] = (DP[i-1]*2) + (1L << i);
        }
    }
}