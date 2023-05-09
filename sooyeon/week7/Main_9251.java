import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9251 {
	static int N;
	static int[][] alphabet;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int N = str1.length();
		int M = str2.length();
		//배열 한칸 더 만들어서 00~N0 , 00~0M 까지 0으로 초기화
		
		alphabet = new int[N+1][M+1];
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				//문자열이 같다면 왼쪽대각선위, 위 중 min값 +1
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					alphabet[i][j] = Math.min(alphabet[i-1][j], alphabet[i-1][j-1])+1;
					//alphabet[i][j] = alphabet[i-1][j-1]+1; 로 해도 되겠네효,,호호..
				}
				//문자열이 다르다면 위,왼 중 max값
				if(str1.charAt(i-1) != str2.charAt(j-1)) {
					alphabet[i][j] = Math.max(alphabet[i-1][j], alphabet[i][j-1]);
				}
			}
		}
		//N행중 가장 높은값을 출력
		int result = 0;
		for (int i = 1; i <= M; i++) {
			result = Math.max(result, alphabet[N][i]);
		}
		System.out.println(result);
		
	}

}