package programers;

import java.util.Arrays;

public class N138475 {
    public int[] solution(int e, int[] starts) {

        int min = getMin(starts);
        int[] nums = new int[e + 1];

        return Arrays.stream(starts)
                .map(this::getAnswer)
                .toArray();
    }

    private int getAnswer(int i) {

        return 0;
    }

    private int getMin(int[] array) {
        return Arrays.stream(array)
                .min()
                .orElseThrow(IllegalArgumentException::new);
    }
}
