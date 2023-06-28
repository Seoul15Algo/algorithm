import java.util.*;
import java.io.*;

public class Main_20442 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer>dq = new ArrayDeque<>();

        String str = br.readLine();
        char c = str.charAt(0);
        int len = 1;
        int R = 0;

        // for문을 진행하면서 이전 문자와 다른 문자가 나올 때 까지 진행
        for(int i = 1; i < str.length(); i++){
            char now = str.charAt(i);

            if(now == c){
                len++;
                continue;
            }

            // 이번 문자가 R일 경우 R의 갯수 총합에 더하고 K와 구분할 수 있도록 -값으로 변경하여 dq에 추가
            if(c == 'R'){
                R += len;
                len *= -1;
            }
            dq.offer(len);
            len = 1;
            c = now;
        }

        if(c == 'R'){
            R += len;
            len *= -1;
        }
        dq.offer(len);

        int answer = R;
        int left = 0;
        int right = 0;

        while(dq.size() > 1){
            // 왼쪽 끝에 R이 나온 경우
            if(dq.peekFirst() < 0){
                R += dq.pollFirst();
                continue;
            }
            // 오른쪽 끝에 R이 나온 경우
            if(dq.peekLast() < 0){
                R += dq.pollLast();
                continue;
            }

            // 양쪽 모두 K가 나온 경우
            left += dq.peekFirst();
            right += dq.peekLast();
            answer = Math.max(answer, R + 2 * Math.min(left, right));

            // 양쪽 K의 개수를 비교해서 적은 쪽을 먼저 확인해야 한다.
            // 많은 쪽은 이미 K개수가 충분 하기 때문에 적은쪽 K를 채우는 것이 더 긴 길이의 문자열을 만들 수 있기 때문에
            // 양쪽이 같은 경우는 이미 해당 길이의 K값 일때는 확인 한 경우 이므로 양쪽을 모두 들어간다

            if(left == right){
                dq.pollFirst();
                dq.pollLast();
                continue;
            }
            if(left < right){
                right -= dq.peekLast();
                dq.pollFirst();
                continue;
            }
            left -= dq.peekFirst();
            dq.pollLast();
        }

        System.out.println(answer);
    }
}