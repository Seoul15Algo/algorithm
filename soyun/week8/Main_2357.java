package com.ssafy.baekjoon.study.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2357 {

    static class SegmentTree {

        int size;
        int[][] tree;   // 행 값이 0: minValue, 1: maxValue

        // 주어진 배열로 Segment Tree 를 생성
        public SegmentTree(int[][] arr, int size) {
            build(arr, size);
        }

        // left partition, right partition을 합침 -> 그때의 최소/최댓값 반환
        private int[] merge(int[] left, int[] right) {
            return new int[] {Math.min(left[0], right[0]), Math.max(left[1], right[1])};
        }

        // Segment Tree 만들기
        public void build(int[][] arr, int size) {
            this.size = size;
            this.tree = new int[2][size * 4];   // 2N <= 트리의 노드 개수 <= 4N

            buildRec(arr, 1, 0, size - 1);
        }

        private int[] buildRec(int[][] arr, int node, int nodeLeft, int nodeRight) {
            // 자식노드가 하나인 경우의 최소/최대값 -> 해당 노드의 값
            if (nodeLeft == nodeRight) {
                tree[0][node] = arr[0][nodeLeft];
                tree[1][node] = arr[1][nodeLeft];
                return new int[]{tree[0][node], tree[1][node]};
            }

            // mid를 기준으로
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;    // (nodeLeft + nodeRight) / 2 를 해도 무방하나, overflow 방지

            // leftVal, rightVal 나누어서 재귀 돈다 -> 반환 값은 해당 partition에서의 최소/최대값
            int[] leftVal = buildRec(arr, node * 2, nodeLeft, mid);
            int[] rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

            // 두 파티션을 합쳤을 때의 최소/최대값을 반환
            int[] result = merge(leftVal, rightVal);
            tree[0][node] = result[0];
            tree[1][node] = result[1];

            return new int[] {tree[0][node], tree[1][node]};
        }


        // (left, right) 범위의 연산 결과 구하기
        public int[] query(int left, int right) {
            return queryRec(left, right, 1, 0, size - 1);
        }

        private int[] queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {

            // 파티션이 내가 처리하려는 구간을 벗어나는 경우 -> 범위 밖의 값이므로 default value 반환
            if (left > nodeRight || right < nodeLeft) {
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};   // default value
            }

            // 파티션이 처리하려는 구간에 완전히 포함되는 경우 -> 그 구간의 연산 결과를 리턴
            if (left <= nodeLeft && right >= nodeRight) {
                return new int[] {tree[0][node], tree[1][node]};
            }

            // 파티션이 처리하려는 구간에 걸치는 경우 (구간에 벗어나는 애들과 포함되는 애들을 나누어서 계산해야 하기 때문)

            // mid를 기준으로
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

            // 두 파티션으로 나뉘어 queryRec 수행
            return merge(
                    queryRec(left, right, node * 2, nodeLeft, mid),
                    queryRec(left, right, node * 2 + 1, mid + 1, nodeRight)
            );
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[2][N];

        for (int i = 0; i < N; i++) {
            arr[0][i] = Integer.parseInt(br.readLine());
            arr[1][i] = arr[0][i];
        }

        // Segment Tree 생성
        SegmentTree segmentTree = new SegmentTree(arr, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int[] result = segmentTree.query(a, b); // (a, b) 구간을 기준으로 query 수행
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        System.out.println(sb);
    }
}