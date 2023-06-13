package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class BJ_14725 {
    static int N;
    static String BAR = "--";
    static StringBuilder sb = new StringBuilder();
    static Node rootNode;
    static class Node {
        Map<String, Node> childNodes; // 자식 노드
        String food;  // 노드에 들어있는 음식
        boolean isEnd; // 리프 노드인지 유무
        boolean isVisit; // 방문 여부

        public Node(String food) { // 생성자에서 Map을 TreeMap으로 초기화(자동 정렬)
            this.childNodes = new TreeMap<>();
            this.food = food;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rootNode = new Node(""); // 루트 노드 생성

        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }

        printMap(rootNode, 0);

        System.out.println(sb.toString());
    }

    private static void insert(String information) {
        String[] foods = information.split(" ");
        Node node = rootNode;

        /**
         * computeIfAbsent
         * 1. key에 해당하는 값이 없을 경우 : 람다식을 수행하여 나온 결과를 "key : value" 쌍으로 map에 추가
         * 2. key에 해당하는 값이 있는 경우 : 기존 value return
         */
        for (int i = 1; i < foods.length; i++) {
            node = node.childNodes.computeIfAbsent(foods[i], food -> new Node(food));
        }
        node.isEnd = true; // 리프 노드 설정
    }

    private static void printMap(Node cur, int depth) { // DFS로 트리를 순회하면서 음식 출력
        if (cur.isEnd) { // 리프 노드인 경우 재귀 종료
            return;
        }

        for (Node nextNode : cur.childNodes.values()) {
            if (nextNode.isVisit) {
                continue;
            }

            for (int i = 0; i < depth; i++) { // 깊이 만큼 "--" 추가
                sb.append(BAR);
            }

            sb.append(nextNode.food).append("\n"); // 음식 추가

            printMap(nextNode, depth + 1);
        }
    }
}