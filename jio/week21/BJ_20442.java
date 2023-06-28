import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_20442 {
    static char[] str;
    static int leftK, rightK, rCnt, maxLen;
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();

        // 총 R의 개수 탐색
        for (char c : str) {
            if (c == 'R') {
                rCnt++;
            }
        }

        maxLen = rCnt;

        if (rCnt != 0) { // R이 존재하는 경우에만 탐색
            findMaxLen();
        }

        System.out.println(maxLen);
    }

    private static void findMaxLen() {
        left = 0; // 왼쪽 끝에서 부터 시작하는 포인터
        right = str.length - 1; // 오른쪽 끝에서 부터 시작하는 포인터

        /**
         * leftK : left 포인터의 왼쪽에 존재하는 K의 개수
         * rightK : right 포인터의 오른쪽에 존재하는 K의 개수
         */
        while (true) {
            if (left >= right) { // 종료 조건
                int minK = Integer.min(leftK, rightK); // leftK, rightK 중 최솟 값
                maxLen = Integer.max(maxLen, minK * 2 + rCnt);
                return;
            }

            if (str[left] == 'R' && str[right] == 'R') { // 쿠쿠루쿠쿠의 양쪽 끝 R을 마주한 경우
                if (leftK <= rightK) { // left 포인터 왼쪽에 있는 K의 개수가 더 적을 경우
                    maxLen = Integer.max(maxLen, leftK * 2 + rCnt);
                    left++; // 왼쪽 포인터 이동
                    rCnt--; // R 개수 감소
                    continue;
                }

                // right 포인터 왼쪽에 있는 K의 개수가 더 적을 경우
                maxLen = Integer.max(maxLen, rightK * 2 + rCnt);
                right--; // 오른쪽 포인터 이동
                rCnt--;  // R개수 감소
                continue;
            }

            if (str[left] == 'K' && str[right] == 'R') { // 왼쪽은 K이고 오른쪽은 R인 경우
                left++;
                leftK++;
                continue;
            }

            if (str[left] == 'R' && str[right] == 'K') { // 왼쪽은 R이고 오른쪽은 K인 경우
                right--;
                rightK++;
                continue;
            }

            if (str[left] == 'K' && str[right] == 'K') { // 양쪽 다 K인 경우
                // 양쪽 포인터 모두 이동
                left++;
                leftK++;

                right--;
                rightK++;
            }
        }
    }
}