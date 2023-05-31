package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_4195 {
    static int T, N, key;
    static Map<String, Integer> nameToInt;

    /**
     * parents 배열의 정의
     * parents[i] : i번째 노드가 최상위 부모라면 "-|자신을 루트로하는 서브트리의 노드(자식) 수|"를 지닌다.
     * parents[i] : i번째 노드가 최상위 부모가 아니라면 "최상위 보모의 index"를 지닌다.
     */
    static int[] parents;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            key = 0;
            nameToInt = new HashMap<>(); // 사람의 이름을 정수형으로 매핑

            init();

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                String firstName = input[0];
                String secondName = input[1];

                if (!nameToInt.containsKey(firstName)) {  // 첫 번째 사람이 처음 들어온 경우
                    nameToInt.put(firstName, key++);
                }

                if (!nameToInt.containsKey(secondName)) { // 두 번째 사람이 처음 들어온 경우
                    nameToInt.put(secondName, key++);
                }

                int firstNum = nameToInt.get(firstName);   // 첫 번째 사람을 의미하는 정수
                int secondNum = nameToInt.get(secondName); // 두 번째 사람을 의미하는 정수

                sb.append(union(firstNum, secondNum)).append("\n");
            }
        }

        System.out.print(sb);
    }

    /**
     * Union Find 과정
     * 1. '-1'로 parents 배열 초기화(parents[i] : i 번째 사람의 최상위 부모)
     * 2. union에 파라미터로 들어온 px, py 값이 같다면 최상위 부모가 같으므로 최상위 부모의 값을 return
     * 3. px, py가 다르다면 py의 부모를 px로 설정
     */

    private static void init() { // union find를 위한 초기화
        parents = new int[2 * 100000];
        Arrays.fill(parents, -1);
    }

    private static int find(int x) { // x의 최상위 부모를 탐색
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static int union(int x, int y) { // x와 y가 다른 그룹이라면 y를 x 그룹에 추가
        int px = find(x);
        int py = find(y);

        if (px == py) {
            return Math.abs(parents[px]);
        }

        parents[px] += parents[py]; // y가 속한 그룹의 노드를 x가 속한 그룹에 추가
        parents[py] = px; // py의 부모를 px로 갱신

        return Math.abs(parents[px]);
    }
}
