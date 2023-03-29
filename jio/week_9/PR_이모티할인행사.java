import java.util.*;

class PR_이모티할인행사 {
    static int[] nums; //각 이모티콘에 적용할 할인율을 저장할 배열
    static int[] discounts;
    static int N;
    static int[][] users;
    static int[] emoticons;
    static int maxEmoplusCnt;
    static int maxSell;

    public int[] solution(int[][] users, int[] emoticons) {
        discounts = new int[]{10, 20, 30, 40};
        N = emoticons.length;
        maxEmoplusCnt = 0;
        maxSell = Integer.MIN_VALUE;

        nums = new int[N];
        perm(0, users, emoticons);

        int[] answer = {maxEmoplusCnt, maxSell};
        return answer;
    }

    private void perm(int cnt, int[][] users, int[] emoticons){ //중복순열
        if(cnt == N){
            int curSell = 0;
            int curEmoplusCnt = 0;
            for(int i=0; i<users.length; i++){ //사용자 순회
                int userRate = users[i][0]; //사용자 비율
                int userCost = users[i][1]; //사용자 비용
                int tempSell = 0;
                for(int j=0; j<nums.length; j++){ //사용자가 선택한 이모티콘 할인 가격의 합 계산
                    int rate = nums[j];
                    if(userRate <= rate){ //이모티콘의 할인율이 사용자 비율보다 클 경우
                        tempSell += emoticons[j] * (1 - (double)rate/100);
                    }
                }
                if(tempSell >= userCost){ //구매한 이모티콘 비용의 합이 사용자 비용보다 클 때
                    curEmoplusCnt += 1;
                }else{
                    curSell += tempSell; //구매한 이모티콘 비용의 합이 사용자 비용보다 작을 경우
                }
            }
            //현재 이모티콘 플러스가 더 크거나 이모티콘 플러스가 같고 현재 판매비용이 더 큰 경우 값 갱신
            if(maxEmoplusCnt < curEmoplusCnt || (maxEmoplusCnt == curEmoplusCnt && maxSell < curSell)){
                maxEmoplusCnt = curEmoplusCnt;
                maxSell = curSell;
            }
            return;
        }
        for(int i=0; i<4; i++){
            nums[cnt] = discounts[i];
            perm(cnt+1, users, emoticons);
            nums[cnt] = 0; //없어도 됨
        }
    }
}