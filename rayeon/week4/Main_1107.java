package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1107 {
    static int N, M;
    static char[] buttons;

    static boolean check(int num) {
    	String sNum = Integer.toString(num);
    	
    	for (int i = 0; i < sNum.length(); i++) {
			for (char b : buttons) {
				if (sNum.charAt(i) == b)
					return false;
			}
		}
    	
    	return true;
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        buttons = new char[M];

        int result = Math.abs(N - 100);
      
        if (M > 0) {
        	String[] s = br.readLine().split(" ");
            for (int i = 0; i < M; i++)
            	buttons[i] = s[i].charAt(0);
        }
        
        int diff = 0;
        while (diff < 500000) {
        	if (diff <= N) {
        		if (check(N - diff)) {
        			result = Math.min(result, diff + Integer.toString(N - diff).length());
        			break;
        		}
        	}
        	
        	if (check(N + diff)) {
        		result = Math.min(result, diff + Integer.toString(N + diff).length());
    			break;
        	}
        	
        	diff++;
        }

        System.out.println(result);
    }
}