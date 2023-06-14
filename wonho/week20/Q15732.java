package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q15732 {

    static class Dotori {
        private int start;
        private int end;
        private int gap;

        public Dotori(String start, String end, String gap) {
            this.start = Integer.parseInt(start);
            this.end = Integer.parseInt(end);
            this.gap = Integer.parseInt(gap);
        }
    }

    private static List<Dotori> dotoris = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkd = br.readLine().split(" ");
        int n = Integer.parseInt(nkd[0]);
        int k = Integer.parseInt(nkd[1]);
        int d = Integer.parseInt(nkd[2]);

        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            dotoris.add(new Dotori(input[0], input[1], input[2]));
        }

        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (left + right) / 2;

            long count = 0;
            int dotoriSize = dotoris.size();
            for (int i = 0; i < dotoriSize; i++) {
                int start = dotoris.get(i).start;
                int end = dotoris.get(i).end;
                int gap = dotoris.get(i).gap;

                if (start > mid) {
                    continue;
                }

                if (end <= mid) {
                    count += (end - start) / gap + 1;
                    continue;
                }

                count += (mid - start) == 0 ? 1 : (mid - start) / gap + 1;
            }

            if (count >= d) {
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }
        System.out.println(left);
    }
}
