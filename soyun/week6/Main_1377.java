package com.ssafy.algo230307_Sort.soyun.week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1377 {

    static int N;
    static int[] arr;   // 일반 배열
    static int[] indexes;   // 일반 배열 숫자들의 인덱스
    static int[] sorted;    // 정렬 배열
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        indexes = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            indexes[i] = i;
        }

        sorted = Arrays.copyOf(arr, N + 1);
        Arrays.sort(sorted);


        // 로직
        // 일반 배열 숫자의 index 가 정렬 배열에서 어느 index 에 위치하는 지 탐색
        // 두 index 의 차이를 구하면 해당 수가 얼만큼 이동했는지 알 수 있음
        // (음: 왼쪽으로 차이만큼 이동, 양: 오른쪽으로 차이만큼 이동)
        // index 차이가 음수인 것들(음의 방향으로 이동) 중 절댓값이 가장 큰 수만큼 정렬 알고리즘이 돈다
        // (큰 수는 필연적으로 뒤로 밀리기 때문에 앞으로 당겨지는 작은 수들 중 가장 많이 이동한 횟수만큼 정렬 알고리즘 돎)
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, Math.abs(binarySearch(arr[i]) - indexes[i]));
        }

        System.out.println(Math.abs(min) + 1);
    }

    // 이분탐색을 통해 정렬 배열에서 해당 숫자가 나타나는 인덱스 반환
    // upper_bound: 동일한 수가 여러개라면 가장 마지막 수의 인덱스 반환 -> 동일한 숫자끼리는 정렬할 필요가 없기 때문
    static int binarySearch(int target){

        int start = 1;
        int end = N + 1;
        while (start < end){
            int mid = (start + end) / 2;
            if (sorted[mid] <= target){
                start = mid + 1;
                continue;
            }
            if (sorted[mid] > target){
                end = mid;
            }
        }
        return start - 1;   // start: 찾는 수보다 처음으로 큰 수가 나오는 index
    }
}
