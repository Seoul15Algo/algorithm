package week33;

class Q77885 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            String s = Long.toString(n, 2);

            int idx = -1;

            for(int j = s.length()-1; j >= 0; j--) {
                if(s.charAt(j) == '0') {
                    idx = j;
                    break;
                }
            }

            if(idx > -1) {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(idx, '1');
                if(idx+1 < s.length()) sb.setCharAt(idx+1, '0');

                answer[i] = Long.parseLong(sb.toString(), 2);
                continue;
            }

            StringBuilder sb = new StringBuilder(s);
            sb.append(1);
            sb.setCharAt(1, '0');
            answer[i] = Long.parseLong(sb.toString(), 2);
        }

        return answer;
    }
}

