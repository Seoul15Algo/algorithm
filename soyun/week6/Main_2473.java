package com.ssafy.algo230307_Sort.soyun.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {

    static int N;
    static long[] solutions;
    static long min;
    static long[] answer;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        solutions = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solutions);

        min = Long.MAX_VALUE;
        answer = new long[3];

        for (int i = 0; i < N - 2; i++) {

            for (int j = i + 1; j < N - 1; j++){

                // 두 개의 용액은 고른 상태
                long target = solutions[i] + solutions[j];
                // 하나의 용액만을 선택하면 됨 -> 이분탐색으로 0과 가장 가까운 값을 택함
                int index = binarySearch(target, j);
                if (Math.abs(target + solutions[index]) < min){
                    min = Math.abs(target + solutions[index]);
                    answer[0] = solutions[i];
                    answer[1] = solutions[j];
                    answer[2] = solutions[index];
                }
            }
        }
        System.out.printf("%d %d %d", answer[0], answer[1], answer[2]);
    }

    // 이분탐색으로 마지막 용액을 탐색
    static int binarySearch(long target, int s){
        int start = s + 1;
        int end = N - 1;
        int mid = -1;
        while (start <= end){
            mid = (start + end) / 2;
            if (solutions[mid] + target == 0){
                return mid;
            }
            if (solutions[mid] + target < 0){
                start = mid + 1;
            }
            if (solutions[mid] + target > 0){
                end = mid - 1;
            }
        }

        // 예외 - 중복 선택을 하게 되는 경우
        // -24 -6 -3 -2 61 98 100
        // i = 2, j = 3일 때, 61 98 100에 대하여 이분탐색 진행
        // 그러나 solutions[mid] + target 이 항상 0보다 크기 때문에 mid 는 61을 가리키게 됨 
        // (s + 1 == mid)인 상태이고, mid - 1값은 이전 값을 중복으로 택하기 떄문에 제외시켜줘야 함
        if (s + 1 == mid){
            return mid;
        }

        long sum1 = target + solutions[mid];
        long sum2 = target + solutions[mid - 1];

        if (Math.abs(sum1) < Math.abs(sum2)){
            return mid;
        }
        return mid - 1;
    }
}
