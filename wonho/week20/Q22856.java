package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q22856 {

    private static int n;
    private static int[][] tree;
    private static int visitCount;
    private static int lastNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        tree = new int[(n + 1) * 4][2];
        visitCount = 0;
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int left = Integer.parseInt(input[1]);
            int right = Integer.parseInt(input[2]);
            tree[parent][0] = left;
            tree[parent][1] = right;
        }

        inOrder(1);
        search(1);
        System.out.println(visitCount);
    }

    public static void inOrder(int cur) {
        if (tree[cur][0] != -1) {
            inOrder(tree[cur][0]);
        }
        lastNode = cur;
        if (tree[cur][1] != -1) {
            inOrder(tree[cur][1]);
        }
    }

    public static void search(int cur) {
        if (tree[cur][0] != -1) {
            visitCount++;
            search(tree[cur][0]);
            if (tree[cur][0] == lastNode) {
                System.out.println(visitCount);
                System.exit(0);
            }
            visitCount++;
        }
        if (tree[cur][1] != -1) {
            visitCount++;
            search(tree[cur][1]);
            if (tree[cur][1] == lastNode) {
                System.out.println(visitCount);
                System.exit(0);
            }
            visitCount++;
        }
    }
}