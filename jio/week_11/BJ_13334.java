package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_13334 {
    static int N, D;
    static List<Pair> pairs;
    static class Pair implements Comparable<Pair>{
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair p) {
            return this.end - p.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pairs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int start = Math.min(first, second);
            int end = Math.max(first, second);
            pairs.add(new Pair(start, end));
        }
        D = Integer.parseInt(br.readLine());

        Collections.sort(pairs); //선분(Pair)를 끝점을 기준으로 오름 차순으로 정렬

        PriorityQueue<Integer> startPoints = new PriorityQueue<>(); //각 선분의 시작점을 넣을 우선순위 큐

        //끝점이 가장 작은 선분부터 확인하여
        int maxCount = 0;
        for (int i = 0; i < pairs.size(); i++) {
            Pair cur = pairs.get(i);
            int start = cur.start;
            int end = cur.end;
            if(end - start > D){ //시작점과 끝점 간의 거리가 D를 넘는 경우 불가능하다.
                continue;
            }
            startPoints.offer(start); //가능한 경우이므로 시작점을 queue에 추가
            while(!startPoints.isEmpty()) { //큐에들어있는 시작점들을 보며 현재 보고 있는 선분의 끝점과 거리 비교
                if(end - startPoints.peek() > D){ //거리가 D를 넘는 경우 불가능하므로 queue에서 제거
                    startPoints.poll();
                    continue;
                }
                break; //처음으로 시작점와 끝점의 거리가 D이하인 경우 break, queue에 들어있는 이후 값들은 무조건 가능하므로
            }
            maxCount = Math.max(maxCount, startPoints.size());
        }
        System.out.println(maxCount);
    }
}
