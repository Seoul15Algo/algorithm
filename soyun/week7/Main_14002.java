package com.study.algo0315.soyun.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14002 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] sorted = new int[n + 1];
        int[] sortedSet = new int[n + 1];
        int[][] LCS = new int[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        int len = removeDuplicated(sorted, sortedSet);  // 중복 제거 -> ex. 10 10은 증가하는 수열이 아님!

        int result = 0;
        // LCS 배열을 완성한다
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < len; j++) {
                if (arr[i] == sortedSet[j]){
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    result = Math.max(result, LCS[i][j]);
                    continue;
                }

                LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                result = Math.max(result, LCS[i][j]);
            }
        }

        /*
        ex) X 10 20 10 30 20 50
          X 0  0  0  0  0  0  0
         10 0  1  1  2  2  2  2
         20 0  1  2  2  2  3  3
         30 0  1  2  2  3  3  3
         50 0  1  2  2  3  3  4

            2차원 배열의 가장 마지막 인덱스부터 BFS 를 돌림
            해당 배열의 원소(arr[i][j])와
            arr[i - 1][j], arr[i][j - 1] 원소가 다르다면
            해당 원소는 부분수열 숫자임!
            부분 수열 숫자를 찾았다면 arr[i - 1][j - 1]로 이동하여 또 BFS 탐색

         */
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        int[] dx = {-1, 0};
        int[] dy = {0, -1};
        boolean[][] visited = new boolean[n + 1][len];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{n, len - 1});
        while (!q.isEmpty()){

            int[] cur = q.poll();
            int check = 0;
            for (int d = 0; d < 2; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (visited[nx][ny]){
                    continue;
                }
                if (LCS[nx][ny] == LCS[cur[0]][cur[1]]){
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    continue;
                }
                check++;
            }
            if (check == 2){
                stk.push(arr[cur[0]]);
                if (LCS[cur[0]][cur[1]] == 1){
                    break;
                }
                q.clear();
                q.offer(new int[]{cur[0] - 1, cur[1] - 1});
            }
        }
        System.out.println(result);
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb);
    }

    // 정렬된 배열의 중복을 제거하는 메소드
    // 반환값은 배열의 유효한 길이이다
    static int removeDuplicated(int[] sorted, int[] sortedSet){

        int cnt = 2;
        sortedSet[1] = sorted[1];
        for (int i = 2; i < sorted.length; i++) {
            if (sortedSet[cnt - 1] == sorted[i]){
                continue;
            }
            sortedSet[cnt] = sorted[i];
            cnt++;
        }
        return cnt;
    }
}