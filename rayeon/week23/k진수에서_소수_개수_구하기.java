package week23;
import java.util.*;

class k진수에서_소수_개수_구하기 {
    // 소수 판별
    static boolean isPrime(long num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= (int)Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    // num을 k진수로 표현
    static Deque<Integer> convert(int num, int k) {
        Deque<Integer> dq = new ArrayDeque<>();

        while (num >= k) {
            dq.addFirst(num % k);

            num /= k;
        }

        dq.addFirst(num);

        return dq;
    }

    public int solution(int n, int k) {
        int answer = 0;

        Deque<Integer> dq = convert(n, k);

        while (!dq.isEmpty()) {
            long num = 0;

            while (!dq.isEmpty() && dq.peek() == 0) {
                dq.poll();
            }

            while (!dq.isEmpty() && dq.peek() != 0) {
                num = num * 10 + (long)dq.poll();
            }

            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }
}
