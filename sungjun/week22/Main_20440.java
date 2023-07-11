package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20440 {
    static int N, resultStart, resultEnd, resultCount;
    static Mosquito[] mos;      // 모기 입장, 퇴장 시간 
    static PriorityQueue<Integer> pq;   // 퇴장 시간

    static class Mosquito implements Comparable<Mosquito> {
        int start;
        int end;

        public Mosquito(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Mosquito o) {
            if(this.start == o.start) return Integer.compare(this.end, o.end);
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mos = new Mosquito[N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mos[i] = new Mosquito(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(mos);     // 입장 시간이 빠른 순서로 정렬, 입장 시간이 같다면 퇴장 시간이 빠른 순서로 정렬

        resultCount = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            // 현재 모기의 입장 시간이 이전 모기의 퇴장시간보다 이후라면
            // 이전 모기는 퇴장한 상태이기 때문에 큐에서 뽑아주고 카운트 감소
            while(!pq.isEmpty() && pq.peek() < mos[i].start) {
                pq.poll();
                count--;
            }

            // 현재 모기의 입장 시간이 이전 모기의 퇴장시간과 같다면
            // 만약 이전 모기의 퇴장시간이 최대 모기 포함 구간의 끝과 같다면 끝 지점을 갱신
            // ex) [12, 16] 모기를 포함하는 구간 8~16이 모기 3마리로 현재까지 최대치인 상황에서
            // [16, 19] 모기가 새로 입장했다면 모기 개수는 그대로이지만 구간이 8~19로 갱신됨
            // 아래에서는 같은 마리수일때는 빠른 시작 시간을 보장하기 위해서 최대값이 갱신되는 경우에만 시작 시각과 종료 시각을 갱신하기 때문에 최대 모기 수가 변하지 않는 경우에는 이렇게 따로 갱신을 해줘야 한다
            if(!pq.isEmpty() && pq.peek() == mos[i].start) {
                if(pq.peek() == resultEnd) {
                    resultEnd = mos[i].end;
                }

                pq.poll();
                count--;
            }

            // 현재 모기의 퇴장 시간 큐에 넣고 카운트 증가
            pq.add(mos[i].end);
            count++;

            // 최대 마리수 갱신 시 시작 시각과 종료 시각 같이 갱신
            if(count > resultCount) {
                resultCount = count;
                resultStart = mos[i].start;
                resultEnd = pq.peek();
            }
        }

        System.out.println(resultCount);
        System.out.println(resultStart + " " + resultEnd);
    }
}