import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15732 {
    static int N, K, D;

    static Rule[] rules;
    static class Rule {
        int start, end, gap;

        public Rule(int start, int end, int gap) {
            this.start = start;
            this.end = end;
            this.gap = gap;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        rules = new Rule[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int gap = Integer.parseInt(st.nextToken());

            rules[i] = new Rule(start, end, gap);
        }

        System.out.println(findLastAcornBox());
    }

    private static int findLastAcornBox() { // 마지막 도토리가 담긴 박스 탐색
        int left = 0;
        int right = N;
        int minIdx = Integer.MAX_VALUE; // lower bound

        while (left <= right) {
            int mid = (left + right) / 2;

            long acornOrder = findAcornOrder(mid); // mid 번째 박스까지 몇개의 도토리가 들어있는 지 확인

            if (acornOrder >= D) { // 목표하는 값보다 더 크거나 같을 경우
                right = mid - 1;
                minIdx = Math.min(minIdx, mid); // lower bound 갱신
                continue;
            }

            left = mid + 1; // 목표하는 값보다 더 작을 경우
        }

        return minIdx;
    }

    /*
        lower bound를 사용하지 않을 경우 틀린 답이 나오게 된다.
        200 2 10
        100 150 10
        110 180 15
     */
    private static long findAcornOrder(int cur) { // cur번 째 박스에 몇 번째 도토리가 존재하는 지 계산
        long acornNum = 0; // cur 번째 박스까지 몇 개의 도토리가 존재하는 지 저장할 변수

        for (Rule rule : rules) {
            if (cur < rule.start) { // 현재 보고 있는 박스가 규칙의 시작 지점 보다 더 작은 경우
                continue;
            }

            int end = Math.min(rule.end, cur);
            acornNum += (end - rule.start) / rule.gap + 1;
        }

        return acornNum;
    }
}
