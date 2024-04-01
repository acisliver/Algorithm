package leetcode;

// Length of Last Word
public class N58 {
    public int lengthOfLastWord(String s) {
        int answer = 0;
        int p = s.length() - 1;
        while (p >= 0) {
            if (s.charAt(p) == ' ') {
                if (answer > 0) {
                    return answer;
                }
            } else {
                answer += 1;
            }
            p--;
        }
        return answer;
    }
}
