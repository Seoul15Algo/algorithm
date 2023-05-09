class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            String s = Long.toString(numbers[i], 2);    // 주어진 정수를 2진수로 변환
            int[] arr = make(s);                        // 변환된 2진수의 앞을 채워 배열로 변환
            
            if(check(arr, 0, arr.length - 1) >= 0){     
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    // 포화 이진트리를 만들기 위해 만들어진 2진수 앞에 0을 채우는 함수
    // 현재 트리의 높이를 구해서 포화 이진트리를 구하는 방식
    static int[] make(String str){
        int h = (int)Math.floor(Math.log(str.length())/Math.log(2)) + 1;
        int len = (1 << h) - 1;
        int tmp = len - str.length();
        
        int[] arr = new int[len];
        
        for(int i = 0; i < str.length(); i++){
            arr[i + tmp] = str.charAt(i) - '0';
        }
        return arr;
    }
    
    // 2개의 구간으로 나누어서 재귀적으로 확인
    static int check(int[] arr, int start, int end){
        // 리프노드일 경우
        if(start == end){
            return arr[start];
        }
        
        int mid = (start + end) / 2;
        
        int left = check(arr, start, mid - 1);
        int right = check(arr, mid + 1, end);
        
        
        // 리프노드가 아닌경우 현재 중앙 값과 양쪽의 결과 값을 비교
        // -1 : 표현할 수 없는경우
        // 0 : 해당 노드가 더미노드인 경우
        // 1 : 해당 노드가 더미노드가 아닌 경우
        
        // 자식이 더미가 아닌경우 부모도 더미가 아니어야한다
        // 자식이 모두 더미인 경우 부모의 값은 상관 없음 -> 부모의 값을 리턴
        if(left >= 0 && right >= 0){
            if(arr[mid] == 1){
                return 1;
            }
            if(left == 0 && right == 0){
                return 0;
            }
        }
        return -1;
    }
}