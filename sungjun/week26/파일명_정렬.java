package week26;

import java.util.ArrayList;
import java.util.Collections;

class 파일명_정렬 {
    // head, number, tail의 구조로 이루어진 File 클래스 정의
    public class File implements Comparable<File> {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        // 정렬 규칙 정의
        public int compareTo(File o) {
            if(this.head.equalsIgnoreCase(o.head)) {
                return Integer.compare(Integer.parseInt(this.number),
                        Integer.parseInt(o.number));
            }

            return this.head.toLowerCase().compareTo(o.head.toLowerCase());
        }

        public String toString() {
            return head + number + tail;
        }
    }

    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        ArrayList<File> sortedFiles = new ArrayList<>();

        for(String file : files) {
            StringBuilder sb = new StringBuilder();

            boolean flag = false;

            for(int i = 0; i < file.length(); i++) {
                char c = file.charAt(i);

                // head는 숫자가 아닌 문자로만 이루어져 있다
                if(c >= '0' && c <= '9') {
                    sb.append(c);
                    flag = true;
                }

                // number은 1~5자리의 숫자
                if(sb.length() > 5) break;
                if(flag && (c > '9' || c < '0' || c == ' ')) break;
            }

            // number 부분으로 split -> 0번째 index가 head
            // head의 길이 + number의 길이 -> tail의 시작 index
            String[] tmp = file.split(sb.toString());

            sortedFiles.add(new File(tmp[0], sb.toString(), file.substring(tmp[0].length()+sb.length(), file.length())));
        }

        // 정렬
        Collections.sort(sortedFiles);

        for(int i = 0; i < files.length; i++) {
            answer[i] = sortedFiles.get(i).toString();
        }

        return answer;
    }
}