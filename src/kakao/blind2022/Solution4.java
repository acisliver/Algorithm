package kakao.blind2022;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/92342
// DFS
public class Solution4 {
    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(Arrays.toString(s.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
    }

    static int DIFF = -1;
    static int[] LION;

    public int[] solution(int n, int[] info) {

        search(n, info, new int[11], 0, 0, 10);

        return DIFF == -1 ? new int[]{-1} : LION;
    }

    private void search(int n, int[] info, int[] lion, int lionScore, int apeachScore, int target) {

        if (target == 0) {
            int diff = lionScore - apeachScore;
            if (diff > 0) {
                lion[10] = n;
                if (diff > DIFF) {
                    DIFF = diff;
                    LION = lion.clone();
                } else if (diff == DIFF) {
                    LION = isSmaller(LION, lion) ? LION : lion.clone();
                }
            }
            return;
        }

        if (n > info[10 - target]) {
            lion[10 - target] = info[10 - target] + 1;
            search(n - lion[10 - target], info, lion, lionScore + target, apeachScore, target - 1);
            lion[10 - target] = 0;
        }
        search(n, info, lion, lionScore, apeachScore + (info[10 - target] == 0 ? 0 : target), target - 1);
    }

    private boolean isSmaller(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] == b[i]) continue;
            else return a[i] > b[i];
        }

        return true;
    }
}
