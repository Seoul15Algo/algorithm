package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12100{

    static int N;
    static int result = 0;
    static int[][] map;

    static void move(int d){
        Queue<Integer> q = new LinkedList<>();

        switch (d){
            // 오른쪽
            case 0:
                for(int i = 0; i < N; i++){
                    // 큐에 숫자 넣기
                    for(int j = N-1; j >= 0; j--){
                        if(map[i][j] != 0) q.add(map[i][j]);
                        map[i][j] = 0;
                    }

                    // 큐에 숫자를 하나씩 꺼내서 같은 수라면 더해서 넣기
                    int index = N-1;
                    while(!q.isEmpty()){
                        int cur = q.poll();

                        if(map[i][index] == 0) map[i][index] = cur;
                        else if(map[i][index] == cur){
                            map[i][index] *= 2;
                            index--;
                        }
                        else{
                            index--;
                            map[i][index] = cur;
                        }
                    }
                }
                break;

            // 아래쪽
            case 1:
                for(int i = 0; i < N; i++){
                    // 큐에 숫자 넣기
                    for(int j = 0; j < N; j++){
                        if(map[j][i] != 0) q.add(map[j][i]);
                        map[j][i] = 0;
                    }

                    // 큐에 숫자를 하나씩 꺼내서 같은 수라면 더해서 넣기
                    int index = 0;
                    while(!q.isEmpty()){
                        int cur = q.poll();

                        if(map[index][i] == 0) map[index][i] = cur;
                        else if(map[index][i] == cur){
                            map[index][i] *= 2;
                            index++;
                        }
                        else{
                            index++;
                            map[index][i] = cur;
                        }
                    }
                }
                break;
                
            // 왼쪽
            case 2:
                for(int i = 0; i < N; i++){
                    // 큐에 숫자 넣기
                    for(int j = 0; j < N; j++){
                        if(map[i][j] != 0) q.add(map[i][j]);
                        map[i][j] = 0;
                    }

                    // 큐에 숫자를 하나씩 꺼내서 같은 수라면 더해서 넣기
                    int index = 0;
                    while(!q.isEmpty()){
                        int cur = q.poll();

                        if(map[i][index] == 0) map[i][index] = cur;
                        else if(map[i][index]==cur){
                            map[i][index] *= 2;
                            index++;
                        }
                        else{
                            index++;
                            map[i][index] = cur;
                        }
                    }
                }
                break;

            // 위쪽
            case 3:
                for(int i = 0; i < N; i++){
                    // 큐에 숫자 넣기
                    for(int j = N-1; j >= 0; j--){
                        if(map[j][i] != 0) q.add(map[j][i]);
                        map[j][i] = 0;
                    }

                    // 큐에 숫자를 하나씩 꺼내서 같은 수라면 더해서 넣기
                    int index = N-1;
                    while(!q.isEmpty()){
                        int cur = q.poll();

                        if(map[index][i] == 0) map[index][i] = cur;
                        else if(map[index][i] == cur){
                            map[index][i] *= 2;
                            index--;
                        }
                        else{
                            index--;
                            map[index][i] = cur;
                        }
                    }
                }
                break;
        }
    }

    static void dfs(int cnt){
        if(cnt == 5){
        	for(int i = 0; i < N; i++){
                for(int j = 0; j < N ; j++){
                    result = Math.max(map[i][j], result);
                }
            }
            return;
        }

        int[][] temp = new int[N][N];
        
        for(int i = 0; i < N; i++){
            temp[i] = map[i].clone();
        }
        
        // 백트래킹으로 모든 경우의 수 체크
        for(int i = 0; i < 4; i++){
            move(i);
            dfs(cnt+1);

            for(int j = 0; j < N; j++){
                map[j] = temp[j].clone();
            }
        }
    }

    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j  <N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(result);
    }
}