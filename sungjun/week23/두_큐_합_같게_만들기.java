package week23;

import java.util.*;

class 두_큐_합_같게_만들기 {
    public int solution(int[] q1, int[] q2) {
        int answer = 0;

        long sum1 = 0;
        long sum2 = 0;

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        for(int i = 0; i < q1.length; i++) {
            sum1 += q1[i];
            sum2 += q2[i];

            queue1.add(q1[i]);
            queue2.add(q2[i]);
        }

        long sum = sum1 + sum2;

        // 두 큐의 총 합이 홀수이면 같게 만드는 것이 불가능하다
        if(sum % 2 == 1) return -1;

        int n = q1.length;

        // 1 1 1 1
        // 1 1 1 9 와 같은 경우
        // 한쪽 큐가 빈 상태로 1 1 1 1 1 1 1 9 까지 갔다가 다시 돌아오는 과정을 거쳐야 하기에 n*3까지 탐색해야 한다
        // 큐1이 전부 비고 큐2로 꽉 찬 상태(n번 실행) 큐1과 큐2의 모든 원소가 뒤바뀐 상태(n*2번 실행) 큐1이 꽉 차고 큐2가 빈 상태(n*3번 실행)
        while(answer < n*3) {
            if(sum1 == sum2) return answer;

            // 더 큰쪽에서 수를 뽑아 다른쪽에 더해준다
            if(sum1 > sum2) {
                int tmp = queue1.poll();
                sum1 -= tmp;
                sum2 += tmp;
                queue2.add(tmp);
                answer++;

                continue;
            }

            int tmp = queue2.poll();
            sum1 += tmp;
            sum2 -= tmp;
            queue1.add(tmp);
            answer++;
        }

        return -1;
    }
}