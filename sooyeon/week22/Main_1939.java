package com.ssafy.algo230405_Random2.sooyeon.week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1939 {
    static int N,M;
    static ArrayList<Node> [] list;
    static class Node {
        int next;
        long w;

        public Node(int next, long w) {
            this.next = next;
            this.w = w;
        }
    }
    static int start,end;
    static long left = 0,right = Long.MIN_VALUE;
    static boolean visited[];
    static long result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) { //리스트 초기화
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());


        for (int i = 0; i < list[end].size(); i++) {
            right = Math.max(right, list[end].get(i).w);
        }

        //이분탐색
        while(left<=right) {
            long mid = (left+right) / 2;

            if(bfs(mid)) {
                left = mid +1;
                result = Math.max(result, mid);
            }else {
                right = mid-1;
            }
        }

        System.out.println(result);

    }

    private static boolean bfs(long mid) {
        visited = new boolean[N+1];
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < list[cur].size(); i++) {

                if(list[cur].get(i).w>=mid && list[cur].get(i).next == end) {
                    return true;
                }

                if(!visited[list[cur].get(i).next] && list[cur].get(i).w >= mid) {
                    visited[list[cur].get(i).next] = true;
                    q.offer(list[cur].get(i).next);
                }
            }

        }

        return false;
    }
}