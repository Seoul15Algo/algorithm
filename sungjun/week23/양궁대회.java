package week23;

import java.util.*;

class 양궁대회 {
    static int maxScore;
    static boolean isWinner;
    static int[] apeach, answer;

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        answer = new int[11];

        isWinner = false;   // 라이언이 이길 수 있는 경우가 존재하는지
        apeach = Arrays.copyOf(info, info.length);
        maxScore = 0;

        solve(0, n, ryan);

        return isWinner ? answer : new int[] {-1};
    }

    private void solve(int n, int cnt, int[] ryan) {
        if(n == 11) {
            int score = getScoreDiff(ryan);     // 라이언과 어피치의 점수 차

            // 최대 점수 갱신 시 무조건 0점을 최대한 많이 쏘는게 정답
            if(score > maxScore) {
                ryan[10] += cnt;
                answer = Arrays.copyOf(ryan, ryan.length);
                maxScore = score;
                isWinner = true;
                ryan[10] -= cnt;

                return;
            }

            // 점수가 가장 낮은 점수를 더 많이 맞힌 순으로 정답
            if(score == maxScore) {
                for(int i = 10; i >= 0; i--) {
                    if(ryan[i] + cnt < answer[i]) break;
                    if(ryan[i] + cnt > answer[i]) {
                        ryan[i] += cnt;
                        answer = Arrays.copyOf(ryan, ryan.length);
                        ryan[i] -= cnt;
                        break;
                    }

                    cnt = 0;
                }

                return;
            }
        }

        for(int i = n; i < 11; i++) {
            int cost = apeach[i] + 1;
            if(cost > cnt) {    // 어피치보다 많이 쏠 수 없는 경우
                solve(i+1, cnt, ryan);
                continue;
            }

            // 해당 점수가 획득 가능한 경우
            ryan[i] = cost;
            solve(i+1, cnt-cost, ryan);
            ryan[i] = 0;
        }
    }

    // 라이언과 어피치의 점수 차이 반환
    private int getScoreDiff(int[] ryan) {
        int ryanSum = 0;
        int apeachSum = 0;

        for (int i = 0; i < 11; i++) {
            if(ryan[i] + apeach[i] == 0) continue;

            if(ryan[i] > apeach[i]) {
                ryanSum += 10-i;
                continue;
            }

            apeachSum += 10-i;
        }

        return ryanSum - apeachSum;
    }
}