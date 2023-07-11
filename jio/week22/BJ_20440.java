package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_20440 {
    static int N;

    static PriorityQueue<Point> points;
    static class Point implements Comparable<Point>{
        int pos, isStart;

        public Point(int pos, int isStart) {
            this.pos = pos;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point p) {
            if(this.pos == p.pos) { // 위치가 같은 경우 끝점이 더 먼저 오도록 한다.
                return Integer.compare(this.isStart, p.isStart);
            }
            return Integer.compare(this.pos, p.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        points = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            points.offer(new Point(start, 1));
            points.offer(new Point(end, 0));
        }

        int mosquito = 0; // 현재 모기 수
        int maxMosquito = 0; // 최대 모기 수
        int maxMosquitoStartTime = 0; // 최대 모기 시작 시간
        int maxMosquitoEndTime = 0; // 최대 모기 종료 시간
        boolean needEndTime = false; // 최대 모기 종료 시간을 정했는지 유무
        boolean isContinue = false;  // 모기가 같은 시간대에 동시에 나가고 들어오는 지 유무

        while (!points.isEmpty()) {
            Point cur = points.poll();

            if (cur.isStart == 1) { // 모기가 입장한 경우
                mosquito++;

                if (mosquito > maxMosquito) { // 최대 모기 수를 갱신했을 때
                    maxMosquitoStartTime = cur.pos;
                    needEndTime = true;
                }
            }

            if (cur.isStart == 0) { // 모기가 퇴장한 경우
                mosquito--;

                if (needEndTime) {
                    // 현재는 모기가 퇴장 중이지만 다시 같은 시간에 모기가 입장하는 경우
                    if (points.size() > 1 && points.peek().pos == cur.pos && points.peek().isStart == 1) {
                        maxMosquito = Integer.max(maxMosquito, mosquito);
                        continue;
                    }
                    maxMosquitoEndTime = cur.pos; // 모기 퇴장 시간 갱신
                    needEndTime = false;
                }
            }

            maxMosquito = Integer.max(maxMosquito, mosquito);
        }

        System.out.println(maxMosquito);
        System.out.println(maxMosquitoStartTime + " " + maxMosquitoEndTime);
    }
}
