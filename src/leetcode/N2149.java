package leetcode;

import java.util.ArrayList;
import java.util.List;

// Rearrange Array Elements by Sign
public class N2149 {
    public int[] rearrangeArray(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int num : nums) {
            if (num > 0) {
                pos.add(num);
            } else {
                neg.add(num);
            }
        }

        List<Integer> answer = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < pos.size() && j < neg.size()) {
            answer.add(pos.get(i));
            answer.add(neg.get(j));
            i += 1;
            j += 1;
        }

        while (i < pos.size()) {
            answer.add(pos.get(i));
            i += 1;
        }

        while (j < neg.size()) {
            answer.add(neg.get(j));
            j += 1;
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
