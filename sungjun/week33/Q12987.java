package week33;

import java.util.*;

class Q12987 {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int cnt = 0;

        for(int i = A.length - 1; i >= 0; i--) {
            if(B[i+cnt] > A[i]) {
                answer++;
                continue;
            }

            cnt++;
        }

        return answer;
    }
}