import java.util.*;
import java.io.*;

public class Main_1377 {
    static class Node implements Comparable<Node> {
        // 정렬해야하는 값과 정렬하기 전 인덱스를 Node에 저장
        int value, index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
        // 버블 소트 결과와 동일하게 값을 기준으로 오름차순 정렬
        // 이때 값이 같을 경우 정렬 전 인덱스를 기준으로 오름차순 정렬
        @Override
        public int compareTo(Node o) {
            if (this.value == o.value) {
                return this.index - o.index;
            }
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Node> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i = 0; i < N; i++){
            list.add(new Node(Integer.parseInt(br.readLine()), i));
        }
        Collections.sort(list);

        // for문을 N - answer 까지만 돌려주는 이유 (프루닝)
        // 구하려는 값이 앞으로 이동한 횟수인데 N - answer 뒤의 숫자들은 answer 이상 앞으로 올 수 없기 때문
        for(int i = 0; i < N - answer; i++){
            // 버블정렬이 일어난 횟수 == 가장 많이 앞으로 온 숫자가 움직인 거리
            // 문제의 정답이 정렬 횟수 + 1 이기 때문에 똑같이 1을 더함
            answer = Math.max(answer, list.get(i).index - i + 1);
        }
        System.out.println(answer);
    }
}