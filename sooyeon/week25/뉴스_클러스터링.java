package com.ssafy.algo230405_Random2.sooyeon.week25;

import java.util.*;

class 뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;

        //문자열을 char 배열로 바꾼다
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();

        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();

        for(int i = 0; i< str1.length()-1; i++) {
            char c1 = str1Arr[i];
            char c2 = str1Arr[i+1];
            if((('a'<=c1 && c1<='z') || ('A'<=c1 && c1<='Z')) && (('a'<=c2 && c2<='z') || ('A'<=c2 && c2<='Z'))) {
                //둘다 알파벳이면 map에 추가
                String str = (c1+""+c2).toUpperCase();
                if(map1.containsKey(str)) { //이미 있으면
                    map1.put(str, map1.get(str)+1);
                }else {
                    map1.put(str,1);
                }
            }
        }

        for(int i = 0; i< str2.length()-1; i++) {
            char c1 = str2Arr[i];
            char c2 = str2Arr[i+1];
            if((('a'<=c1 && c1<='z') || ('A'<=c1 && c1<='Z')) && (('a'<=c2 && c2<='z') || ('A'<=c2 && c2<='Z'))) {
                //둘다 알파벳이면 map에 추가
                String str = (c1+""+c2).toUpperCase();
                if(map2.containsKey(str)) { //이미 있으면
                    map2.put(str, map2.get(str)+1);
                }else {
                    map2.put(str,1);
                }
            }
        }

        int anum = 0;
        int bnum = 0;
        int n = 0; //교집합 개수

        for(String key : map1.keySet()) {
            anum += map1.get(key);
            if(map2.containsKey(key)) { //b에 키 값이 있으면
                n += Math.min(map1.get(key), map2.get(key));
            }
        }
        for(String key : map2.keySet()) {
            bnum += map2.get(key);
        }

        if(anum==0 && bnum==0) {
            return 65536;
        }else {
            return (65536 * (n) / (anum+bnum-n));
        }

    }
}