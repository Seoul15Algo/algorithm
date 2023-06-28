import java.util.*;
import java.io.*;

public class Main_15732 {
    static class Rule{
        int start, end, term;

        public Rule(int start, int end, int term){
            this.start = start;
            this.end = end;
            this.term = term;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Rule> list = new ArrayList<>();

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int term = Integer.parseInt(st.nextToken());

            list.add(new Rule(start, end, term));
        }

        // 1부터 N 까지 이분탐색을 통해 마지막 도토리를 찾음
        int left = 1;
        int right = N;

        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;

            for(int i = 0; i < K; i++){
                Rule now = list.get(i);

                // 현재 값보다 규칙의 시작 값이 큰 경우 continue
                if(now.start > mid){
                    continue;
                }

                // 현재 값과 규칙의 마지막 값 중에서 더 작은 값을 구하고 거기까지 count
                int last = Math.min(mid, now.end);
                cnt += ((last - now.start) / now.term) + 1;

                // 중간에 이미 count 값이 D값을 넘어갈 경우 break
                if(cnt > D){
                    break;
                }
            }

            // cnt == D 여도 비어있는 상자가 선택 될 수 있기 때문에
            // 조건을 만족하는 가장 작은 상자 즉 도토리가 들어있는 상자까지 탐색을 진행한다
            if(cnt >= D){
                right = mid - 1;
            }
            if(cnt < D){
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}
