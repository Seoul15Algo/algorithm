class Solution {
    static boolean[] target; //2진법으로 변환한 배열 (좌측 0 패딩 포함)
    static int result;

    public int[] solution(long[] numbers) {
        int[] res = new int[numbers.length];
        for (int index = 0; index < numbers.length; index++) {
            
            //2진수로 변환
            String str = Long.toBinaryString(numbers[index]);
            long num = numbers[index];

            /*2진법 변환한 target 배열 생성 시작(포화이진트리만들어서 앞에 길이 비었으면 0(false) 넣어주기)*/
            //포화 이진트리 길이 계산
            int exp = 1;
            int treeLen = 0;
            while(true) {
                treeLen = (int)Math.pow(2, exp++) - 1;
                if (treeLen >= str.length()) break;
            }

            target = new boolean[treeLen];
            
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '1') {
                     target[treeLen-str.length()+i] = true;
                }
            }
            /*2진법 변환한 target 배열 생성 끝*/
            
            result = 1;
            solve(0, treeLen - 1, false);
            res[index] = result;
        }
        return res;
    }
    //Root 부터 탐색
    public void solve(int s, int e, boolean isEnd) {
        int mid = (s + e) / 2;
        boolean cur = target[mid];
        //중간에 0이 나왔는데 현재 노드가 1이라면
        if (isEnd && cur) {
            result = 0;
            return;
        }
        //마지막 노드가 아닐 경우, 계속 진행
        if (s != e) {
            solve(s, mid-1, !cur);
            solve(mid+1, e, !cur);
        }
    }
}