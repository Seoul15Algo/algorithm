public class Solution_k진수에서_소수_개수_구하기 {

    public int solution(int n, int k) {

        String[] splitted = Long.toString(n, k).split("0");

        int count = 0;
        for (String input : splitted) {
            if (input.isEmpty()) {
                continue;
            }
            long num = Long.parseLong(input);
            if (isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    public boolean isPrime(long n) {

        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
