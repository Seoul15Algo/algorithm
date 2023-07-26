import java.util.*;
public class 두_큐_합_같게_만들기 {
    Deque<Integer> dq1 = new ArrayDeque<>();
    Deque<Integer> dq2 = new ArrayDeque<>();
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0;
        long sum2 = 0;

        for(int i = 0; i<queue1.length; i++){
            dq1.offer(queue1[i]);
            sum1 += queue1[i];
        }
        for(int i = 0; i<queue2.length; i++){
            dq2.offer(queue2[i]);
            sum2 += queue2[i];
        }

        while(sum1 != sum2){
            if(sum1>sum2){
                int tmp = dq1.poll();
                dq2.offer(tmp);
                sum1 -= tmp;
                sum2 += tmp;
            }
            else{
                int tmp = dq2.poll();
                dq1.offer(tmp);
                sum2 -= tmp;
                sum1 += tmp;
            }
            answer++;
            if(answer > queue1.length * 2){
                return -1;
            }
        }
        return answer;
    }
}
