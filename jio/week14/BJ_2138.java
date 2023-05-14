package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2138 {
    static int N;
    static int[] chooseFirst, notChooseFirst, target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[] chars = br.readLine().toCharArray();
        chooseFirst = new int[N];     // 첫 번째 스위치를 누른 경우
        notChooseFirst = new int[N];  // 첫 번째 스위치를 누르지 않은 경우

        for (int i = 0; i < N; i++) {
            chooseFirst[i] = chars[i] - '0';
            notChooseFirst[i] = chars[i] - '0';
        }

        chars = br.readLine().toCharArray();
        target = new int[N];
        for (int i = 0; i < N; i++) {
            target[i] = chars[i] - '0';
        }

        // 첫 번째 스위치를 눌러준다.
        chooseFirst[0] ^= 1;
        chooseFirst[1] ^= 1;

        int chooseFirstCnt = simulation(chooseFirst) + 1; // 첫 번째 스위치를 켰으므로 +1
        int notChooseFirstCnt = simulation(notChooseFirst);

        if (!isSame(chooseFirst) && !isSame(notChooseFirst)) { // 두 경우 모두 target과 다를 경우
            System.out.println(-1);
        }
        if (isSame(chooseFirst) && !isSame(notChooseFirst)) { // 첫 번째 스위치를 킨 경우에만 target과 같을 경우
            System.out.println(chooseFirstCnt);
        }
        if (!isSame(chooseFirst) && isSame(notChooseFirst)) { // 두 번째 스위치를 킨 경우에만 target과 같을 경우
            System.out.println(notChooseFirstCnt);
        }
        if (isSame(chooseFirst) && isSame(notChooseFirst)) { // 두 경우 모두 target과 같을 경우 더 작은 값을 선택
            System.out.println(Math.min(chooseFirstCnt, notChooseFirstCnt));
        }
    }

    private static int simulation(int[] lights) { // 두 번째 스위치 부터 순차적으로 스위치를 켤지 말지 결정
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (lights[i - 1] != target[i - 1]) {
                lights[i - 1] ^= 1;  // 0 -> 1 or 1 -> 0
                lights[i] ^= 1;
                if (i + 1 < N) {
                    lights[i + 1] ^= 1;
                }
                ans++;
            }
        }
        return ans;
    }

    private static boolean isSame(int[] lights) { // 시뮬레이션이 끝난 후 목표한 상태(target)와 일치하는 지 확인
        for (int i = 0; i < N; i++) {
            if (lights[i] != target[i]) {
                return false;
            }
        }
        return true;
    }
}
