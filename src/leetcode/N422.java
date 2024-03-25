package leetcode;

import java.util.ArrayList;
import java.util.List;

// Find All Duplicates in an Array
public class N422 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]);  // x: i번째 숫자의 값
            if (nums[x - 1] < 0) {  // 한 숫자는 1번 혹은 2번만 나타나기 때문에 이미 음수인 경우 중복
                answer.add(x);
            }
            nums[x - 1] *= -1;      // [1,n] 범위에 숫자가 존재하므로 [0,n-1]의 인덱스를 가짐 따라서 인덱스를 방문 여부 확인용으로 활용
        }
        return answer;
    }
}
