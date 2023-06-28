import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_20210 {
    static class Word implements Comparable<Word>{
        char[] list;
        String str;

        public Word(String str) {
            this.str = str;
            this.list = str.toCharArray();
        }

        @Override
        public int compareTo(Word o) {
            int now_pointer = 0;
            int next_pointer = 0;

            while(now_pointer < this.list.length && next_pointer < o.list.length) {
                if(isNumber(this.list[now_pointer])){
                    if(isNumber(o.list[next_pointer])){
                        int tmp = compareNumber(o, now_pointer, next_pointer);
                        if(tmp == 0){
                            while(now_pointer < this.list.length && isNumber(this.list[now_pointer])){
                                now_pointer++;
                            }
                            while(next_pointer < o.list.length && isNumber(o.list[next_pointer])){
                                next_pointer++;
                            }
                            continue;
                        }
                        return tmp;
                    }
                    return -1;
                }

                if(isNumber(o.list[next_pointer])){
                    return 1;
                }

                if(this.list[now_pointer] == o.list[next_pointer]) {
                    now_pointer++;
                    next_pointer++;
                    continue;
                }

                if(Character.toLowerCase(this.list[now_pointer]) == Character.toLowerCase(o.list[next_pointer])){
                    return this.list[now_pointer] - o.list[next_pointer];
                }
                return Character.toLowerCase(this.list[now_pointer]) - Character.toLowerCase(o.list[next_pointer]);
            }

            return (this.list.length - now_pointer) - (o.list.length - next_pointer);
        }
        public int compareNumber(Word o, int now_index, int next_index){
            int now_zero = 0;
            int next_zero = 0;
            int now_len = 0;
            int next_len = 0;
            boolean flag = false;

            while(now_index + now_len < this.list.length){
                if(this.list[now_index + now_len] == '0'){
                    if(!flag){
                        now_zero++;
                    }
                    now_len++;
                    continue;
                }
                if(!isNumber(this.list[now_index + now_len])){
                    break;
                }
                flag = true;
                now_len++;
            }

            flag = false;
            while(next_index + next_len < o.list.length){
                if(o.list[next_index + next_len] == '0'){
                    if(!flag){
                        next_zero++;
                    }
                    next_len++;
                    continue;
                }
                if(!isNumber(o.list[next_index + next_len])){
                    break;
                }
                flag = true;
                next_len++;
            }

            if(now_len - now_zero != next_len - next_zero){
                return (now_len - now_zero) - (next_len - next_zero);
            }
            int tmp = now_len - now_zero;
            now_index += now_zero;
            next_index += next_zero;

            while(tmp-- > 0){
                if( this.list[now_index] != o.list[next_index]){
                    return this.list[now_index] - o.list[next_index];
                }
                now_index++;
                next_index++;
            }
            return now_zero - next_zero;
        }
        public boolean isNumber(char c){
            if(c >= '0' && c <= '9'){
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Word> list = new ArrayList<>();

        while(N-- > 0){
            String str = br.readLine();
            list.add(new Word(str));
        }

        Collections.sort(list);

        for(Word now : list){
            sb.append(now.str).append("\n");
        }

        System.out.println(sb);
    }
}