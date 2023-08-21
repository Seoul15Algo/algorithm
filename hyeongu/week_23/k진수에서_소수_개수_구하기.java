public class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();

        while(n!=0){
            sb.insert(0, n%k);
            n/=k;
        }

        String[] arr = sb.toString().split("0");

        for(String str: arr){
            if(str.equals("")){
                continue;
            }
            long num=Long.parseLong(str);

            if(prime(num)){
                answer++;
            }
        }

        return answer;
    }

    public boolean prime (long a){

        if(a<2){
            return false;
        }

        for(int i=2; i<=Math.sqrt(a); i++){
            if(a % i == 0){
                return false;
            }
        }
        return true;
    }
}
