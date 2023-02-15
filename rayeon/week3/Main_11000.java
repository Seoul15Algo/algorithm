import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lesson{
    int start_time;
    int end_time;
    
    public Lesson(int start_time, int end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
}

public class Main_11000 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        Lesson[] lessons = new Lesson[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            lessons[i] = new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(lessons, (o1, o2) -> (o1.start_time == o2.start_time ? o1.end_time - o2.end_time : o1.start_time - o2.start_time));
        
        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>();
        for (Lesson l : lessons) {
        	if (rooms.size() > 0 && rooms.peek() <= l.start_time)
				rooms.poll();
			
			rooms.offer(l.end_time);
		}
        
		System.out.println(rooms.size());
    }

}