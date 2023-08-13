package week23;

import java.util.*;

class 양궁_대회 {
    static final int TARGET_SIZE = 11;
    static int N;

    static int[] answer;

    static int[] apeachInfo;
    static int apeachScore;

    static int maxDiff;

    static void subset(int target, int count, int lionScore, int[] lionInfo) {
        if (count == N || target == TARGET_SIZE - 1) {
            if (lionScore <= apeachScore) {
                return;
            }

            lionInfo[target] = N - count;

            int diff = lionScore - apeachScore;

            if (diff > maxDiff) {
                maxDiff = diff;
                answer = Arrays.copyOf(lionInfo, TARGET_SIZE);
            }

            if (diff == maxDiff) {
                for (int i = TARGET_SIZE - 1; i >= 0; i--) {
                    if (answer[i] != lionInfo[i]) {
                        if (answer[i] < lionInfo[i]) {
                            answer = Arrays.copyOf(lionInfo, TARGET_SIZE);
                        }

                        break;
                    }
                }
            }

            lionInfo[target] = 0;
            return;
        }

        int arrowCount = apeachInfo[target] + 1;
        if (arrowCount <= N - count) {
            lionInfo[target] = arrowCount;

            if (arrowCount > 1) {
                apeachScore -= (10 - target);
            }

            subset(target + 1, count + arrowCount, lionScore + (10 - target), lionInfo);

            if (arrowCount > 1) {
                apeachScore += (10 - target);
            }
        }

        lionInfo[target] = 0;
        subset(target + 1, count, lionScore, lionInfo);
    }

    public int[] solution(int n, int[] info) {
        N = n;
        answer = new int[TARGET_SIZE];
        apeachInfo = info;
        apeachScore = 0;
        maxDiff = 0;

        for (int i = 0; i < TARGET_SIZE; i++) {
            if (apeachInfo[i] > 0) {
                apeachScore += (10 - i);
            }
        }

        subset(0, 0, 0, new int[TARGET_SIZE]);

        if (maxDiff <= 0) {
            answer = new int[] {-1};
        }

        return answer;
    }
}