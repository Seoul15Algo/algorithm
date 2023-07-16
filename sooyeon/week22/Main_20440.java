package com.ssafy.algo230405_Random2.sooyeon.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20440 {
    static int N;
    static int[][] mos;
    static int count, resultS, resultE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        mos = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            mos[i][0] = start;
            mos[i][1] = end;
        }

        //정렬 -> 오름차순
        Arrays.sort(mos,(a,b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }else {
                return a[0] - b[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(mos[0][1]);
        count = 1;
        resultS = mos[0][0];
        resultE = mos[0][1];

        for (int i = 1; i < N; i++) {
            while(!pq.isEmpty() && pq.peek() < mos[i][0]) {
                pq.poll();
            }
            if(!pq.isEmpty() && pq.peek() == mos[i][0]) { //끝나는 부분이 같으면 pq에서 빼준다.
                if(pq.peek() == resultE) {
                    resultE = mos[i][1];
                }
                pq.poll();
            }

            pq.add(mos[i][1]);
            if(pq.size() > count) {
                count = pq.size();
                resultS = mos[i][0];
                resultE = pq.peek();
            }
        }

        System.out.println(count);
        System.out.println(resultS + " "+ resultE);
    }
}