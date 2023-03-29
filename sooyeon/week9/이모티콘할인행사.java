import java.util.*;
//프로그래머스 - 이모티콘할인행사

class Solution {
    static int n,m;
    static int[] nums;
    static boolean[] visited;
    static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        
        //System.out.println(n+" "+m);
        nums = new int[m];
        visited = new boolean[m];
        answer = new int[2];
        
        
        perm(0, users, emoticons);

        return answer;
    }
        
        
        
    static void perm(int cnt, int[][] users, int[] emoticons) { //경우의 수 돌리기
        if(cnt == m) {
            cal(nums, users, emoticons);
            return;
        }
        for(int i = 0; i <= 40; i+=10) {
            if(visited[cnt]) continue;
            visited[cnt] = true;
            nums[cnt] = i;
            perm(cnt+1, users, emoticons);
            nums[cnt] = 0;
            visited[cnt] = false;
            
        }
    }
    
    static void cal(int[] nums, int[][] users, int[] emoticons) {
        int service = 0;
        int price = 0;
        for(int i = 0; i < n; i++) {
            int acc = 0; //구매한 이모티콘 누적 값
            boolean isJoin = false;
            for(int j = 0; j < m; j++) {
                if(nums[j] >= users[i][0]) { //이모티콘 할인율이 users의 비율보다 크거나 같을때
                    double percent = 1 - nums[j]/100.0;
                    acc += emoticons[j]*percent;
                }
                
                if(users[i][1] <= acc) { //가격이상의 돈이라 이모티콘 플러스 가입
                    service++;
                    isJoin = true;
                    break;
                }
            }
            //이모티콘 서비스에 가입 못한 경우
            if(!isJoin) {
                price += acc;
            }
            
        }
        
        if((service > answer[0]) || (service == answer[0] && price > answer[1])) { 
            //서비스가입자가 기존 답보다 클때 or 서비스가입자가 같은데 판매액이 기존 답보다 큰 경우
            answer[0] = service;
            answer[1] = price;
        }
        
        return;
    }
    
}