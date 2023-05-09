package com.ssafy.algo230329_Random1.soyun.week9;

public class Solution_Emoticon {

    final int[] DISCOUNT_RATIOS = { 10, 20, 30, 40 };   // 할인 비율
    int[][] users;
    int[] emoticons;
    int[] discountRatios;
    int maxSubscribers;
    int maxSales;


    int getDiscountedCharge(int emoticonCharge, int discountRatio){

        return (int)(emoticonCharge - emoticonCharge * (discountRatio / 100D));
    }

    int[] discountEmoticons(){
        // TODO: 이모티콘을 할인율에 맞게 할인한 가격을 반환한다.
        int[] discounted = new int[emoticons.length];
        for (int i = 0; i < discounted.length; i++) {
            discounted[i] = getDiscountedCharge(emoticons[i], discountRatios[i]);
        }
        return discounted;
    }

    int[] getPurchaseResult(int[] discounted){
        // TODO: 이모티콘 구매 결과를 반환한다. [구독자 수, 매출액]
        int subscribers = 0;
        int sales = 0;

        for (int[] user : users){
            int totalCharge = 0;
            for (int i = 0; i < discounted.length; i++) {
                // 사용자의 기준 할인율보다 이모티콘 할인율이 크다면 -> 구매 진행
                if (discountRatios[i] >= user[0]){
                    totalCharge = totalCharge + discounted[i];
                }
            }
            // 사용자의 예산보다 총 구매금액이 크다면 -> 이모티콘 플러스 가입
            if (totalCharge >= user[1]){
                subscribers++;
                continue;
            }
            sales = sales + totalCharge;
        }
        return new int[]{subscribers, sales};
    }


    void combination(int count){

        // 이모티콘의 할인율을 모두 선정했다면
        if (count == emoticons.length){
            // TODO: 고객이 할인한 이모티콘을 얼마나 구매할 것인지 계산
            int[] discounted = discountEmoticons();
            int[] result = getPurchaseResult(discounted);

            
            // TODO: 우선순위에 맞게 갱신
            // 우선순위 1: 구독자 수
            if (result[0] > maxSubscribers){
                maxSubscribers = result[0];
                maxSales = result[1];
            }
            // 우선순위 2: 매출액
            if (result[0] == maxSubscribers){
                if (result[1] > maxSales){
                    maxSales = result[1];
                }
            }
            return;
        }

        // 중복순열을 사용하여 이모티콘 할인율을 선택
        for (int i = 0; i < DISCOUNT_RATIOS.length; i++) {
            discountRatios[count] = DISCOUNT_RATIOS[i];
            combination(count + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.discountRatios = new int[emoticons.length];
        this.maxSales = Integer.MIN_VALUE;
        this.maxSubscribers = Integer.MIN_VALUE;

        combination(0);
        //System.out.println(maxSubscribers + " " + maxSales);

        return new int[]{maxSubscribers, maxSales};
    }
}
