package kakao.blind2020;

// https://school.programmers.co.kr/learn/courses/30/lessons/60059
//
public class Solution3 {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        System.out.println(s.solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }

    public boolean solution(int[][] key, int[][] lock) {

        int[][] newLock = new int[lock.length * 3][lock[0].length * 3];

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[0].length; j++) {
                newLock[i + lock.length][j + lock[0].length] = lock[i][j];
            }
        }

        for (int k = 0; k < 4; k++) {
            for (int i = 1; i < newLock.length - lock.length; i++) {
                for (int j = 1; j < newLock[0].length - lock.length; j++) {
                    if (check(key, newLock, i, j)) return true;
                }
            }
            key = rotate(key);
        }

        return false;
    }

    private int[][] rotate(int[][] key) {

        int n = key.length;
        int m = key[0].length;

        int[][] rotate = new int[n][m];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = key[n - 1 - j][i];
            }
        }

        return rotate;
    }

    private boolean check(int[][] key, int[][] lock, int row, int col) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                lock[i + row][j + col] += key[i][j];
            }
        }

        for (int k = 0; k < key.length; k++) {
            for (int l = 0; l < key[0].length; l++) {
                if (lock[k + key.length][l + key[0].length] != 1) {

                    // 원상복구
                    for (int i = 0; i < key.length; i++) {
                        for (int j = 0; j < key[0].length; j++) {
                            lock[i + row][j + col] -= key[i][j];
                        }
                    }

                    return false;
                }
            }

        }

        return true;
    }
}
