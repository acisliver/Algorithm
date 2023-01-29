package kakao.blind2023;

import java.util.Arrays;

class Solution4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(Arrays.toString(solution4.solution(new long[]{63, 111, 95})));
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int a = 0; a < numbers.length; a++) {
            long number = numbers[a];
            String binary = Long.toBinaryString(number);
            int len = binary.length();
            int possible = 1;

            if (len % 2 == 0) {
                for (int i = 0; i < binary.length(); i++) {
                    char bit = binary.charAt(i);
                    if (i % 2 == 0) {
                        if (bit != '1') {
                            possible = 0;
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < binary.length(); i++) {
                    char bit = binary.charAt(i);
                    if (i % 2 == 1) {
                        if (bit != '1') {
                            possible = 0;
                            break;
                        }
                    }
                }
            }

            answer[a] = possible;
        }

        return answer;
    }
}
