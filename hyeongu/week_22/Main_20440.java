import java.io.*;
import java.util.*;

public class Main_20440 {
    static class Mosquito implements Comparable<Mosquito>{
        int start, end;

        public Mosquito(int start, int end){
            this.start = start;
            this.end = end;
        }

        // 시작시간 오름차순, 종료시간 내림차순 정렬
        @Override
        public int compareTo(Mosquito o) {
            if(o.start == this.start){
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Mosquito> order = new PriorityQueue<>();
        PriorityQueue<Integer> room = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        // 최댓값, 시작시간, 종료시간
        int[] answer = new int[3];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            order.add(new Mosquito(start, end));
        }

        int cnt = 0;
        int start_time = -1;

        while(!order.isEmpty()){
            Mosquito now = order.poll();

            // 새로 들어오는 모기보다 먼저 나가는 모기를 모두 내보냄
            // 이때 최댓값이 갱신되는 경우 정답배열 갱신
            while(!room.isEmpty() && room.peek() < now.start){
                if(cnt > answer[0]){
                    answer[0] = cnt;
                    answer[1] = start_time;
                    answer[2] = room.peek();
                }

                room.poll();
                cnt--;
            }

            // 새로 들어오는 모기와 나가는 모기가 바통터치하는 경우
            // 시작시간은 갱신하지 않고 이어서 함
            if(!room.isEmpty() && room.peek() == now.start){
                room.poll();
                room.add(now.end);
                continue;
            }

            // 시작시간 갱신 및 모기 추가
            start_time = now.start;
            room.add(now.end);
            cnt++;
        }

        // 모기가 모두 들어온 뒤 방안에 남아있는 모기 확인
        if(cnt > answer[0]){
            answer[0] = cnt;
            answer[1] = start_time;
            answer[2] = room.peek();
        }
        sb.append(answer[0]).append("\n").append(answer[1]).append(" ").append(answer[2]);
        System.out.println(sb);
    }
}