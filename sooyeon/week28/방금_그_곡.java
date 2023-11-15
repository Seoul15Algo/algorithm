package com.ssafy.algo230405_Random2.sooyeon.week28;

import java.util.*;

class Solution {
    PriorityQueue<Music> pq;
    public String solution(String m, String[] musicinfos) {
        //A, A#, B, C, C#, D, D#, E, F, F#, G, G#
        //A# -> a, C#-> c, D# -> d, F# -> f, G#->g 로 변환

        pq = new PriorityQueue<>();

        String M = "";
        for(int i = 0; i < m.length(); i++) {
            char cur = m.charAt(i);
            if(i!= m.length()-1 && m.charAt(i+1) == '#') {
                M += (cur+"").toLowerCase();
                i++;
            }else{
                M += cur;
            }
        }


        //시간 차이 받고, 음악 제목, 곡 시간만큼 연주 문자 붙혀서 저장

        for(int i = 0; i < musicinfos.length; i++) {
            int beforeH = Integer.parseInt(musicinfos[i].charAt(0)+""+musicinfos[i].charAt(1));
            int beforeM = Integer.parseInt(musicinfos[i].charAt(3)+""+musicinfos[i].charAt(4));
            int afterH = Integer.parseInt(musicinfos[i].charAt(6)+""+musicinfos[i].charAt(7));
            int afterM = Integer.parseInt(musicinfos[i].charAt(9)+""+musicinfos[i].charAt(10));

            int totalTime = (afterH - beforeH)*60 + (afterM - beforeM);

            String title = "";

            int point = 12;
            while(true) { //제목 뽑기
                char cur = musicinfos[i].charAt(point);
                point++;
                if(cur == ','){ //, 나오는 순간 break
                    break;
                }
                title += cur+"";
            }

            String music = "";
            while(true) { //악보 정보 뽑기
                if(point == musicinfos[i].length()) {
                    break;
                }
                char cur = musicinfos[i].charAt(point);

                if(point != musicinfos[i].length()-1 && musicinfos[i].charAt(point+1)=='#') {
                    music += (cur+"").toLowerCase();
                    point+=2;
                }else{
                    music += cur;
                    point +=1;
                }
            }


            String totalMusic = ""; //총 악보 정보
            int musicNum = music.length();
            for(int j = 0; j < (totalTime/musicNum); j++) {
                totalMusic += music;
            }
            totalMusic += music.substring(0, totalTime%musicNum);


            //totalTime 이 큰 순서로 pq에 넣기
            pq.offer(new Music(totalTime, title, totalMusic));

        }

        int size = pq.size();
        for(int i = 0; i < size; i++) {
            Music curM = pq.poll();
            if(curM.music.contains(M)) {
                return curM.title;
            }

        }

        return "(None)";
    }

}
class Music implements Comparable<Music>{
    int time;
    String title;
    String music;

    Music(int time, String title, String music){
        this.time = time;
        this.title = title;
        this.music = music;
    }

    @Override
    public int compareTo(Music o) {
        return o.time - this.time;
    }


}
