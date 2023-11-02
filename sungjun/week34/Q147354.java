package week34;
import java.util.*;

class Q147354 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (o1, o2) -> {
            if(o1[col-1] == o2[col-1]) {
                return Integer.compare(o2[0], o1[0]);
            }
            return Integer.compare(o1[col-1], o2[col-1]);
        });

        int[] si = new int[row_end-row_begin+1];

        for(int i = row_begin-1; i < row_end; i++) {
            int[] cur = data[i];
            int sum = 0;

            for(int j = 0; j < cur.length; j++) {
                sum += (cur[j] % (i+1));
            }

            si[i-row_begin+1] = sum;
        }

        answer = si[0];

        for(int i = 1; i < si.length; i++) {
            answer = answer^si[i];
        }

        return answer;
    }
}