package week26;

import java.util.*;

class n진수_게임 {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        int num = 0;
        String string = "";
        while (string.length() <= t * m) {
            string += Integer.toString(num++,n);
        }

        int j = p - 1;
        for (int i = 0; i < t; i++) {
            answer += string.charAt(j);
            j += m;
        }

        return answer.toUpperCase();
    }
}
