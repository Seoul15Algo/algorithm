package com.study.algo0315.soyun.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9251 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();
        int[][] LCS = new int[a.length() + 1][b.length() + 1];

        int result = 0;
        // 문자열 a와 b를 한 문자씩 비교
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                // 두 문자가 같다면 LCS[i][j] = LCS[i - 1][j - 1] + 1
                if (a.charAt(i - 1) == b.charAt(j - 1)){
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    result = Math.max(result, LCS[i][j]);
                    continue;
                }
                // 두 문자가 다르다면 LCS[i - 1][j]와 LCS[i - 1][j] 중 큰 값 대입
                LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                // 현재까지 가장 긴 최장 공통 부분 수열의 길이
                result = Math.max(result, LCS[i][j]);
            }
        }
        System.out.println(result);
    }
}