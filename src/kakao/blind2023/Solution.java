package kakao.blind2023;

public class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int height = x - r;
        int width = y - c;
        int distance = Math.abs(height) + Math.abs(width);
        int offset = distance - k;

        if (offset < 0) return "impossible";
        if (offset % 2 != 0) return "impossible";



        return answer;
    }
}
