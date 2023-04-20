package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238 {
    static int N, M, curFuel;
    static int[][] grid;
    static int tx, ty; //초기 택시 위치
    static Customer[] customers; //승객 정보 저장
    static class Customer implements Comparable<Customer>{
        int sx, sy, ex, ey;

        public Customer(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }

        @Override
        public int compareTo(Customer c){
            if(this.sx == c.sx){ //행이 같을 경우 열로 비교
                return Integer.compare(this.sy, c.sy);
            }
            return Integer.compare(this.sx, c.sx);
        }
    }
    static class Pair{
        int x, y, dis;

        public Pair(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        curFuel = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1){
                    grid[i][j] = -1; //벽의 위치를 -1로 표시
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken()) - 1;
        ty = Integer.parseInt(st.nextToken()) - 1;

        customers = new Customer[M+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            customers[i] = new Customer(sx, sy, ex, ey);
            grid[sx][sy] = i; //격자에 탑승객 번호 표시
        }

        int customerCount = 0;
        while(true){
            if(customerCount == M){ //모든 승객을 태운 경우
                break;
            }
            Customer customer = findCustomer(); //가장 가까운 승객 찾기
            if(customer == null){ //태울 수 있는 승객이 없는 경우
                break;
            }
            if (!canMoveToDestination(customer)) { //도착지까지 가지 못하는 경우
                break;
            }
            customerCount++;
        }
        if(customerCount == M){ //택시가 모든 승객을 태운 경우
            System.out.println(curFuel);
        }else{
            System.out.println(-1);
        }
    }

    private static Customer findCustomer(){ //가장 가까운 승객을 찾음
        boolean[][] visited = new boolean[N][N];
        visited[tx][ty] = true;
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(tx, ty, 0));
        PriorityQueue<Customer> foundCustomers = new PriorityQueue<>();
        while (!que.isEmpty()) {
            int size = que.size();
            int usedFuel = 0; //승객까지 가는데 사용한 연료(이동거리)를 저장
            while(size-- > 0){ //BFS를 한 라운드 씩 끊어서 돌려준다.(같은 거리에 승객이 여러명 있을 수 있기 떄문에)
                Pair cur = que.poll();
                int x = cur.x;
                int y = cur.y;
                int dis = cur.dis;
                usedFuel = dis;
                if(grid[x][y] > 0){ //승객이라면 몇 번째 승객인지 return
                    int customerNum = grid[x][y]; //승객 번호 저장
                    foundCustomers.offer(customers[customerNum]);
                }
                if(dis >= curFuel){ //남은 연료가 없다면 더 이상 움직일 수 없으므로 continue
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (!inRange(nx, ny)) { //격자를 벗어난 경우
                        continue;
                    }
                    if (visited[nx][ny]) { //이전에 방문한 경우
                        continue;
                    }
                    if (grid[nx][ny] == -1) { //벽일 경우
                        continue;
                    }
                    visited[nx][ny] = true;
                    que.offer(new Pair(nx, ny, dis+1));
                }
            }
            if (!foundCustomers.isEmpty()) { //승객을 찾은 경우 BFS를 즉시 종료
                Customer nearCustomer = foundCustomers.poll();
                grid[nearCustomer.sx][nearCustomer.sy] = 0; //승객을 태웠다는 의미로 격자에서 지워줌
                curFuel -= usedFuel;  //남은 연료 계산
                tx = nearCustomer.sx; //택시 위치 출발지로 이동
                ty = nearCustomer.sy;
                return nearCustomer;
            }
        }
        return null; //태울 수 있는 승객을 찾지 못했다면 null return
    }

    private static boolean canMoveToDestination(Customer customer){ //가장 가까운 승객을 찾음
        boolean[][] visited = new boolean[N][N];
        visited[tx][ty] = true;
        Queue<Pair> que = new ArrayDeque<>();
        que.offer(new Pair(tx, ty, 0));
        int ex = customer.ex; //해당 승객의 도착지 정보
        int ey = customer.ey;
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int dis = cur.dis;
            if(x == ex && y == ey){ //도착지에 도달했다면
                curFuel -= dis;  //연료 계산
                curFuel += (dis) * 2;
                tx = x; //택시 위치 이동
                ty = y;
                return true;
            }
            if(dis >= curFuel){ //남은 연료가 없다면 더 이상 움직일 수 없으므로 continue
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (!inRange(nx, ny)) { //격자를 벗어난 경우
                    continue;
                }
                if (visited[nx][ny]) { //이전에 방문한 경우
                    continue;
                }
                if (grid[nx][ny] == -1) { //벽일 경우
                    continue;
                }
                visited[nx][ny] = true;
                que.offer(new Pair(nx, ny, dis+1));
            }
        }
        return false; //도착지까지 가지 못하는 경우
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
