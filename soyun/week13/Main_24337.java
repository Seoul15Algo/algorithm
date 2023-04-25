package baekjoon.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_24337 {

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Deque<Integer> deque = new ArrayDeque<>();

        // 단비가 볼 수 있는 건물의 개수가 더 많을 때(오른쪽부터 채우기)
        if (a < b) {
            // 내림차순
            for (int i = 1; i <= b; i++) {
                deque.addFirst(i);
            }
            // 오름차순
            for (int i = a - 1; i >= 1; i--) {
                deque.addFirst(i);
            }
        }
        // 가희가 볼 수 있는 건물의 개수가 더 많을 때(왼쪽부터 채우기)
        else {
            // 오름차순
            for (int i = 1; i <= a; i++) {
                deque.addLast(i);
            }
            // 내림차순
            for (int i = b - 1; i >= 1; i--) {
                deque.addLast(i);
            }
        }
// ------ 위를 수행한 이후 덱에는 a, b 조건을 만족하는 건물의 높이가 왼쪽부터 차례대로 들어가게 됨

        int fill = N - deque.size();    // 더 추가해줘야 하는 건물 개수 (최솟값을 찾고 있으므로 크기는 1)

        // 음수라면 -> 주어진 N개의 건물로는 조건을 만족할 수 없음을 의미
        if (fill < 0){
            System.out.println(-1);
            return;
        }

        boolean isFilled = false;   // 건물을 추가해줬는지 여부
        // a 조건을 만족하는 건물들 순회
        for (int i = 0; i < a; i++){
            int number = deque.poll();
            // 해당 건물의 크기가 1이라면 -> 크기가 1인 건물을 추가해줘도 조건을 항상 만족함
            if (number == 1){
                for (int j = 0; j < fill; j++) {
                    result.append("1 ");
                }
                isFilled = true;    // 건물을 추가해줬음을 표시
            }
            result.append(number).append(" ");
        }
// -------- 현재 a 조건을 만족하고 있음

        // a 조건 만족한 상태 -> 크기가 1인 건물을 추가해줘도 조건을 항상 만족함
        if (!isFilled){
            for (int j = 0; j < fill; j++) {
                result.append("1 ");
            }
        }

        // b 조건을 만족하는 건물들 순회
        while(!deque.isEmpty()){
            result.append(deque.poll()).append(" ");
        }

        System.out.println(result);
    }
}
