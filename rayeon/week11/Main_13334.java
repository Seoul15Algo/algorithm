package Seoul15Algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BJ 13334 철로
public class Main_13334 {
    static class L {
        int start;
        int end;

        public L(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        L[] l = new L[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            l[i] = new L(e < s ? e : s, e < s ? s : e);
        }

        int d = Integer.parseInt(br.readLine());

        Arrays.sort(l, new Comparator<L>() {
            @Override
            public int compare(L o1, L o2) {
                if (o1.end == o2.end)
                    return Integer.compare(o1.start, o2.start);
                return Integer.compare(o1.end, o2.end);
            }
        });

        PriorityQueue<L> pq = new PriorityQueue<>(new Comparator<L>() {
            @Override
            public int compare(L o1, L o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (l[i].end - l[i].start <= d) { // 집과 사무실의 거리가 d 이하인 경우, 현재 철로에 포함
                pq.add(l[i]);
            }

            // 이전 철로에 포함되었던 사람 중 집 또는 사무실이 현재 철로의 범위를 벗어나는 경우, 현재 철로에서 제외
            while (!pq.isEmpty() && pq.peek().start < l[i].end - d) {
                pq.poll();
            }

            answer = Math.max(answer, pq.size());
        }

        System.out.println(answer);
        br.close();
    }
}