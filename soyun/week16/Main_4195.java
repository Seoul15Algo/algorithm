package com.ssafy.baekjoon.random;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195 {

    static int[] parent;
    static int[] rank;
    static Map<String, Integer> map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            rank = new int[F * 2];

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            int idx = 0;
            map = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st  = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                // (아이디 : 인덱스 번호) Map에 저장
                if (!map.containsKey(a)) {
                    map.put(a, idx++);
                }
                if (!map.containsKey(b)) {
                    map.put(b, idx++);
                }

                int count = union(map.get(a), map.get(b));
                result.append(count).append("\n");
            }
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static int union(int x, int y) {
        x = find(x);
        y = find(y);

        // 같은 부모 -> 연산해줄 필요 없음
        if (x == y) {
            return rank[x];
        }

        // x의 랭크가 더 클 경우 -> x를 부모로
        if (rank[x] > rank[y]){
            parent[y] = x;
            rank[x] = rank[x] + rank[y];
            return rank[x];
        }
        // x와 y의 랭크가 같을 경우 -> 둘 중 아무나 부모가 되어도 상관 X
        else if (rank[x] == rank[y]){
            parent[y] = x;
            rank[x] = rank[x] + rank[y];
            return rank[x];
        }
        // y의 랭크가 더 클 경우 -> y를 부모로
        else {
            parent[x] = y;
            rank[y] = rank[y] + rank[x];
            return rank[y];
        }
    }
}
