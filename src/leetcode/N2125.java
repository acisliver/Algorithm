package leetcode;

public class N2125 {
    public int numberOfBeams(String[] bank) {
        int[] devices = new int[bank.length];
        int sI = -1;

        for (int i = 0; i < bank.length; i++) {
            char[] row = bank[i].toCharArray();
            for (char device : row) {
                if (device == '1') {
                    devices[i] += 1;
                }
            }
            if (devices[i] > 0 && sI == -1) {
                sI = i;
            }
        }

        int answer = 0;
        for (int i = sI + 1; i < bank.length; i++) {
            if (devices[i] > 0) {
                answer += devices[i] * devices[sI];
                sI = i;
            }
        }

        return answer;
    }
}
