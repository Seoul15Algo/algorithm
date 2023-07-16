package com.ssafy.algo230405_Random2.sooyeon.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21943 {
    static int N;
    static int[] nums;
    static int[] permNums;
    static int[] calcs;
    static boolean[] isPlus;
    static boolean[] visited;
    static int plusNum, mulNum;
    static int result;
    static ArrayList<Integer> list ;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        isPlus= new boolean[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //더하기, 곱하기 개수 구하기
        st = new StringTokenizer(br.readLine());
        plusNum = Integer.parseInt(st.nextToken());
        mulNum = Integer.parseInt(st.nextToken());
        
        //순서 배치 정하기
        permNums= new int[N];
        visited= new boolean[N];
        perm(0);


        System.out.println(result);
        
    }

    private static void perm(int cnt) {
        if(cnt == N) {
            //plus의 개수만큼 combi 돌리기
            combi(0,0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            permNums[cnt] = nums[i];
            perm(cnt+1);
            permNums[cnt] = 0;
            visited[i] = false;
        }

    }

    private static void combi(int cnt,int start) {
        if(cnt == plusNum) {
            //계산 -> plus 먼저, 그후 계산
            result = Math.max(result, calc());
            return;
        }
        for (int i = start; i < N-1; i++) {
            isPlus[i] = true;
            combi(cnt+1, i+1);
            isPlus[i] = false;
        }
    }

    private static int calc() {
        int calc = 1;
        calcs = new int[N];
        calcs = Arrays.copyOf(permNums,N);
        list = new ArrayList<>(); //곱할수들 저장할 리스트


        //더하기 먼저 계산
        for (int i = 0; i < N-1; i++) {
            if(isPlus[i]) {
                calcs[i+1] = calcs[i] = calcs[i] + calcs[i+1];
                //끝이거나 끝이아닌데 다음연산이 x 이면 리스트에 넣어줌
                if((i == N-2) || (i != N-2 && !isPlus[i+1])) {
                    list.add(calcs[i]);
                }
            }
            if(!isPlus[i]) { //곱하기 일 경우
                if(i != 0 && isPlus[i-1]) continue; //첫번째가 아니고, 이전 연산이 + 인 경우 이미 리스트에 넣었기 때문에 continue
                if(i == N-2) { //끝이면 리스트에 값 마지막 값과 함께 리스트에 넣어줌
                    list.add(calcs[i]);
                    list.add(calcs[i+1]);
                }
                if(i!=N-2) { //끝이아니면
                    list.add(calcs[i]);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            calc *= list.get(i);
        }

        return calc;
    }
}
