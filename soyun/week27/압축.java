import java.util.*;
 
class Solution {
    
    public List<Integer> solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();        
        HashMap<String, Integer> dict = new HashMap<>();

        int dictIdx = 1;
        for(int i='A'; i<='Z'; i++){
            dict.put( String.valueOf((char)i), dictIdx++) ;
        }
       
        int idx = 0;
        while(idx < msg.length()){
            StringBuilder w = new StringBuilder();
            while(idx < msg.length()){
                if(!dict.containsKey(w.toString() + msg.charAt(idx))){
                    break;
                }
                w.append(msg.charAt(idx++));
            }
            
            answer.add(dict.get(w.toString()));
            if(idx < msg.length()){
                dict.put(w.append(msg.charAt(idx)).toString(), dictIdx++);
            }            
        }
           
        return answer;
    }
}