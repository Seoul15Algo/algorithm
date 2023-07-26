package week23;

import java.util.*;

class K_진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;

        // 10진수 n -> k진수로 변환
        String s = Integer.toString(n, k);

        // 0을 기준으로 나눠줌
        // 나눠진 숫자가 홀수라면 무조건 문제의 조건 중 하나를 만족
        String[] group = s.split("0");

        for (int i = 0; i < group.length; i++) {
            if(group[i].equals("")) continue;   // 공백 예외처리

            // 소수가 맞다면 answer + 1
            if(isPrime(Long.parseLong(group[i]))){
                answer++;
            }
        }

        return answer;
    }

    // 소수 판별 함수
    public boolean isPrime(long n) {
        if(n == 0 || n == 1) return false;

        // 모든 짝수는 소수가 아님
        // i를 3부터 2씩 증가시키며 n이 i로 나누어 떨어진다면 소수가 아님
        for (int i = 3; i < Math.sqrt(n)+1; i+=2) {
            if(n % i == 0) return false;
        }

        return true;
    }
}
