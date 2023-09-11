package week28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 방금_그_곡 {
    static Map<String, String> notesMap = new HashMap<>();

    // 재생시간 순으로 정렬되는 Music 클래스 생성
    public class Music implements Comparable<Music> {
        public int time;
        public String name;
        public String notes;

        public Music (int time, String name, String notes) {
            this.time = time;
            this.name = name;
            this.notes = notes;
        }

        @Override
        public int compareTo(Music m) {
            return Integer.compare(m.time, this.time);
        }
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";

        // 노트 변환 표
        notesMap.put("A", "A");
        notesMap.put("A#", "B");
        notesMap.put("B", "C");
        notesMap.put("C", "D");
        notesMap.put("C#", "E");
        notesMap.put("D", "F");
        notesMap.put("D#", "G");
        notesMap.put("E", "H");
        notesMap.put("F", "I");
        notesMap.put("F#", "J");
        notesMap.put("G", "K");
        notesMap.put("G#", "L");

        PriorityQueue<Music> pq = new PriorityQueue<>();

        // 음악 정보를 파싱하여 Music 클래스로 변환
        for(String s : musicinfos) {
            String[] musicinfo = s.split(",");
            String[] start = musicinfo[0].split(":");
            String[] end = musicinfo[1].split(":");

            int time = (Integer.parseInt(end[0])*60 + Integer.parseInt(end[1])) -
                    (Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]));

            // 노트를 변환표에 맞게 변환
            ArrayList<String> convertedNotes = convertNotes(musicinfo[3]);
            int fullNotes = convertedNotes.size();

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < time / fullNotes; i++) {
                for(int j = 0; j < convertedNotes.size(); j++) {
                    sb.append(convertedNotes.get(j));
                }
            }

            for(int i = 0; i < time % fullNotes; i++) {
                sb.append(convertedNotes.get(i));
            }

            String notes = sb.toString();

            pq.add(new Music(time, musicinfo[2], notes));
        }

        boolean flag = false;

        while(!pq.isEmpty()) {
            Music music = pq.poll();
            ArrayList<String> convertedNotes = convertNotes(m);

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < convertedNotes.size(); i++) {
                sb.append(convertedNotes.get(i));
            }

            if(music.notes.contains(sb.toString())) {
                answer = music.name;
                flag = true;
                break;
            }
        }

        // 일치하는 곡이 없으면 (None) 반환
        return flag ? answer : "(None)";
    }

    // 노트 변환 메서드
    public ArrayList<String> convertNotes(String notes) {
        ArrayList<String> convertedNotes = new ArrayList<>();

        for(int i = 0; i < notes.length(); i++) {
            if(i < notes.length()-1) {
                if(notes.charAt(i+1) == '#') {
                    convertedNotes.add(notesMap.get(notes.substring(i, i+2)));
                    i++;
                    continue;
                }
            }

            convertedNotes.add(notesMap.get(notes.substring(i, i+1)));
        }

        return convertedNotes;
    }
}
