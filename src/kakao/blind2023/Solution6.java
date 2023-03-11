package kakao.blind2023;

// 미로 탈출 명령어
// https://school.programmers.co.kr/learn/courses/30/lessons/150365
public class Solution6 {
    public static void main(String[] args) {

    }

    static int[] dR = {1, 0, 0, -1};
    static int[] dC = {0, -1, 1, 0};
    static char[] d = {'d', 'l', 'r', 'u'};
    static StringBuilder answer;
    static int N, M, X, Y, R, C, K;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        X = x;
        Y = y;
        R = r;
        C = c;
        K = k;
        return answer.toString();
    }

//    private void dfs(int curX, int curY, int dis) {
//        if (curX == X && curY == Y && dis == K)
//    }
}
