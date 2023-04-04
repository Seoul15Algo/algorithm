package com.ssafy.algo230405_Random2.soyun.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1208 {

    static int N, S;
    static int[] numbers;
    static boolean[] visited;
    static List<Long> left, right;
    static long total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        left = new ArrayList<>();
        right = new ArrayList<>();
        visited = new boolean[N];
        // 2^40 -> 두 개로 나누면 2^20이 됨
        // left, right 로 나누어 각각의 부분집합 합을 List 에 저장
        powerSet(0, N / 2, 0, false);
        powerSet(N / 2, N, 0, true);

        // 정렬 -> 이분탐색 할라고
        Collections.sort(left);
        Collections.sort(right);

        total = 0;
        // upperBound 와 lowerBound 를 사용하여 left 에 있는 숫자가 right 에 몇 개나 등장했는지 count
        for (int i = 0; i < left.size(); i++) {
            total = total + upperBound(left.get(i)) - lowerBound(left.get(i));
        }
        if (S == 0){
            System.out.println(total - 1);
        }
        else {
            System.out.println(total);
        }
    }

    static void powerSet(int n, int r, long sum, boolean isRight){
        if (n == r){
            if (isRight){
                right.add(sum);
                return;
            }
            // S = left.get(i) + right.get(i)이므로
            // S - left.get(i) = right.get(i)
            left.add(S - sum);
            return;
        }

        visited[n] = true;
        powerSet(n + 1, r, sum + numbers[n], isRight);
        visited[n] = false;
        powerSet(n + 1, r, sum, isRight);
    }

    static long lowerBound(long target){
        int start = 0;
        int end = right.size();
        while (start < end){
            int mid = (start + end) / 2;
            if (right.get(mid) >= target){
                end = mid;
                continue;
            }
            start = mid + 1;
        }
        return start;
    }

    static long upperBound(long target){
        int start = 0;
        int end = right.size();
        while (start < end){
            int mid = (start + end) / 2;
            if (right.get(mid) > target){
                end = mid;
                continue;
            }
            start = mid + 1;
        }
        return start;
    }
}