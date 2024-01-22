package leetcode;

// Set Mismatch
public class N645 {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] v = new boolean[n];
        int[] answer = new int[2];

        for (int num : nums) {
            if (v[num - 1]) {
                answer[0] = num;
            }
            v[num - 1] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                answer[1] = i + 1;
            }
        }

        return answer;
    }
}
