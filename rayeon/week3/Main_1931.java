import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Meeting{
	int start;
	int end;
	
	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class Main_1931 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings, new Comparator<Meeting>() {
			public int compare(Meeting o1, Meeting o2) {
				if(o1.end == o2.end)
					return o1.start - o2.start;
				else
					return o1.end - o2.end;
			}
		});

		int result = 0;
		int lastEnd = 0;
		for (Meeting meeting : meetings) {
			if (lastEnd <= meeting.start) {
				lastEnd = meeting.end;
				result++;
			}
		}
		
		System.out.println(result);
	}	
}