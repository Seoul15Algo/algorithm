import java.util.*;

class 파일명정렬 {
    static List<File> list = new ArrayList<>();

    static class File implements Comparable<File>{
        String fileName;    // 전체 파일 이름
        String head;        // head 부분
        int number;         // index 부분
        int index;          // 입력 순서

        public File(String fileName, String head, int number) {
            this.fileName = fileName;
            this.head = head.toUpperCase();
            this.number = number;
            this.index = list.size();
        }

        // 문제에 주어진 조건에 맞게 compare 규칙 정의
        public int compareTo(File f){
            if(this.head.equals(f.head)){
                if(this.number == f.number){
                    return this.index - f.index;
                }
                return this.number - f.number;
            }
            return this.head.compareTo(f.head);
        }
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        for(String str : files){
            list.add(findHead(str));
        }

        Collections.sort(list);

        for(int i = 0; i < files.length; i++){
            answer[i] = list.get(i).fileName;
        }

        return answer;
    }

    // 주어진 string에서 숫자가 나오는 순간을 찾고 앞부분을 head로 저장
    public static File findHead(String str){
        for(int i = 0; i < str.length(); i++){
            char now = str.charAt(i);

            if(isNumber(now)){
                return new File(str, str.substring(0, i), findNumber(str, i));
            }
        }
        return null;
    }

    // 이어진 숫자를 구해서 return
    public static int findNumber(String str, int start){
        for(int i = start; i < str.length(); i++){
            char now = str.charAt(i);
            if(!isNumber(now)){
                return Integer.parseInt(str.substring(start, i));
            }
        }
        return Integer.parseInt(str);
    }

    public static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }
}