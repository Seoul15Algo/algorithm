package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15732 {

    static class Rule {
        int start;
        int end;
        int step;

        public Rule(int start, int end, int step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }
    }

    static int N, K, D;
    static Rule[] rules;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        rules = new Rule[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int step = Integer.parseInt(st.nextToken());
            rules[i] = new Rule(start, end, step);
        }

        System.out.println(findLast(1, N));
    }

    static long findLast(long low, long high){
        long answer = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2; // 마지막 도토리가 담긴 상자라고 가정

            // 답이 될 수 있는 경우
            if (isPossible(mid)){
                // mid의 앞쪽에 있는 상자에 마지막 도토리가 담길 수도 있으므로 한 칸 앞으로 이동
                high = mid - 1;
                answer = mid;   // 일단 정답일수도 있으니까 갱신
                continue;
            }

            // 담아야 할 도토리 개수보다 적게 담은 경우
            // mid의 뒷쪽에 있는 상자에 마지막 도토리가 담겨 있으므로 한 칸 뒤로 이동
            low = mid + 1;
        }
        return answer;
    }

    static boolean isPossible(long mid){
        long acorns = 0;
        for (Rule rule : rules) {
            // mid가 규칙의 시작지점보다 작다면 -> 넣을 수 있는 도토리 없음
            if (mid < rule.start){
                continue;
            }
            // mid가 규칙의 종료지점보다 크다면 -> 시작지점 ~ 종료지점까지의 상자에 넣을 수 있는 도토리 수 계산
            else if (mid > rule.end){
                acorns = acorns + (rule.end - rule.start) / rule.step + 1;
            }
            // 그 외의 경우: mid가 start와 end 사이에 있는 경우 -> 시작지점 ~ mid까지의 상자에 넣을 수 있는 도토리 수 계싼
            else {
                acorns = acorns + (mid - rule.start) / rule.step + 1;
            }
        }

        // 주어진 도토리를 모두 넣을 수 있는 경우 -> 일단은 답이 될 수 있음
        if (acorns >= D){
            return true;
        }
        return false;
    }
}
