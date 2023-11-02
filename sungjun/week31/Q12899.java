package week31;

import java.util.*;

class Q12899 {
    public String solution(int n) {
        int[] order = new int[] {4, 1, 2};

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int idx = n % 3;
            n /= 3;

            if (idx == 0) n--;

            sb.append(order[idx]);
        }

        return sb.reverse().toString();
    }
}