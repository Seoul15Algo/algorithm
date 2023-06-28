import java.util.*;
import java.io.*;

public class Main_16947 {
    static List<Station> list;
    static Station start;
    static class Station{
        boolean visit;
        int dist;
        List<Station> link;
        public Station(){
            link = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        start = new Station();

        for(int i = 0; i < N; i++){
            list.add(new Station());
        }

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            list.get(a).link.add(list.get(b));
            list.get(b).link.add(list.get(a));
        }

        dfs(null, list.get(0));
        findDepth(start);

        for(Station now : list){
            sb.append(now.dist).append(" ");
        }
        System.out.println(sb);
    }

    // dfs를 이용해서 기존에 방문했던 역을 찾는다
    // 해당 분기에서 다시 뒤로 돌아갔을 때 dfs에서 찾은 역이 나올 때 까지가 싸이클
    public static boolean dfs(Station prev, Station now){
        now.visit = true;
        boolean flag = false;

        for(Station next : now.link){
            // 다음에 방문할 역이 바로 전의 역 일때
            if(next.equals(prev)){
                continue;
            }

            // 이미 방문한 역을 찾았을 때 -> 싸이클의 시작점
            if(next.visit){
                start = next;
                flag = true;
            }

            // 싸이클을 찾은 경우
            if(flag || dfs(now, next)){
                // 다시 뒤로 돌아와서 찾은 역을 다시 만났을 경우
                // 해당 역을 false로 바꾸는 이유는 이 역보다 전에 방문한 역을 false 처리 해주기 위해서
                if(now.equals(start)){
                    return false;
                }
                findDepth(now);
                flag = true;
                break;
            }
        }
        return now.visit = flag;
    }

    public static void findDepth(Station now){
        for(Station next : now.link){
            if(next.visit){
                continue;
            }
            next.dist = now.dist + 1;
            next.visit = true;
            findDepth(next);
            next.visit = false;
        }
    }
}