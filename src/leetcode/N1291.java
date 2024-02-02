package leetcode;

import java.util.ArrayList;
import java.util.List;

// Sequential Digits
public class N1291 {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> answer = new ArrayList<>();

        int digit = String.valueOf(low).length();
        int head = (int) Math.pow(10, digit - 1);
        if (digit > 9) {
            return answer;
        }

        int num = 0;
        int n = 1;
        for (int i = 0; i < digit; i++) {
            num *= 10;
            num += n;
            n += 1;
        }

        while (digit <= 9 && num <= high) {
            if (num >= low) {
                answer.add(num);
            }

            if (n == 10) {
                digit += 1;
                head *= 10;
                num = 0;
                n = 1;
                for (int i = 0; i < digit; i++) {
                    num *= 10;
                    num += n;
                    n += 1;
                }
            } else {
                num %= head;
                num *= 10;
                num += n;
                n += 1;
            }
        }

        return answer;
    }
}
