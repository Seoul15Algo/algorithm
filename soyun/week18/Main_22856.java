package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22856 {

    static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int N;
    static Node[] tree;
    static int total;
    static int duplicated;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        total = 0;
        duplicated = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[p] = new Node(a, b);
        }

        circuit(1, 0);

        System.out.println(total - duplicated);
    }

    static void circuit(int root, int depth){

        // 만약 왼쪽 노드가 존재하지 않는다면
        if (tree[root].left == -1){
            duplicated = depth; // 현재 노드가 마지막 노드일 수도 있으므로 duplicated(중복으로 방문하는 간선)를 갱신
        } else {
            total = total + 2;  // 왔다갔다 간선 두 개 더해줌
            circuit(tree[root].left, depth + 1);    // 왼쪽노드 탐색
        }

        // 만약 오른쪽 노드가 존재하지 않는다면
        if (tree[root].right == -1){
            duplicated = depth;
        } else {
            total = total + 2;
            circuit(tree[root].right, depth + 1);
        }
    }
}
