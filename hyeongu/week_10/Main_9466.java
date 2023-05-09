import java.util.*;
import java.io.*;

public class Main_9466 {
    static int N, answer, start;
    static List<Student> list;

    static class Student{
        int index;          // 해당 학생을 몇번째로 확인했는지
        boolean visit;      // 해당 학생을 확인했는지
        Student link;       // 해당 학생이 선택한 학생

        public Student() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int k = 0; k < T; k++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            list = new ArrayList<>();

            for(int i = 0; i <= N; i++) {
                list.add(new Student());
            }

            // N에서 프로젝트에 속한 학생의 수를 빼서 answer 계산
            answer = N;
            start = 1;

            for(int i = 1; i <= N; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                list.get(i).link = list.get(tmp);

                // 자기 자신을 선택한 경우
                if(tmp == i) {
                    list.get(i).visit = true;
                    answer--;
                }
            }

            // 아직 확인하지 않은 학생부터 확인
            for(int i = 1; i <= N; i++) {
                if(!list.get(i).visit) {
                    list.get(i).visit = true;
                    makeTeam(i);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }


    // start : 확인한 학생 수
    // index : start -> 해당 학생을 몇번째로 확인했는지를 저장
    static void makeTeam(int n) {
        Student next = list.get(n).link;
        int cnt = start;
        list.get(n).index = cnt++;

        // 다음 학생을 확인했을 때 까지 진행
        while(!next.visit) {
            next.visit = true;
            next.index = cnt++;
            next = next.link;
        }

        // 마지막 학생 즉 중복된 학생이 해당 사이클에 포함되는 경우
        // 생성된 사이클의 길이를 answer에 더해줌
        if(next.index >= start) {
            answer -= cnt-next.index;
        }
        // 마지막으로 확인한 학생 수
        start = cnt;
    }
}