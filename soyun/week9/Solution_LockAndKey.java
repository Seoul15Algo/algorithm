package com.ssafy.algo230329_Random1.soyun.week9;

public class Solution_LockAndKey {

    int[][] key;
    int[][] lock;
    int point;  // lock 을 확장한 newLock 에서 lock 배열 기준 (0, 0)인 곳의 좌표

    boolean check(int[][] newLock) {
        // TODO: 자물쇠 영역이 모두 유효한지 확인
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {

                if(newLock[point + i][point + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    void match(int[][] newLock, int rot, int x, int y) {
        // TODO: newLock 배열에 key 배열을 더해줌
        for(int i = 0; i < key.length; i++) {
            for(int j = 0; j < key.length; j++) {

                // 회전 X
                if(rot == 0) {
                    newLock[x + i][y + j] += key[i][j];
                }
                // 90도 회전
                else if(rot == 1) {
                    newLock[x + i][y + j] += key[j][key.length - i - 1];
                }
                // 180도 회전
                else if(rot == 2) {
                    newLock[x + i][y + j] += key[key.length - i - 1][key.length- j - 1];
                }
                // 270도 회전
                else {
                    newLock[x + i][y + j] += key[key.length - j - 1][i];
                }
            }
        }
    }

    int[][] initNewLock(){
        // TODO: newLock을 생성하여 반환
        int[][] newLock = new int[lock.length + key.length * 2][lock.length + key.length * 2];
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                newLock[i + point][j + point] = lock[i][j];
            }
        }
        return newLock;
    }

    boolean rotateKey(int x, int y){
        // TODO: Key를 회전시킴
        for(int r = 0; r < 4; r++) {
            // lock 배열을 확장한 newLock
            // -> 키의 일부가 자물쇠의 영역을 벗어나도 일치하는 경우 고려
            int[][] newLock = initNewLock();

            match(newLock, r, x, y);

            // 자물쇠 영역이 모두 유효한지 확인
            if(check(newLock)) {
                return true;
            }
        }
        return false;
    }

    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.point = key.length - 1;

        // key 이동 가능 거리: (key 크기 - 1) + lock 크기
        for(int x = 0; x < point + lock.length; x++) {
            for(int y = 0; y < point + lock.length; y++) {
                if (rotateKey(x, y)){
                    return true;
                }
            }
        }
        return false;
    }
}
