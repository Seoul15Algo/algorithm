public class Solution_양궁대회 {

    private final int MAX = 11;
    private int N;
    private int[] apeach;
    private boolean[] choice;

    private int maxGap;
    private int[] lion;

    public int[] solution(int n, int[] info) {

        this.N = n;
        this.apeach = info;
        this.choice = new boolean[MAX];

        this.maxGap = 0;
        this.lion = new int[MAX];


        for (int i = 1; i <= N; i++) {
            dfs(0, 0, i);
        }

        if (maxGap == 0) {
            return new int[]{-1};
        }

        return lion;
    }

    public void dfs(int start, int cnt, int limit) {
        if (cnt == limit) {
            int arrows = N;
            int apeachScore = 0;
            int lionScore = 0;
            int[] cur = new int[MAX];

            for (int i = 0; i < MAX; i++) {
                if (choice[i]) {
                    arrows = arrows - (apeach[i] + 1);
                    lionScore = lionScore + (10 - i);
                    cur[i] = apeach[i] + 1;
                    continue;
                }
                cur[i] = 0;
                if (apeach[i] != 0) {
                    apeachScore = apeachScore + (10 - i);
                }
            }

            // 화살을 다 써버리는 경우
            if (arrows < 0) {
                return;
            }
            // 화살이 남아있는 경우
            if (arrows > 0) {
                // 가장 낮은 점수를 더 쏜다.
                cur[10] = cur[10] + arrows;
            }

            int curGap = lionScore - apeachScore;

            // 라이언이 진 경우
            if (curGap < 0) {
                return;
            }

            // 가장 큰 점수 차이가 아닌 경우
            if (curGap < maxGap) {
                return;
            }

            // 기존의 점수 차이보다 더 큰 점수 차이인 경우
            if (curGap > maxGap) {
                maxGap = curGap;
                lion = cur;
            }

            // 기존의 점수 차이와 같은 경우 -> 더 낮은 점수를 많이 맞힌 경우를 pick
            if (curGap == maxGap) {
                for (int i = MAX - 1; i >= 0; i--) {
                    // 현재가 더 낮은 점수를 많이 맞힌 경우
                    if (cur[i] > lion[i]) {
                        maxGap = curGap;
                        lion = cur;
                        return;
                    }
                    // 기존이 더 낮은 점수를 많이 맞힌 경우
                    if (cur[i] < lion[i]) {
                        return; // 아무것도 갱신하지 않는다
                    }
                }
            }
        }

        for (int i = start; i < MAX; i++) {
            choice[i] = true;
            dfs(i + 1, cnt + 1, limit);
            choice[i] = false;
        }
    }
}
