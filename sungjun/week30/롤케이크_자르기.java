package week30;

class 롤케이크_자르기 {
    public int solution(int[] topping) {
        int[][] toppings = new int[topping.length][2];
        boolean[] flavor = new boolean[10001];

        int leftCount = 0;

        for(int i = 0; i < topping.length; i++) {
            if(flavor[topping[i]]) {
                toppings[i][0] = leftCount;
                continue;
            }
            flavor[topping[i]] = true;
            toppings[i][0] = ++leftCount;
        }

        int rightCount = 0;
        flavor = new boolean[10001];

        for(int i = topping.length-1; i >= 0; i--) {
            if(flavor[topping[i]]) {
                toppings[i][1] = rightCount;
                continue;
            }

            flavor[topping[i]] = true;
            toppings[i][1] = ++rightCount;
        }

        int firstIdx = -1;
        int lastIdx = -1;
        boolean flag = false;

        for(int i = 0; i < topping.length-1; i++) {
            if(toppings[i][0] == toppings[i+1][1]) {
                if(!flag) {
                    flag = true;
                    firstIdx = i;
                }
                continue;
            }
            if(flag) {
                lastIdx = i;
                break;
            }
        }

        return firstIdx == -1 ? 0 : lastIdx - firstIdx;
    }
}