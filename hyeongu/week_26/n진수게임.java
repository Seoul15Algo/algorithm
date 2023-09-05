class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int maxSize = m * t;

        // 1부터 n진수로 바꿔서 계속 더함
        // m명의 사람이 t번씩 말하는 길이까지 구함
        for(int i = 0; sb.length() <= maxSize; i++){
            sb.append(Integer.toString(i, n));
        }

        // 왜 답을 대문자로 해놓는거야 귀찮게
        String total = sb.toString().toUpperCase();
        sb.setLength(0);

        // 튜브가 말할 숫자만 뽑아서 저장
        for(int i = p - 1; i < maxSize; i += m){
            sb.append(total.charAt(i));
        }

        return sb.toString();
    }
}