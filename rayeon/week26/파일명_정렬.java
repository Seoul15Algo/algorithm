package week26;

import java.util.*;

class 파일명_정렬 {
    static class File implements Comparable<File>{
        int index;
        String head;
        int number;
        String tail;

        public File (int index, String head, int number, String tail) {
            this.index = index;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public int compareTo(File o) {
            if (!this.head.toUpperCase().equals(o.head.toUpperCase())) {
                return this.head.toUpperCase().compareTo(o.head.toUpperCase());
            } else if (this.number != o.number) {
                return this.number - o.number;
            } else {
                return this.index - o.index;
            }
        }
    }

    public String[] solution(String[] inputs) {
        String[] answer = new String[inputs.length];

        PriorityQueue<File> files = new PriorityQueue<>();

        for (int i = 0; i < inputs.length; i++) {
            int startNumIndex = 0;
            int endNumIndex = inputs[i].length();

            for (int j = 0; j < inputs[i].length(); j++) {
                if (startNumIndex == 0 && Character.isDigit(inputs[i].charAt(j))) {
                    startNumIndex = j;
                    continue;
                }

                if (startNumIndex > 0 && !Character.isDigit(inputs[i].charAt(j))) {
                    endNumIndex = j;
                    break;
                }
            }

            String head = inputs[i].substring(0, startNumIndex);
            int number = Integer.parseInt(inputs[i].substring(startNumIndex, endNumIndex));
            String tail = "";

            if (endNumIndex < inputs[i].length() - 1) {
                tail = inputs[i].substring(endNumIndex, inputs[i].length());
            }

            files.add(new File(i, head, number, tail));
        }

        for (int i = 0; i < inputs.length; i++) {
            answer[i] = inputs[files.poll().index];
        }

        return answer;
    }
}
















