package week9;

public class LockAndKey {
    private int keyLength;
    private int lockLength;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        keyLength = key.length;
        lockLength = lock.length;
        
        int[][] next = key;
        if (check(next, lock)) {
            answer = true;
        }
        for (int i = 0; i < 3; i++) {
            next = getRotatedKey(next);
            if (check(next, lock)) {
                answer = true;
            }
        }
        return answer;
    }
    
    public int[][] getRotatedKey(int[][] key) {
        int[][] next = new int[keyLength][keyLength];
        
        for (int i = 0; i < keyLength; i++) {
            for (int j = 0; j < keyLength; j++) {
                next[j][keyLength-i-1] = key[i][j];
            }
        }
        return next;
    }
    
    public boolean check(int[][] key, int[][] lock) {
        boolean result = false;
        int r = keyLength - 1;
        for (int i = 0; i < lockLength + keyLength - 1; i++) {
            int c = keyLength - 1;
            for (int j = 0; j < lockLength + keyLength - 1; j++) {
                if (insertKey(r, c, key, lock)) {
                    result = true;
                }
                c--;
            }
            r--;
        }
        return result;
    }
    
    public boolean insertKey(int r, int c, int[][] key, int[][] lock) {
        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                if (i + r >= 0 && i + r < keyLength && j + c >= 0 && j + c < keyLength) {
                    if ((lock[i][j] ^ key[i + r][j + c]) == 0) {
                        return false;
                    }
                    continue;
                }
                if (lock[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
