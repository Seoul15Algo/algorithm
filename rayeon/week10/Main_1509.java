package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팰린드롬 분할
public class Main_1509 {
    static char[] str;
    static int[][] longestMatchDist;
    
    public static int search(int start, int end) {
        if (start >= end) { // 길이가 1인 경우
            return longestMatchDist[start][start] = 1;
        }
        
        if (longestMatchDist[start][end] != 0) // 이미 방문한 경우
            return longestMatchDist[start][end];
        
        if (str[start] == str[end]) { // 가능성이 있는 경우
            if ((end - start) == 1) { // 길이가 2인 경우 더 이상 탐색할 값이 없으므로 그대로 반환
                return longestMatchDist[start][end] = 2;
            } 
            
            if (search(start+1, end-1) > 0) { // 길이가 3이상인 경우 범위를 좁혀 탐색
                return longestMatchDist[start][end] = longestMatchDist[start+1][end-1] + 2;
            }
        }

        return longestMatchDist[start][end] = -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        str = br.readLine().toCharArray();
        int n = str.length;
        int[] dp = new int[n];
        longestMatchDist = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = (i+1);
        }

        for (int i = 0; i < (n-1); i++) {
            for (int j = (i+1); j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]+1);
                
                int val = search(i, j);
                
                if (val > 0) {
                    if (i == 0) {
                        dp[j] = Math.min(dp[j], dp[i]);
                        continue;
                    }
                    
                    dp[j] = Math.min(dp[j], dp[i-1]+1);
                }
            }
        }
        
        System.out.println(dp[n-1]);
        br.close();
    }
}
