class PR_표현가능한이진트리 {
    int possibleFlag; //포화 이진트리인지 유무
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {

            //1. 기존 이진수 옆에 트리의 높이에 맞게 0을 추가하여 포화 이진트리로 변경
            String binaryString = Long.toBinaryString(numbers[i]);
            int height = 0; //포화 이진트리의 높이
            int addZeroCnt = 0;
            while(true){
                int totalNode = (int) Math.pow(2, height) - 1; //트리 높이 : 2^height-1
                if(binaryString.length() <= totalNode){
                    addZeroCnt += totalNode - binaryString.length(); //몇 개의 0을 추가해야하는 지 계산
                    break;
                }
                height += 1;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < addZeroCnt; j++) {
                sb.append("0");
            }
            binaryString = sb.append(binaryString).toString();

            //2. 표현이 가능한 포화 이진 트리인지 결정
            possibleFlag = 1;
            isPossible(binaryString, 0, binaryString.length()-1);
            if(possibleFlag == 1){ //표현 가능한 경우
                answer[i] = 1;
                continue;
            }
            answer[i] = 0;
        }
        return answer;
    }

    void isPossible(String binaryString, int left, int right) {
        if(right - left <= 1){ //left와 right가 같거나 right가 left보다 더 작아졌을 경우
            return;
        }
        int mid = left + (right - left) / 2; //중간 값 계산
        if(binaryString.charAt(mid) == '0'){ //값이 0인 루트노드일 경우 자신의 왼쪽, 오른쪽 서브트리가 모두 0이어야 한다.
            for (int i = left; i <= right; i++) {
                if(binaryString.charAt(i) == '1'){ //값이 1인 경우 표현이 불가능하다.
                    this.possibleFlag = 0;
                    return;
                }
            }
        }
        isPossible(binaryString, left, mid-1); //왼쪽 서브트리 탐색
        isPossible(binaryString, mid+1, right); //오른쪽 서브트리 탐색
    }
}