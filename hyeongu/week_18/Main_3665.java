import java.util.*;
import java.io.*;

public class Main_3665 {
    static int[] input;     // 자신보다 우선순위가 높은 팀의 수
    static Team[] arr;
    static int N;

    static class Team{
        int index;
        boolean visit;
        List<Team> next;    // 자신보다 우선순위가 낮은 팀 리스트
        public Team(int index){
            this.index = index;
            this.visit = false;
            next = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            arr = new Team[N];
            input = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();

            for(int i = 0; i < N; i++){
                arr[i] = new Team(i);
            }

            // 작년 팀 순위 추가
            // 작년 순위가 5 4 3 2 1 인 경우
            // (5, 4), (5, 3), (5, 2), (5, 1), (4, 3), (4, 2), ...
            // 위처럼 가능한 모든 경우를 저장한다
            list.add(Integer.parseInt(st.nextToken()) - 1);
            for(int i = 0; i < N - 1; i++){
                int now = Integer.parseInt(st.nextToken()) - 1;

                // 앞서 나온 팀을 모두 리스트에 저장하고
                // 그 팀들과 우선순위를 모두 추가
                for(int last : list){
                    arr[last].next.add(arr[now]);
                    input[now]++;
                }

                list.add(now);
            }

            // 작년과 바뀐 우선순위를 수정
            // a, b가 들어왔을때
            // a -> b 로 되어있었다면
            // b -> a 로 바꾸는 과정
            int M = Integer.parseInt(br.readLine());
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                if(arr[a].next.contains(arr[b])){
                    arr[a].next.remove(arr[b]);
                    arr[b].next.add(arr[a]);
                    input[b]--;
                    input[a]++;
                }else{
                    arr[b].next.remove(arr[a]);
                    arr[a].next.add(arr[b]);
                    input[a]--;
                    input[b]++;
                }
            }
            sb.append(findRank()).append("\n");
        }
        System.out.println(sb);
    }

    // 위상정렬을 이용해서 순위를 정렬
    // 확실한 순위를 찾을 수 없는경우 -> 우선순위가 없는 팀이 나올경우 -> ? 출력
    // 데이터에 일관성이 없는 경우 -> 위상정렬이 불가능 한 경우 -> IMPOSSIBLE 출력
    static String findRank(){
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // 자신보다 우선순위가 높은 팀이 없는경우 -> 가장 순위가 높은 팀
        for(int i = 0; i < N; i++){
            if(input[i] == 0){
                q.offer(i);
            }
        }
        // 순위가 높은 팀이 없는 경우 -> false
        if(q.isEmpty()){
            return "IMPOSSIBLE";
        }

        int cnt = 0;
        while(!q.isEmpty()){
            cnt++;
            int now = q.poll();
            // 순위가 높은 팀이 한팀이 아닐 경우 -> false
            if(!q.isEmpty()){
                return "?";
            }

            arr[now].visit = true;
            sb.append(now + 1).append(" ");

            for(Team t : arr[now].next){
                // 우선순위가 낮은 팀인데 이미 방문했을 경우 -> false
                if(t.visit || input[t.index] < 0){
                    return "IMPOSSIBLE";
                }

                // 우선순위가 높았던 팀을 제거
                input[t.index]--;
                // 우선순위가 높은 팀이 없을경우 -> 다음 우선순위
                if(input[t.index] == 0){
                    q.offer(t.index);
                }
            }
        }

        // 끝까지 확인했는데 N개보다 적은 팀을 확인한 경우 -> false
        if(cnt < N){
            return "IMPOSSIBLE";
        }

        return sb.toString();
    }
}
