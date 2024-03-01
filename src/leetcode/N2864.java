package leetcode;

// Maximum Odd Binary Number
public class N2864 {
    public String maximumOddBinaryNumber(String s) {
        int len = s.length();
        int oneCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                oneCount += 1;
            }
        }

        if (oneCount == 1) {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < len - oneCount; i++) {
                answer.append("0");
            }
            answer.append("1");
            return answer.toString();
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < oneCount - 1; i++) {
            answer.append("1");
        }
        for (int i = 0; i < len - oneCount; i++) {
            answer.append("0");
        }
        answer.append("1");

        return answer.toString();
    }
}
