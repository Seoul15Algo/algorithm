package baekjoon.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다시 풀겠습니다,,,
public class Main_20440 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sarr [] = new int [N];
        int earr [] = new int [N];
        TreeSet<Integer> temp = new TreeSet<Integer>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sarr[i] = s;
            earr[i] = e;
            temp.add(s);
            temp.add(e);
        }
        ArrayList<Integer> idx = new ArrayList<Integer>(temp);
        int sum[] = new int[idx.size()];
        for (int i = 0; i < N; i++) {
            int x = Collections.binarySearch(idx, sarr[i]);
            int y = Collections.binarySearch(idx, earr[i]);
            for (int j = x; j < y; j++)
                sum[j]++;
        }
        int max = 0;
        int maxidx = -1;
        int maxendidx = -1;
        for (int i = 0; i < idx.size(); i++) {
            if (sum[i] > max) {
                maxidx = i;
                maxendidx = i;
                max = sum[i];
            }
            if (sum[i] == max && i - 1 == maxendidx) {
                maxendidx = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max+"\n"+idx.get(maxidx)+" "+idx.get(maxendidx + 1));
        System.out.print(sb);
    }
}
