import java.util.*;
import java.io.*;

public class Main_22948 {
    static PriorityQueue<Boundary> pq = new PriorityQueue<>();
    static List<Circle> list = new ArrayList<>();
    static int answer;
    static StringBuilder sb;
    static boolean[] visit;

    // 경곗값을 통해 포함관계를 구할 때 이용하는 클래스
    static class Boundary implements Comparable<Boundary>{
        int index, value;
        public Boundary(int k, int value){
            this.index = k;
            this.value = value;
        }

        @Override
        public int compareTo(Boundary o) {
            return this.value - o.value;
        }
    }

    // 인덱스, 부모, 자식들을 저장할 수 있는 Circle 클래스
    static class Circle{
        int index;
        Circle parent;
        List<Circle> children;

        public Circle(int index){
            this.index = index;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        answer = 0;

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++){
            list.add(new Circle(i));
        }

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            pq.offer(new Boundary(k, x - r));
            pq.offer(new Boundary(k, x + r));
        }

        Circle now = list.get(0);
        Stack<Integer> stack = new Stack<>();
        // pq에서 하나씩 빼면서 인덱스를 비교하고 다르면 stack에 삽입
        // 같으면 해당 원 종료
        while(!pq.isEmpty()){
            int index = pq.poll().index;

            if(stack.isEmpty() || index != stack.peek()){
                stack.push(index);
                Circle next = list.get(index);
                now.children.add(next);
                next.parent = now;
                now = next;
                continue;
            }

            stack.pop();
            now = now.parent;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 1];

        // 도착지부터 출발해서 위로 이동하며 visit 배열을 채움
        now = list.get(goal);

        while(true){
            visit[now.index] = true;

            // 루트노드에 도착 한 경우
            if(now.index == 0){
                // 출발지부터 올라가는 메소드 호출
                up(start, goal);
                break;
            }
            // 출발노드에 도착 한 경우
            // 도착원이 출발원에 포함 된 경우
            if(now.index == start){
                // 바로 출발지에서 도착지로 내려가는 메소드 호출
                down(start, goal);
                break;
            }

            now = now.parent;
        }
        System.out.println(answer);
        System.out.print(sb.toString());
    }

    // 올라가는 메소드
    // 시작점부터 올라가면서 방문한 원을 탐색
    // 이미 방문한 원을 만났을 경우 해당 원이 공통조상
    // 그 원부터 내려가는 메소드 호출
    public static void up(int start, int goal){
        Circle now = list.get(start);

        while(!visit[now.index]){
            sb.append(now.index).append(" ");
            now = now.parent;
            answer++;
        }

        down(now.index, goal);
    }

    // 내려가는 메소드
    // 공통 조상부터 내려오면서 도착지까지 이동
    // 자식노드 중에서 이미 방문한 노드가 이동해왔던 경로이므로 visit 배열을 이용해서 이동
    public static void down(int index, int goal){
        answer++;
        sb.append(index).append(" ");

        if(index == goal){
            return;
        }
        Circle now = list.get(index);

        for(Circle next : now.children){
            if(visit[next.index]){
                down(next.index, goal);
                return;
            }
        }
    }
}