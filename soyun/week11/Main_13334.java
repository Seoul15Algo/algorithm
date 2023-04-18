package algo230412.soyun.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13334 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int home = Integer.parseInt(st.nextToken());
            int office = Integer.parseInt(st.nextToken());
            if (home > office){
                int temp = home;
                home = office;
                office = temp;
            }
            people.add(new Person(home, office));
        }
        // end가 작은 순으로 sort
        Collections.sort(people);
        int D = Integer.parseInt(br.readLine());

        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // start가 작은 순으로 sort
        for (Person person : people) {
            // 선분 길이보다 두 점 사이의 거리가 길다면
            if (person.end - person.start > D){
                continue;
            }

            // 우선순위 큐에 시작점 추가
            pq.offer(person.start);
            while (!pq.isEmpty()) {
                // 시작점과 끝 점이 D 이하: 그룹 유지 가능 -> break
                if (person.end - pq.peek() <= D){
                    break;
                }
                // 그룹 유지 불가능 -> 새로운 시작점으로 갱신 (그룹이 생길 때까지)
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }

    static class Person implements Comparable<Person> {
        int start;
        int end;

        public Person(int home, int office) {
            this.start = home;
            this.end = office;
        }

        @Override
        public int compareTo(Person other) {
            if (this.end < other.end){
                return -1;
            } else if (this.end == other.end){
                return Integer.compare(this.start, other.start);
            } else {
                return 1;
            }
        }
    }
}