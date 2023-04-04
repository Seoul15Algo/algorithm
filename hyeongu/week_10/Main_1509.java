import java.util.*;
import java.io.*;

public class Main_1509 {
    static char[] arr;
    static int[] dp;
    static boolean[][] palindrome;
    static int N;
    static final int INF = 2501;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        N = str.length();
        arr = new char[N + 1];
        dp = new int[N + 1];
        palindrome = new boolean[N + 1][N + 1];     // palindrome[i][j] => i부터 j까지가 palindrome인지 아닌지

        for(int i = 0; i < N; i++){
            arr[i + 1] = str.charAt(i);
        }
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // dp를 이용해서 boolean 배열을 채우기 위해 뒤에서 부터 확인
        for(int i = N; i > 0; i--) {
            for(int j = i; j <= N; j++) {
                palindrome[i][j] = isPalindrome(i, j);
            }
        }

        // 팰린드롬인 부분을 자르고 앞부분의 분할의 개수가 최소인 경우를 선택하는 dp
        // 새로운 개수 : 앞부분 분할의 개수(dp[j - 1]) + 팰린드롬 (1)
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= i; j++){
                if(palindrome[j][i]){
                    dp[i] = Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }
        System.out.println(dp[N]);
    }

    static boolean isPalindrome(int start, int end) {
        // 마지막 쌍이 같은지 확인 후
        // 내부의 문자열은 dp를 이용하여 확인
        if(arr[start] == arr[end]) {
            start++;
            end--;

            // start < end 일 경우 내부 문자열의 길이가 2 이상
            if(start < end){
                return palindrome[start][end];
            }

            // 내부 문자열의 길이가 1이하 이므로 true
            return true;
        }
        // 마지막 쌍이 다르면 false
        return false;
    }
}