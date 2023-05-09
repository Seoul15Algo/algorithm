package baekjoon.algo03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11000 {

    static class Lecture implements Comparable {

        public long start;
        public long end;

        public Lecture(long start, long end){
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Object o) {
            Lecture other = (Lecture)o;
            if (this.start < other.start){
                return -1;
            }
            else if(this.start == other.start){
                return Long.compare(this.end, other.end);
            }
            else
                return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Lecture> lectures = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            lectures.offer(new Lecture(start, end));
        }

        PriorityQueue<Long> timetables = new PriorityQueue<>();
        timetables.offer(lectures.poll().end);
        while(!lectures.isEmpty()) {
            long end = timetables.peek();
            Lecture cur = lectures.poll();
            if (end <= cur.start){
                timetables.poll();
            }
            timetables.offer(cur.end);
        }

        System.out.println(timetables.size());
    }
}