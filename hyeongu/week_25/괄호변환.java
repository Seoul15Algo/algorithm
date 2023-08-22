class 괄호변환 {
    public String solution(String p) {
        return separate(p);
    }

    public static String separate(String p){
        StringBuilder u = new StringBuilder();

        if(p.length() == 0){
            return p;
        }

        int open = 0;

        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '('){
                open++;
            }

            u.append(p.charAt(i));

            if(i % 2 == 1 && (i + 1) / 2 == open){
                return makeRight(u.toString(), separate(p.substring(i + 1)));
            }
        }
        return p;
    }

    public static String makeRight(String u, String v){
        StringBuilder combine = new StringBuilder();

        if(u.charAt(0)=='('){
            combine.append(u);
            combine.append(v);

            return combine.toString();
        }

        combine.append('(');
        combine.append(v);
        combine.append(')');

        for(int i = 1; i < u.length() - 1; i++){
            if(u.charAt(i)=='(')
                combine.append(')');
            else
                combine.append('(');
        }

        return combine.toString();
    }
}