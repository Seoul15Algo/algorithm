package com.ssafy.algo230228_DataStructure.soyun.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2533 {

    static class Node {
        int root;
        Node child;

        public Node(int root) {
            this.root = root;
        }

        public Node(int root, Node child) {
            this.root = root;
            this.child = child;
        }
    }

    static Node[] nodes;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // s 노드의 연결관계에 e 추가 (끼워넣기)
            nodes[s].child = new Node(e, nodes[s].child);
            // e 노드의 연결관계에 s 추가 (끼워넣기)
            nodes[e].child = new Node(s, nodes[e].child);
        }

        dfs(1, 0);
        System.out.println(count);
    }


    private static boolean dfs(int n, int prev) {
        boolean isLeaf = true;

        // 해당 노드와 연결된 노드들을 순회
        for (Node node = nodes[n].child; node != null; node = node.child) {
            // 이전에 탐색했던 노드였다면 넘어감 (가지치기)
            if (node.root == prev) {
                continue;
            }

            // 연결된 노드 중 리프노드가 존재한다면 -> 얼리어답터가 되어야 함
            if (dfs(node.root, n)) {
                isLeaf = false;
            }
        }

        if (!isLeaf) {
            count++;
        }
        return isLeaf;
    }

}