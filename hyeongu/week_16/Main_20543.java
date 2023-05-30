import java.util.*;
import java.io.*;
public class Main_20543 {
    static List<Column> list;
    static class Column{
        long sum;
        Queue<Long> q;

        public Column(long sum, Queue<Long>q) {
            this.sum = sum;
            this.q = q;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int range = M / 2;

        list = new ArrayList<>();
        long[][] arr = new long[N][N];
        long[][] answer = new long[N][N];

        for(int i = 0; i < N; i++) {
            list.add(new Column(0, new LinkedList<>()));
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken()) * -1;
            }
        }

        for(int i = range; i < N - range; i++) {
            if(i - range >= M) {
                for(int j = range; j < N - range; j++) {
                    list.get(j).sum -= list.get(j).q.poll();
                }
            }

            long sum = 0;
            for(int k = 0; k <= range; k++) {
                sum += list.get(k).sum;
            }

            for(int j = range; j < N - range; j++) {
                answer[i][j] = arr[i - range][j - range] - sum;
                list.get(j).sum += answer[i][j];
                list.get(j).q.offer(answer[i][j]);

                sum += answer[i][j];
                if(j + 1 < N ) {
                    sum += list.get(j + 1).sum;
                }
                if(j >= 2 * range) {
                    sum -= list.get(j - 2 * range).sum;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}