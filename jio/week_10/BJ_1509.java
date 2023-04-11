package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1509 {
    static String text;
    static int N;
    static boolean[][] isPalindrome; //부분 문자열의 펠린드롬 여부 저장
    static int[] dp; //dp[i] : i까지의 문자열의 펠린드롬 분할의 최소 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        text = br.readLine();
        N = text.length();
        text = "#" + text; //문자열을 1번 부터 시작하기 위해 앞에 # 추가
        isPalindrome = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            isPalindrome[i][i] = true;
        }
        dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        /**
         * 입력이 ABBA일 경우 isPalindrome 배열과 dp 배열의 결과
         *
         * <==isPalindrome==>
         * true false false true
         * false true true false
         * false false true false
         * false false false true
         *
         * <==dp==>
         * [0, 1, 2, 2, 1]
         */
        for (int i = 2; i <= N; i++) { //i : 문자열의 끝
            //dp[i-1]: 문자열의 끝을 포함하지 않는 최소 펠린드롬 분할의 개수, 1: 자기 자신혼자 있을 경우
            dp[i] = dp[i-1] + 1;
            for (int j = 1; j <= i - 1; j++) { // j : 문자열의 시작
                //i 번째 문자를 포함하여 펠린드롬이 안되는 경우는 39번째 줄에서 설정한 dp[i]의 초기값보다 더 작을 수 없기 때문에
                if(text.charAt(i) != text.charAt(j)){ //문자열의 시작과 끝이 다를 경우 펠린드롬이 될수 없다.
                    continue;
                }
                if(i-j == 1){ //문자열의 끝이 문자열의 시작의 바로 다음일 경우
                    isPalindrome[j][i] = true;
                }else{
                    isPalindrome[j][i] = isPalindrome[j+1][i-1];
                }
                if(isPalindrome[j][i]){ //j~i까지의 문자열이 펠린드롬이라면 dp값 갱신
                    dp[i] = Math.min(dp[i], dp[j-1]+1);
                }
            }
        }
        System.out.println(dp[N]);
    }
}
