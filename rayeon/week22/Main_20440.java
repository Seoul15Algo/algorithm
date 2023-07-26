package week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20440 {
    static class Mosquito {
        int e;
        int x;

        public Mosquito(int e, int x) {
            this.e = e;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Mosquito> mosquitos = new PriorityQueue<>(new Comparator<Mosquito>() {
            @Override
            public int compare(Mosquito o1, Mosquito o2) {
                if (o1.e == o2.e) {
                    return Integer.compare(o1.x, o2.x);
                }

                return Integer.compare(o1.e, o2.e);
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int e = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            mosquitos.offer(new Mosquito(e, x));
        }

        PriorityQueue<Mosquito> times = new PriorityQueue<>(new Comparator<Mosquito>() {
            @Override
            public int compare(Mosquito o1, Mosquito o2) {
                if (o1.x == o2.x) {
                    return Integer.compare(o1.e, o2.e);
                }

                return Integer.compare(o1.x, o2.x);
            }
        });

        int answer = 0;
        int Te = 0;
        int Tx = 0;

        while (!mosquitos.isEmpty()) {
            Mosquito mosquito = mosquitos.poll();

            while (!times.isEmpty() && times.peek().x <= mosquito.e) {
                Mosquito time = times.poll();

                if (answer == times.size() + 1 && time.x == mosquito.e) {
                    Tx = mosquito.x;
                }
            }

            times.offer(mosquito);

            if (answer < times.size()) {
                answer = times.size();
                Te = mosquito.e;
                Tx = times.peek().x;
            }
        }

        System.out.println(answer);
        System.out.println(Te + " " + Tx);
        br.close();
    }
}