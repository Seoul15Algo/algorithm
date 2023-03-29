class Solution {
    static int[] sales = {10, 20, 30, 40};
    static int[] answer, sale_rate;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        sale_rate = new int[emoticons.length];
        perm(users, emoticons, 0);
        
        return answer;
    }
    
    // 중복순열
    static void perm(int[][] users, int[] emoticons, int depth){
        if(depth == emoticons.length){
            result(users, emoticons);
            return;
        }
                
        for(int i = 0; i < 4; i++){
            sale_rate[depth] = sales[i];
            perm(users, emoticons, depth + 1);
        }
    }
    
    // 해당 케이스에서 가입자수와 매출액 계산
    static void result(int[][] users, int[] emoticons){
        int plus = 0; 
        int money = 0;

        for(int i = 0; i < users.length; i++){
            int sale_limit = users[i][0];
            int sum_limit = users[i][1];
            int sum = 0;
            
            for(int j = 0; j < emoticons.length; j++){
                if(sale_rate[j] >= sale_limit){
                    sum += emoticons[j] * (100 - sale_rate[j]) / 100;
                }
            }
            
            if(sum >= sum_limit){
                plus++;
                continue;
            }
            money += sum;
        }
        
        if(plus > answer[0]){
            answer[0] = plus;
            answer[1] = money;
            return;
        }
        if(plus == answer[0]){
            answer[1] = Math.max(answer[1], money);
        }
    }
}