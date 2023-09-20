import java.util.*;

class Solution {
    
    static class Music implements Comparable<Music>{
        private int seq;
        private String start;
        private String end;
        private String name;
        private int playtime;
        private String melody;
        
        public Music(int seq, String start, String end, String name, String melody) {
            this.seq = seq;
            this.start = start;
            this.end = end;
            this.name = name;
            this.playtime = getTime(end) - getTime(start);
            this.melody = getMelody(playtime, melody);
        }
        
        public int getTime(String time) {
            String[] s = time.split(":");
            return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        }
        
        public String getMelody(int playtime, String melody) {
            int musicLength = melody.length();
            if (playtime < musicLength) {
                return melody.substring(0, playtime);
            }
            int quotient = playtime / musicLength;
            int remain = playtime % musicLength;
            
            return melody.repeat(quotient) + melody.substring(0, remain);
        }
        
        public int compareTo(Music other) {
            if (this.playtime == other.playtime) {
                return Integer.compare(this.seq, other.seq);
            }
            return -1 * Integer.compare(this.playtime, other.playtime);
        }
    }
    public String solution(String m, String[] musicinfos) {
        PriorityQueue<Music> pq = new PriorityQueue<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] splitted = musicinfos[i].split(",");
            String start = splitted[0];
            String end = splitted[1];
            String name = splitted[2];
            String melody = replace(splitted[3]);
            pq.offer(new Music(i, start, end, name, melody));
        }
        m = replace(m);
        while (!pq.isEmpty()) {
            Music music = pq.poll();
            if (music.melody.contains(m)) {
                return music.name;
            }
        }
        return "(None)";
    }
    
    public String replace(String melody) {
       return melody.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a"); 
    }
}