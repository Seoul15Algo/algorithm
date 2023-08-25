# 프로그래머스 2018 KAKAO BLIND RECRUITMENT - N진수 게임

---

## 구현 목표

[프로그래머스 링크](https://school.programmers.co.kr/learn/courses/30/lessons/17687)

튜브가 활동하는 코딩 동아리에서는 전통적으로 해오는 게임이 있다. 이 게임은 여러 사람이 둥글게 앉아서 숫자를 하나씩 차례대로 말하는 게임인데, 규칙은 다음과 같다.

숫자를 0부터 시작해서 차례대로 말한다. 첫 번째 사람은 0, 두 번째 사람은 1, … 열 번째 사람은 9를 말한다.
10 이상의 숫자부터는 한 자리씩 끊어서 말한다. 즉 열한 번째 사람은 10의 첫 자리인 1, 열두 번째 사람은 둘째 자리인 0을 말한다.
이렇게 게임을 진행할 경우,
```
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, …
```
순으로 숫자를 말하면 된다.

한편 코딩 동아리 일원들은 컴퓨터를 다루는 사람답게 이진수로 이 게임을 진행하기도 하는데, 이 경우에는
```
0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, …
```
순으로 숫자를 말하면 된다.

이진수로 진행하는 게임에 익숙해져 질려가던 사람들은 좀 더 난이도를 높이기 위해 이진법에서 십육진법까지 모든 진법으로 게임을 진행해보기로 했다. 숫자 게임이 익숙하지 않은 튜브는 게임에 져서 벌칙을 받는 굴욕을 피하기 위해, 자신이 말해야 하는 숫자를 스마트폰에 미리 출력해주는 프로그램을 만들려고 한다. 튜브의 프로그램을 구현하라.

---

## 풀이 방식

1. 최대 몇 번째 순서까지 게임이 진행되는지 먼저 구한다
2. 해당 순번을 넘어설때까지 말해야 하는 숫자를 모두 기록한다
3. 튜브가 n번째 순서이고, 게임에 참여하는 사람이 총 m명, 그리고 k번의 차례를 거쳐야 한다면 튜브는 n, n+m, n+m*2, n+m*3 ... n+m*(k-1) 까지의 차례에 숫자를 말해야 한다
4. 인덱스가 0부터 시작하기 때문에 각 차례 -1 번째 인덱스에 있는 수를 찾아 결과 문자열에 더해준다
5. 결과 문자열의 알파벳들을 모두 대문자로 바꾸어준다


``` Java
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        
        // 최대 순번
        int maxNum = p + (m * t);
        
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        // 최대 순번까지 말해야 하는 숫자를 기록
        while(sb.length() < maxNum) {
            sb.append(Integer.toString(num, n));
            num++;
        }
        
        String fullSequence = sb.toString();
    
        // 튜브의 순서
        int idx = p-1;
        
        for(int i = 0; i < t; i++) {
            answer.append(fullSequence.charAt(idx+(i*m)));
        }
        
        return answer.toString().toUpperCase();     // 알파벳 대문자로
    }
}

```

---
