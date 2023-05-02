package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_24337 {
    static int N, A, B, frontCnt, backCnt, maxHeight;
    static int[] towerHeights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        towerHeights = new int[N];
        maxHeight = Math.max(A, B);

        backCnt = A + B - 1;    // 주어진 A, B 조건을 충족하기 위해 필요한 최소 탑의 개수
        frontCnt = N - backCnt; // N이 backCnt 보다 커서 추가로 앞에 채워주어야 할 1의 개수

        for (int i = 0; i < frontCnt; i++) { // 1을 채워 줌
            towerHeights[i] = 1;
        }

        if(!solve()){
            System.out.println(-1);
        }else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(towerHeights[i]).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static boolean solve(){
        if (N < A + B - 1) {  // N이 조건을 충족하기 위해 필요한 최소 탑의 개수보다 작을 경우
            return false;
        }

        //1. A가 B 이상일 경우
        if(A >= B){
            for (int i = 0; i < maxHeight; i++) {  // 앞에서 1부터 증가하면 채워 줌
                towerHeights[frontCnt + i] = i + 1;
            }

            int cur = N-1;
            int count = 1;

            while(true){  // 뒤에서 1부터 증가하며 채워 줌
                if (towerHeights[cur] != 0) {
                    break;
                }
                towerHeights[cur--] = count++;
            }

            return true;
        }

        //2. B > A 인 경우
        for (int i = 0; i < maxHeight; i++) { // 뒤에서 1부터 증가하며 채워 줌
            towerHeights[N - 1 - i] = i + 1;
        }

        int cur = frontCnt;
        int count = 1;

        while(true){  // 앞에서 1부터 증가하면 채워 줌
            if (towerHeights[cur] != 0) {
                break;
            }
            towerHeights[cur++] = count++;
        }

        // input => 8 1 5
        // output => 1 1 1 5 4 3 2 1 => 5 1 1 1 4 3 2 1

        if (frontCnt != 0 && A == 1) {   // N이 조건을 만족하는 최소 탑의 개수이면서 A가 1인 경우 예외처리
            towerHeights[0] = maxHeight; // 중간에 있던 가장 높은 탑을 제일 앞으로 땡겨 준다.
            towerHeights[frontCnt] = 1;
        }

        return true;
    }
}
